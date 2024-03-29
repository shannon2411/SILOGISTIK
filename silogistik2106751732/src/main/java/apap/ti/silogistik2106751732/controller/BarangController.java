package apap.ti.silogistik2106751732.controller;

import apap.ti.silogistik2106751732.DTO.BarangMapper;
import apap.ti.silogistik2106751732.DTO.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106751732.DTO.response.ReadBarangResponseDTO;
import apap.ti.silogistik2106751732.model.Barang;
import apap.ti.silogistik2106751732.service.BarangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/barang")
public class BarangController {
    @Autowired
    BarangService barangService;

    @Autowired
    BarangMapper barangMapper;

    @GetMapping("")
    public String viewAllBarang(Model model) {
        List<Barang> listBarang = barangService.getAllBarang();
        List<ReadBarangResponseDTO> listBarangResponse = new ArrayList<>();
        for (Barang barang : listBarang) {
            ReadBarangResponseDTO barangResponse = barangMapper.barangToReadBarangResponseDTO(barang);
            listBarangResponse.add(barangResponse);
        }
        model.addAttribute("listBarang", listBarangResponse);
        return "daftar-barang";
    }

    @GetMapping("/tambah")
    public String formAddBarang(Model model) {
        //kirim bukuDTO untuk nantinya isian field di-bind kesini.
        CreateBarangRequestDTO barangDTO = new CreateBarangRequestDTO();
        model.addAttribute("barangDTO", barangDTO);

        return "form-tambah-barang";
    }

    @PostMapping("/tambah")
    public String formAddBarang(@ModelAttribute CreateBarangRequestDTO barangDTO, Model model, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
            model.addAttribute("validationErrors", errors);
            return "400";
        } else {
            System.out.println("no error detected");
        }
        Barang barang = barangMapper.createBarangRequestDTOToBarang(barangDTO);
        try {
            barangService.saveBarang(barang);
            redirectAttrs.addFlashAttribute("flashMessage", String.format("Barang %s (%s) berhasil ditambahkan", barang.getMerk(), barang.getSku()));
            return "redirect:/barang";
        } catch (ResponseStatusException ex) {
            model.addAttribute("flashMessage", ex.getReason());
            model.addAttribute("barangDTO", barangDTO);
            return "form-tambah-barang";
        }

    }

    @GetMapping("/{skuBarang}")
    public String detailBarang(@PathVariable(required = true) String skuBarang, Model model) {
        Barang barang = barangService.getBarangById(skuBarang);
        ReadBarangResponseDTO barangResponse = barangMapper.barangToReadBarangResponseDTO(barang);
        model.addAttribute("barang", barangResponse);
        return "detail-barang";
    };

    @GetMapping("/{skuBarang}/ubah")
    public String formUbahBarang(@PathVariable(value = "skuBarang") String skuBarang, Model model) {
        Barang barang = barangService.getBarangById(skuBarang);
        model.addAttribute("barangDTO", barang);
        return "form-ubah-barang";
    }

    @PostMapping("/{skuBarang}/ubah")
    public String formUbahBarang(@ModelAttribute Barang barang, @PathVariable(value = "skuBarang") String skuBarang, Model model, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
            model.addAttribute("validationErrors", errors);
            return "kon";
        } else {
            System.out.println("no error detected");
        }
        try {
            barangService.saveBarang(barang);
            redirectAttrs.addFlashAttribute("flashMessage", String.format("Barang %s (%s) berhasil diubah", barang.getMerk(), barang.getSku()));
            return "redirect:/barang";
        } catch (ResponseStatusException ex) {
            model.addAttribute("flashMessage", ex.getReason());
            model.addAttribute("barangDTO", barang);
            return "form-ubah-barang";
        }
    }






}
