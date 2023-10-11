package apap.ti.silogistik2106751732.service;

import apap.ti.silogistik2106751732.model.Karyawan;
import apap.ti.silogistik2106751732.repository.KaryawanDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KaryawanServiceImpl implements KaryawanService{
    @Autowired
    KaryawanDB karyawanDB;

    @Override
    public void saveKaryawan(Karyawan karyawan) {
        karyawanDB.save(karyawan);
    }

    @Override
    public List<Karyawan> getAllKaryawan() { return karyawanDB.findAll(); }
}
