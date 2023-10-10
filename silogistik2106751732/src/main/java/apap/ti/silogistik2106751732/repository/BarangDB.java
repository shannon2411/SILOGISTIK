package apap.ti.silogistik2106751732.repository;

import apap.ti.silogistik2106751732.model.Barang;
import apap.ti.silogistik2106751732.model.GudangBarang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarangDB extends JpaRepository<Barang, String> {
}
