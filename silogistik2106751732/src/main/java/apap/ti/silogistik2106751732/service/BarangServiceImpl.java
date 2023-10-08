package apap.ti.silogistik2106751732.service;

import apap.ti.silogistik2106751732.model.Barang;
import apap.ti.silogistik2106751732.repository.BarangDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
