package apap.ti.silogistik2106751732.controller;

import apap.ti.silogistik2106751732.model.Barang;
import apap.ti.silogistik2106751732.model.PermintaanPengiriman;
import apap.ti.silogistik2106751732.service.BarangService;
import apap.ti.silogistik2106751732.service.PermintaanPengirimanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class BonusController {
    @Autowired
    BarangService barangService;

    @Autowired
    PermintaanPengirimanService permintaanPengirimanService;

    @GetMapping("/filter-permintaan-pengiriman")
    public String filterPermintaanPengiriman(@RequestParam(value = "start-date", required = false) LocalDate startDate,
                                             @RequestParam(value = "end-date", required = false) LocalDate endDate,
                                             @RequestParam(value = "sku", required = false) String sku,
                                             Model model) {
        List<Barang> listBarang = barangService.getAllBarang();
        List<PermintaanPengiriman> listPermintaanPengiriman;
        if ((startDate != null && endDate != null) && sku != null) {
            listPermintaanPengiriman = permintaanPengirimanService.filterPermintaanPengiriman(sku, startDate, endDate);
            model.addAttribute("listPermintaanPengiriman", listPermintaanPengiriman);
        }
        model.addAttribute("listBarang", listBarang);
        return "form-filter-permintaan-pengiriman";
    }
}
