package apap.ti.silogistik2106751732.service;

import apap.ti.silogistik2106751732.model.Gudang;
import apap.ti.silogistik2106751732.repository.GudangDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GudangServiceImpl implements GudangService{

    @Autowired
    private GudangDB gudangDB;

    @Override
    public void saveGudang(Gudang gudang) {
        gudangDB.save(gudang);
    }

    @Override
    public List<Gudang> getAllGudang() {
        return gudangDB.findAll();
    }

    @Override
    public Gudang getGudangById(Long id) {
        Optional<Gudang> gudang = gudangDB.findById(id);
        if (gudang.isPresent()) {
            return gudang.get();
        } else {
            return null;
        }
    }
}
