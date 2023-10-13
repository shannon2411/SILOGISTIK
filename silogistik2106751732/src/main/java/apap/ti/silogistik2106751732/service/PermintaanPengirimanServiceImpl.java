package apap.ti.silogistik2106751732.service;

import apap.ti.silogistik2106751732.DTO.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106751732.model.GudangBarang;
import apap.ti.silogistik2106751732.model.PermintaanPengiriman;
import apap.ti.silogistik2106751732.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106751732.repository.PermintaanPengirimanDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PermintaanPengirimanServiceImpl implements PermintaanPengirimanService {
    @Autowired
    PermintaanPengirimanDB permintaanPengirimanDB;

    @Override
    public void savePermintaanPengiriman(PermintaanPengiriman permintaanPengiriman) throws ResponseStatusException {
        if (permintaanPengiriman.getBiayaPengiriman() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Biaya pengiriman tidak boleh nol");
        }
        permintaanPengiriman.setIsCancelled(false);
        permintaanPengiriman.setWaktuPermintaan(LocalDateTime.now());
        String nomorPengiriman = generateNomorPengiriman(permintaanPengiriman);
        permintaanPengiriman.setNomorPengiriman(nomorPengiriman);
        for (PermintaanPengirimanBarang barangPermintaan : permintaanPengiriman.getListBarangPermintaan()) {
            if (barangPermintaan.getKuantitasPengiriman() <= 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Kuantitas tidak boleh nol");
            }
            barangPermintaan.setIdPermintaanPengiriman(permintaanPengiriman);
            List<GudangBarang> listGudangBarang = barangPermintaan.getSkuBarang().getListGudangMemuatBarang();
            int totalStokBarang = 0;
            for (GudangBarang gudangBarang : listGudangBarang) {
                totalStokBarang = totalStokBarang + gudangBarang.getStok();
            }
            if (totalStokBarang < barangPermintaan.getKuantitasPengiriman()) {
               throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Barang %s (%s) hanya memiliki stok sebanyak %d", barangPermintaan.getSkuBarang().getMerk(), barangPermintaan.getSkuBarang().getSku(), totalStokBarang));
            }
        }

        permintaanPengirimanDB.save(permintaanPengiriman);
    }

    @Override
    public List<PermintaanPengiriman> getAllPermintaanPengiriman() {
        return permintaanPengirimanDB.findAll();
    }

    @Override
    public PermintaanPengiriman getPermintaanPengirimanById(Long id) {
        Optional<PermintaanPengiriman> permintaanPengiriman = permintaanPengirimanDB.findById(id);
        if (permintaanPengiriman.isPresent()) {
            return permintaanPengiriman.get();
        } else {
            return null;
        }
    }

    @Override
    public CreatePermintaanPengirimanRequestDTO accumulateBarangPermintaan(CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO) {
        List<PermintaanPengirimanBarang> listBarangPermintaan = createPermintaanPengirimanRequestDTO.getListBarangPermintaan();
            Map<String, PermintaanPengirimanBarang> accumulatedListBarangPermintaan = new HashMap<>();
        for (PermintaanPengirimanBarang barangPermintaan : listBarangPermintaan) {
            String sku = barangPermintaan.getSkuBarang().getSku();
            if (accumulatedListBarangPermintaan.get(sku) == null) {
                accumulatedListBarangPermintaan.put(sku, barangPermintaan);
            } else if (barangPermintaan.getId() == null) {
                int newKuantitas = accumulatedListBarangPermintaan.get(sku).getKuantitasPengiriman() + barangPermintaan.getKuantitasPengiriman();
                accumulatedListBarangPermintaan.get(sku).setKuantitasPengiriman(newKuantitas);
            } else {
                int newKuantitas = accumulatedListBarangPermintaan.get(sku).getKuantitasPengiriman() + barangPermintaan.getKuantitasPengiriman();
                accumulatedListBarangPermintaan.get(sku).setKuantitasPengiriman(newKuantitas);
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

    @Override
    public List<PermintaanPengiriman> filterPermintaanPengiriman(String sku, LocalDate startDate, LocalDate endDate) {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.withDayOfMonth(endDate.getDayOfMonth() + 1).atStartOfDay();
        return permintaanPengirimanDB.findPermintaanPengirimanByWaktuPengirimanRangeAndBarang(sku, startDateTime, endDateTime);
    }

    @Override
    public PermintaanPengiriman cancelPermintaanPengiriman(Long idPermintaanPengiriman) throws ResponseStatusException {
        Optional<PermintaanPengiriman> permintaanPengirimanOpt = permintaanPengirimanDB.findById(idPermintaanPengiriman);
        if (permintaanPengirimanOpt.isPresent()) {
            PermintaanPengiriman permintaanPengiriman = permintaanPengirimanOpt.get();
            LocalDateTime limitCancel = permintaanPengiriman.getWaktuPermintaan().withDayOfMonth(
                    permintaanPengiriman.getWaktuPermintaan().getDayOfMonth() + 1
            );
            LocalDateTime now = LocalDateTime.now();
            if (permintaanPengiriman.getIsCancelled()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Permintaan pengiriman ini sudah di-cancel");
            }
            if (now.isAfter(limitCancel)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Permintaan pengiriman tidak dapat di-cancel karena telah melewati 24 jam");

            }
            permintaanPengiriman.setIsCancelled(true);
            permintaanPengirimanDB.save(permintaanPengiriman);
            return permintaanPengiriman;
        } else {
            return null;
        }
    }

}
