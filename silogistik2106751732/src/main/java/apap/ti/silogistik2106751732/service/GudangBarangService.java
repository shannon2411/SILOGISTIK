package apap.ti.silogistik2106751732.service;

import apap.ti.silogistik2106751732.model.Barang;
import apap.ti.silogistik2106751732.model.GudangBarang;

import java.util.List;

public interface GudangBarangService {
    void deleteGudangBarang(GudangBarang gudangBarang);
    List<GudangBarang> getAllGudangBarangBySkuBarang(String skuBarang);

    List<GudangBarang> getAllGudangBarang();
}
