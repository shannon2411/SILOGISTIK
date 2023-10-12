package apap.ti.silogistik2106751732.controller;

import apap.ti.silogistik2106751732.DTO.PermintaanPengirimanMapper;
import apap.ti.silogistik2106751732.DTO.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106751732.DTO.request.RestockBarangRequestDTO;
import apap.ti.silogistik2106751732.DTO.response.ReadGudangResponseDTO;
import apap.ti.silogistik2106751732.DTO.response.ReadListItemPermintaanPengirimanResponseDTO;
import apap.ti.silogistik2106751732.model.*;
import apap.ti.silogistik2106751732.service.BarangService;
import apap.ti.silogistik2106751732.service.KaryawanService;
import apap.ti.silogistik2106751732.service.PermintaanPengirimanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/permintaan-pengiriman")
public class PermintaanPengirimanController {
    @Autowired
    PermintaanPengirimanService permintaanPengirimanService;

    @Autowired
    PermintaanPengirimanMapper permintaanPengirimanMapper;

    @Autowired
    KaryawanService karyawanService;

    @Autowired
    BarangService barangService;

    @GetMapping("")
    public String viewAllPermintaanPengiriman(Model model) {
        List<PermintaanPengiriman> listPermintaanPengiriman = permintaanPengirimanService.getAllPermintaanPengiriman();
        List<ReadListItemPermintaanPengirimanResponseDTO> listPermintaanPengirimanResponse = new ArrayList<>();
        for (PermintaanPengiriman permintaanPengiriman : listPermintaanPengiriman) {
            ReadListItemPermintaanPengirimanResponseDTO permintaanPengirimanResponse = permintaanPengirimanMapper.permintaanPengirimanToReadListItemPermintaanPengirimanResponseDTO(permintaanPengiriman);
            listPermintaanPengirimanResponse.add(permintaanPengirimanResponse);
        }
        model.addAttribute("listPermintaanPengiriman", listPermintaanPengirimanResponse);
        return "daftar-permintaan-pengiriman";
    }

    @GetMapping("/{idPermintaanPengiriman}")
    public String detailPermintaanPengiriman(@PathVariable(value = "idPermintaanPengiriman") Long idPermintaanPengiriman, Model model) {
        PermintaanPengiriman permintaanPengiriman = permintaanPengirimanService.getPermintaanPengirimanById(idPermintaanPengiriman);
        model.addAttribute("permintaanPengiriman", permintaanPengiriman);
        return "detail-permintaan-pengiriman";
    }

    @GetMapping("/tambah")
    public String formTambahPermintaanPengiriman(Model model) {
        CreatePermintaanPengirimanRequestDTO permintaanPengirimanDTO = new CreatePermintaanPengirimanRequestDTO();
        List<Karyawan> listKaryawan = karyawanService.getAllKaryawan();
        List<Barang> listBarang = barangService.getAllBarang();
        permintaanPengirimanDTO.setListBarangPermintaan(new ArrayList<>());
        permintaanPengirimanDTO.getListBarangPermintaan().add(new PermintaanPengirimanBarang());
        model.addAttribute("permintaanPengirimanDTO", permintaanPengirimanDTO);
        model.addAttribute("listKaryawan", listKaryawan);
        model.addAttribute("listBarang", listBarang);
        return "form-tambah-permintaan-pengiriman";
    }

    @PostMapping(value="/tambah", params = {"addRow"})
    public String addRowPermintaanPengirimanBarang(@ModelAttribute CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO, Model model) {
        if (createPermintaanPengirimanRequestDTO.getListBarangPermintaan() == null || createPermintaanPengirimanRequestDTO.getListBarangPermintaan().size() == 0) {
            createPermintaanPengirimanRequestDTO.setListBarangPermintaan(new ArrayList<>());
        }

        //memasukkan Penulis baru (kosong) ke list, untuk dirender sebagai row baru
        createPermintaanPengirimanRequestDTO.getListBarangPermintaan().add(new PermintaanPengirimanBarang());
        List<Karyawan> listKaryawan = karyawanService.getAllKaryawan();
        List<Barang> listBarang = barangService.getAllBarang();

        //kirim list penerbit penulis untuk menjadi pilihan pada dropdown
        model.addAttribute("listKaryawan", listKaryawan);
        model.addAttribute("listBarang", listBarang);
        model.addAttribute("permintaanPengirimanDTO", createPermintaanPengirimanRequestDTO);
        return "form-tambah-permintaan-pengiriman";
    }

    @PostMapping(value = "/tambah", params = {"deleteRow"})
    public String deleteRowPermintaanPengirimanBarang(@ModelAttribute CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO, @RequestParam("deleteRow") int row, Model model) {
        createPermintaanPengirimanRequestDTO.getListBarangPermintaan().remove(row);
        List<Karyawan> listKaryawan = karyawanService.getAllKaryawan();
        List<Barang> listBarang = barangService.getAllBarang();

        model.addAttribute("listKaryawan", listKaryawan);
        model.addAttribute("listBarang", listBarang);
        model.addAttribute("permintaanPengirimanDTO", createPermintaanPengirimanRequestDTO);
        return "form-tambah-permintaan-pengiriman";
    }

    @PostMapping("/tambah")
    public String createPermintaanPengiriman(@ModelAttribute CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO, Model model, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            String errorMessage = "Lengkapi isian!";
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("listKaryawan", karyawanService.getAllKaryawan());
            model.addAttribute("listBarang", barangService.getAllBarang());
            model.addAttribute("permintaanPengirimanDTO", createPermintaanPengirimanRequestDTO);
            return "form-tambah-permintaan-pengiriman";
        }


        CreatePermintaanPengirimanRequestDTO processedDTO = permintaanPengirimanService.accumulateBarangPermintaan(createPermintaanPengirimanRequestDTO);
        PermintaanPengiriman newPermintaanPengiriman = permintaanPengirimanMapper.createPermintaanPengirimanRequestDTOToPermintaanPengiriman(processedDTO);
        try {
            permintaanPengirimanService.savePermintaanPengiriman(newPermintaanPengiriman);
            redirectAttrs.addFlashAttribute("flashMessage", "Permintaan pengiriman berhasil ditambahkan");

            return "redirect:/permintaan-pengiriman";
        } catch (ResponseStatusException ex) {
            model.addAttribute("listKaryawan", karyawanService.getAllKaryawan());
            model.addAttribute("listBarang", barangService.getAllBarang());
            model.addAttribute("permintaanPengirimanDTO", createPermintaanPengirimanRequestDTO);
            model.addAttribute("flashMessage", ex.getReason());
            return "form-tambah-permintaan-pengiriman";
        }
    }

    @GetMapping("/{idPermintaanPengiriman}/cancel")
    public String cancelPermintaanPengiriman(@PathVariable(value = "idPermintaanPengiriman") Long idPermintaanPengiriman,
                                             Model model) {
        try {
            PermintaanPengiriman permintaanPengirimanCancelled = permintaanPengirimanService.cancelPermintaanPengiriman(idPermintaanPengiriman);
            model.addAttribute("message", String.format("Permintaan pengiriman dengan nomor pengiriman %s berhasil dihapus", permintaanPengirimanCancelled.getNomorPengiriman()));
        } catch (ResponseStatusException ex) {
            model.addAttribute("message", ex.getReason());
        }
        return "cancel-permintaan-pengiriman";

    }
}
