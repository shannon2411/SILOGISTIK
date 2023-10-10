package apap.ti.silogistik2106751732.service;

import apap.ti.silogistik2106751732.model.Barang;
import apap.ti.silogistik2106751732.model.GudangBarang;

import java.util.List;

public interface BarangService {
    void saveBarang(Barang barang);

    List<Barang> getAllBarang();

    Barang getBarangById(String skuBarang);

}
