package apap.ti.silogistik2106751732.service;

import apap.ti.silogistik2106751732.model.Gudang;

import java.util.List;

public interface GudangService {
    void saveGudang(Gudang gudang);

    List<Gudang> getAllGudang();

    Gudang getGudangById(Long id);
}
