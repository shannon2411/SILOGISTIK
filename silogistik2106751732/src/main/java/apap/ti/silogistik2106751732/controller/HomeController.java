package apap.ti.silogistik2106751732.controller;

import apap.ti.silogistik2106751732.DTO.response.HomeResponseDTO;
import apap.ti.silogistik2106751732.model.Gudang;
import apap.ti.silogistik2106751732.model.GudangBarang;
import apap.ti.silogistik2106751732.service.GudangBarangService;
import apap.ti.silogistik2106751732.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    HomeService homeService;

    @Autowired
    GudangBarangService gudangBarangService;

    @GetMapping("/")
    public String home(Model model){
        HomeResponseDTO quantities = homeService.getQuantities();
        model.addAttribute("quantities", quantities);

        return "home";
    }
}
