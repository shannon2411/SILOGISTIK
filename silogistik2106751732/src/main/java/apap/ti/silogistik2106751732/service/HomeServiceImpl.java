package apap.ti.silogistik2106751732.service;

import apap.ti.silogistik2106751732.DTO.response.HomeResponseDTO;
import apap.ti.silogistik2106751732.repository.BarangDB;
import apap.ti.silogistik2106751732.repository.GudangDB;
import apap.ti.silogistik2106751732.repository.KaryawanDB;
import apap.ti.silogistik2106751732.repository.PermintaanPengirimanDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    GudangDB gudangDB;

    @Autowired
    BarangDB barangDB;

    @Autowired
    PermintaanPengirimanDB permintaanPengirimanDB;

    @Autowired
    KaryawanDB karyawanDB;

    @Override
    public HomeResponseDTO getQuantities() {
        HomeResponseDTO response = new HomeResponseDTO();
        response.setJumlahBarang(barangDB.count());
        response.setJumlahGudang(gudangDB.count());
        response.setJumlahKaryawan(karyawanDB.count());
        response.setJumlahPermintaanPengiriman(permintaanPengirimanDB.count());
        return response;
    }
}
