package apap.ti.silogistik2106751732.repository;

import apap.ti.silogistik2106751732.model.PermintaanPengiriman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermintaanPengirimanDB extends JpaRepository<PermintaanPengiriman, Long> {
}
