package apap.ti.silogistik2106751732.service;

import apap.ti.silogistik2106751732.model.Barang;
import apap.ti.silogistik2106751732.model.Gudang;
import apap.ti.silogistik2106751732.model.GudangBarang;
import apap.ti.silogistik2106751732.repository.BarangDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BarangServiceImpl implements BarangService {
    @Autowired
    private BarangDB barangDB;

    @Override
    public void saveBarang(Barang barang) {
        barangDB.save(barang);
    }

    @Override
    public List<Barang> getAllBarang() {
        return barangDB.findAll();
    }

    @Override
    public Barang getBarangById(String skuBarang) {
        Optional<Barang> barang = barangDB.findById(skuBarang);
        if (barang.isPresent()) {
            return barang.get();
        } else {
            return null;
        }
    }

}
