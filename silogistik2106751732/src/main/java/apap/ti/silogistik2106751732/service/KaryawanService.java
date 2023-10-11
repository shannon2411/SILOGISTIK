package apap.ti.silogistik2106751732.service;

import apap.ti.silogistik2106751732.model.Karyawan;

import java.util.List;

public interface KaryawanService {
    void saveKaryawan(Karyawan karyawan);
    List<Karyawan> getAllKaryawan();
}
