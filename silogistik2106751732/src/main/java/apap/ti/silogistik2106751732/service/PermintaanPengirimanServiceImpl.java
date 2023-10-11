package apap.ti.silogistik2106751732.service;

import apap.ti.silogistik2106751732.DTO.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106751732.model.GudangBarang;
import apap.ti.silogistik2106751732.model.PermintaanPengiriman;
import apap.ti.silogistik2106751732.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106751732.repository.PermintaanPengirimanDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PermintaanPengirimanServiceImpl implements PermintaanPengirimanService {
    @Autowired
    PermintaanPengirimanDB permintaanPengirimanDB;

    @Override
    public void savePermintaanPengiriman(PermintaanPengiriman permintaanPengiriman) {
        permintaanPengiriman.setIsCancelled(false);
        permintaanPengiriman.setWaktuPermintaan(LocalDateTime.now());

        String nomorPengiriman = generateNomorPengiriman(permintaanPengiriman);
        permintaanPengiriman.setNomorPengiriman(nomorPengiriman);
        for (PermintaanPengirimanBarang barangPermintaan : permintaanPengiriman.getListBarangPermintaan()) {
            barangPermintaan.setIdPermintaanPengiriman(permintaanPengiriman);
        }
        permintaanPengirimanDB.save(permintaanPengiriman);
    }

    @Override
    public List<PermintaanPengiriman> getAllPermintaanPengiriman() {
        return permintaanPengirimanDB.findAll();
    }

    @Override
    public CreatePermintaanPengirimanRequestDTO accumulateBarangPermintaan(CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO) {
        List<PermintaanPengirimanBarang> listBarangPermintaan = createPermintaanPengirimanRequestDTO.getListBarangPermintaan();
            Map<String, PermintaanPengirimanBarang> accumulatedListBarangPermintaan = new HashMap<>();
//        listBarangDimuatGudang.get()
        for (PermintaanPengirimanBarang barangPermintaan : listBarangPermintaan) {
            String sku = barangPermintaan.getSkuBarang().getSku();
//            gudangBarang.setIdGudang(gudangMapper.restockBarangRequestDTOtoGudang(restockDto));
            if (accumulatedListBarangPermintaan.get(sku) == null) {
                accumulatedListBarangPermintaan.put(sku, barangPermintaan);
            } else if (barangPermintaan.getId() == null) {
                int newKuantitas = accumulatedListBarangPermintaan.get(sku).getKuantitasPengiriman() + barangPermintaan.getKuantitasPengiriman();
                accumulatedListBarangPermintaan.get(sku).setKuantitasPengiriman(newKuantitas);
            } else {
                int newKuantitas = accumulatedListBarangPermintaan.get(sku).getKuantitasPengiriman() + barangPermintaan.getKuantitasPengiriman();
                accumulatedListBarangPermintaan.get(sku).setKuantitasPengiriman(newKuantitas);
//                gudangBarangDB.deleteById(gudangBarang.getId());
            }
        }
        List<PermintaanPengirimanBarang> updatedListBarangPermintaan = accumulatedListBarangPermintaan.values().stream().toList();
        createPermintaanPengirimanRequestDTO.setListBarangPermintaan(updatedListBarangPermintaan);
        return createPermintaanPengirimanRequestDTO;
    }

    @Override
    public String generateNomorPengiriman(PermintaanPengiriman permintaanPengiriman) {
        String format = "REQ%02d%s%s";
        int totalBarang = 0;
        for (PermintaanPengirimanBarang barangPermintaan : permintaanPengiriman.getListBarangPermintaan()) {
            totalBarang = totalBarang + barangPermintaan.getKuantitasPengiriman();
        }
        totalBarang = totalBarang % 100;
        String kodeLayanan = "";
        switch (permintaanPengiriman.getJenisLayanan()) {
            case (1):
                kodeLayanan = "SAM";
                break;
            case (2):
                kodeLayanan = "KIL";
                break;
            case (3):
                kodeLayanan = "REG";
                break;
            case (4):
                kodeLayanan = "HEM";
                break;
        }
        String waktu = permintaanPengiriman.getWaktuPermintaan().toString().substring(11,19);
        String waktuStr = permintaanPengiriman.getWaktuPermintaan().toString();
        String nomorPengiriman = String.format(format, totalBarang, kodeLayanan, waktu);
        return nomorPengiriman;
    }

}
