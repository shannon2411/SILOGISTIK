package apap.ti.silogistik2106751732.controller;

import apap.ti.silogistik2106751732.model.Barang;
import apap.ti.silogistik2106751732.service.BarangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/barang")
public class BarangController {
    @Autowired
    BarangService barangService;

    @GetMapping("")
    public String viewAllBarang(Model model) {
        List<Barang> listBarang = barangService.getAllBarang();
        model.addAttribute("listBarang", listBarang);
        return "daftar-barang";
    }

}
