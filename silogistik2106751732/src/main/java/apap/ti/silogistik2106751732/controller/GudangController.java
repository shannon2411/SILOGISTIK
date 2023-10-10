package apap.ti.silogistik2106751732.controller;

import apap.ti.silogistik2106751732.DTO.GudangMapper;
import apap.ti.silogistik2106751732.DTO.request.RestockBarangRequestDTO;
import apap.ti.silogistik2106751732.DTO.response.ReadGudangResponseDTO;
import apap.ti.silogistik2106751732.model.Barang;
import apap.ti.silogistik2106751732.model.Gudang;
import apap.ti.silogistik2106751732.model.GudangBarang;
import apap.ti.silogistik2106751732.service.BarangService;
import apap.ti.silogistik2106751732.service.GudangBarangService;
import apap.ti.silogistik2106751732.service.GudangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/gudang")
public class GudangController {

    @Autowired
    GudangService gudangService;

    @Autowired
    GudangMapper gudangMapper;

    @Autowired
    BarangService barangService;

    @Autowired
    GudangBarangService gudangBarangService;

    @GetMapping("")
    public String viewAllGudang(Model model){
        List<Gudang> listGudang = gudangService.getAllGudang();
        model.addAttribute("listGudang", listGudang);
        return "daftar-gudang";
    }

    @GetMapping("/{idGudang}")
    public String detailGudang(@PathVariable(required = true) Long idGudang,Model model) {
        Gudang gudang = gudangService.getGudangById(idGudang);
        ReadGudangResponseDTO gudangResponse = gudangMapper.gudangToReadGudangResponseDTO(gudang);
        model.addAttribute("gudang", gudangResponse);
        return "detail-gudang";
    };

    @GetMapping("/{idGudang}/restock-barang")
    public String formRestockBarang(@PathVariable(required = true) Long idGudang, Model model) {
        Gudang gudang = gudangService.getGudangById(idGudang);

        RestockBarangRequestDTO restockBarangRequestDTO = gudangMapper.gudangToRestockBarangRequestDTO(gudang);
        if (restockBarangRequestDTO.getListBarangDimuatGudang().size() == 0) {
            restockBarangRequestDTO.setListBarangDimuatGudang(new ArrayList<>());
            restockBarangRequestDTO.getListBarangDimuatGudang().add(new GudangBarang());
        }
        model.addAttribute("listBarang", barangService.getAllBarang());
        model.addAttribute("gudangDTO", restockBarangRequestDTO);
        return "form-restock-barang";
    }

    @PostMapping(value="/{idGudang}/restock-barang", params = {"addRow"})
    public String addRowRestockBarang(@ModelAttribute RestockBarangRequestDTO restockBarangRequestDTO, Model model) {
        if (restockBarangRequestDTO.getListBarangDimuatGudang() == null || restockBarangRequestDTO.getListBarangDimuatGudang().size() == 0) {
            restockBarangRequestDTO.setListBarangDimuatGudang(new ArrayList<>());
        }

        //memasukkan Penulis baru (kosong) ke list, untuk dirender sebagai row baru
        restockBarangRequestDTO.getListBarangDimuatGudang().add(new GudangBarang());

        //kirim list penerbit penulis untuk menjadi pilihan pada dropdown
        model.addAttribute("listBarang", barangService.getAllBarang());
        model.addAttribute("gudangDTO", restockBarangRequestDTO);
        return "form-restock-barang";
    }

    @PostMapping(value = "/{idGudang}/restock-barang", params = {"deleteRow"})
    public String deleteRowRestockBarang(@ModelAttribute RestockBarangRequestDTO restockBarangRequestDTO, @RequestParam("deleteRow") int row, Model model) {
        GudangBarang gudangBarangToDelete = restockBarangRequestDTO.getListBarangDimuatGudang().get(row);
        gudangBarangService.deleteGudangBarang(gudangBarangToDelete);
        restockBarangRequestDTO.getListBarangDimuatGudang().remove(row);
        model.addAttribute("listBarang", barangService.getAllBarang());
        model.addAttribute("gudangDTO", restockBarangRequestDTO);
        return "form-restock-barang";
    }

    @PostMapping("/{idGudang}/restock-barang")
    public String restockBarang(@ModelAttribute RestockBarangRequestDTO restockBarangRequestDTO, Model model, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            String errorMessage = "Lengkapi isian!";
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("listBarang", barangService.getAllBarang());
            model.addAttribute("gudangDTO", restockBarangRequestDTO);
            return "form-restock-barang";
        }


        RestockBarangRequestDTO processedDTO = gudangService.restockBarang(restockBarangRequestDTO);
        Gudang newGudang = gudangMapper.restockBarangRequestDTOtoGudang(processedDTO);
        gudangService.saveGudang(newGudang);


        // Add variables for rendering in Thymeleaf, if needed
        redirectAttrs.addAttribute("flash-message", "Berhasil melakukan restock");

        return "redirect:/gudang/" + newGudang.getIdGudang(); // Adjust the view name as needed

    }

    @GetMapping("/cari-barang")
    public String findGudangByBarangForm(@RequestParam(value = "sku", required = false) String skuBarang, Model model) {
        List<Barang> listBarang = barangService.getAllBarang();
        model.addAttribute("listBarang", listBarang);
        if (skuBarang != null) {
            List<GudangBarang> listGudangMemuatBarang = gudangBarangService.getAllGudangBarangBySkuBarang(skuBarang);
            model.addAttribute("listGudang", listGudangMemuatBarang);
        }
        return "form-find-barang";
    }
}
