package apap.ti.silogistik2106751732.controller;

import apap.ti.silogistik2106751732.DTO.response.HomeResponseDTO;
import apap.ti.silogistik2106751732.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    HomeService homeService;

    @GetMapping("/")
    public String home(Model model){
        HomeResponseDTO quantities = homeService.getQuantities();
        model.addAttribute("quantities", quantities);
        return "home";
    }
}
