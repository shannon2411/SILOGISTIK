package apap.ti.silogistik2106751732.controller;

import apap.ti.silogistik2106751732.DTO.PermintaanPengirimanMapper;
import apap.ti.silogistik2106751732.DTO.response.ReadBarangResponseDTO;
import apap.ti.silogistik2106751732.DTO.response.ReadListItemPermintaanPengirimanResponseDTO;
import apap.ti.silogistik2106751732.model.PermintaanPengiriman;
import apap.ti.silogistik2106751732.service.PermintaanPengirimanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/permintaan-pengiriman")
public class PermintaanPengirimanController {
    @Autowired
    PermintaanPengirimanService permintaanPengirimanService;

    @Autowired
    PermintaanPengirimanMapper permintaanPengirimanMapper;

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
}
