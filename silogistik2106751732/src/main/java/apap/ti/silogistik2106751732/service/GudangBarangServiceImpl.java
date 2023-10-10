package apap.ti.silogistik2106751732.service;

import apap.ti.silogistik2106751732.model.GudangBarang;
import apap.ti.silogistik2106751732.repository.GudangBarangDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GudangBarangServiceImpl implements GudangBarangService {
    @Autowired
    GudangBarangDB gudangBarangDb;
    @Override
    public void deleteGudangBarang(GudangBarang gudangBarang) {
        gudangBarangDb.delete(gudangBarang);
    }
}
