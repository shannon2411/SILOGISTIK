package apap.ti.silogistik2106751732.service;

import apap.ti.silogistik2106751732.model.Barang;
import apap.ti.silogistik2106751732.model.Gudang;
import apap.ti.silogistik2106751732.model.GudangBarang;
import apap.ti.silogistik2106751732.repository.BarangDB;
import apap.ti.silogistik2106751732.repository.GudangBarangDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GudangBarangServiceImpl implements GudangBarangService {
    @Autowired
    GudangBarangDB gudangBarangDb;

    @Autowired
    BarangDB barangDb;
    @Override
    public void deleteGudangBarang(GudangBarang gudangBarang) {
        gudangBarangDb.delete(gudangBarang);
    }

    @Override
    public List<GudangBarang> getAllGudangBarangBySkuBarang(String skuBarang) {
        Optional<Barang> optBarang = barangDb.findById(skuBarang);
        Barang barang;
        if (optBarang.isPresent()) {
            barang = optBarang.get();
        } else {
            return null;
        }
        List<GudangBarang> listGudangMemuatBarang = barang.getListGudangMemuatBarang();
        return listGudangMemuatBarang;
    }

    @Override
    public List<GudangBarang> getAllGudangBarang() {
        return gudangBarangDb.findAll();
    }

    ;
}
