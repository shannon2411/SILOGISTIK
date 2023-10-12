package apap.ti.silogistik2106751732.service;

import apap.ti.silogistik2106751732.DTO.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106751732.model.Barang;
import apap.ti.silogistik2106751732.model.PermintaanPengiriman;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PermintaanPengirimanService {
    void savePermintaanPengiriman(PermintaanPengiriman permintaanPengiriman);

    List<PermintaanPengiriman> getAllPermintaanPengiriman();

    PermintaanPengiriman getPermintaanPengirimanById(Long id);

    CreatePermintaanPengirimanRequestDTO accumulateBarangPermintaan(CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO);

    String generateNomorPengiriman(PermintaanPengiriman permintaanPengiriman);
}
