package apap.ti.silogistik2106751732.service;

import apap.ti.silogistik2106751732.DTO.GudangMapper;
import apap.ti.silogistik2106751732.DTO.request.RestockBarangRequestDTO;
import apap.ti.silogistik2106751732.model.Gudang;
import apap.ti.silogistik2106751732.model.GudangBarang;
import apap.ti.silogistik2106751732.repository.GudangBarangDB;
import apap.ti.silogistik2106751732.repository.GudangDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GudangServiceImpl implements GudangService{

    @Autowired
    private GudangDB gudangDB;

    @Autowired
    private GudangBarangDB gudangBarangDB;

    @Autowired
    private GudangMapper gudangMapper;

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

    @Override
    public RestockBarangRequestDTO restockBarang(RestockBarangRequestDTO restockDto) {
        List<GudangBarang> listBarangDimuatGudang = restockDto.getListBarangDimuatGudang();
        Map<String, GudangBarang> accumulatedListBarangDimuatGudang = new HashMap<>();
//        listBarangDimuatGudang.get()
        for (GudangBarang gudangBarang : listBarangDimuatGudang) {
            String sku = gudangBarang.getSkuBarang().getSku();
            gudangBarang.setIdGudang(gudangMapper.restockBarangRequestDTOtoGudang(restockDto));
            if (accumulatedListBarangDimuatGudang.get(sku) == null) {
                accumulatedListBarangDimuatGudang.put(sku, gudangBarang);
            } else if (gudangBarang.getId() == null) {
                int newStok = accumulatedListBarangDimuatGudang.get(sku).getStok() + gudangBarang.getStok();
                accumulatedListBarangDimuatGudang.get(sku).setStok(newStok);
            } else {
                int newStok = accumulatedListBarangDimuatGudang.get(sku).getStok() + gudangBarang.getStok();
                accumulatedListBarangDimuatGudang.get(sku).setStok(newStok);
                gudangBarangDB.deleteById(gudangBarang.getId());
            }
        }
        List<GudangBarang> updatedListBarangDimuatGudang = accumulatedListBarangDimuatGudang.values().stream().toList();
        restockDto.setListBarangDimuatGudang(updatedListBarangDimuatGudang);
        return restockDto;
    };
}
