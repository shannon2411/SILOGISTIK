package apap.ti.silogistik2106751732.controller;

import apap.ti.silogistik2106751732.DTO.GudangMapper;
import apap.ti.silogistik2106751732.DTO.response.ReadGudangResponseDTO;
import apap.ti.silogistik2106751732.model.Gudang;
import apap.ti.silogistik2106751732.service.GudangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/gudang")
public class GudangController {

    @Autowired
    GudangService gudangService;

    @Autowired
    GudangMapper gudangMapper;

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
}
