package apap.ti.silogistik2106751732.repository;

import apap.ti.silogistik2106751732.model.GudangBarang;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface GudangBarangDB extends JpaRepository<GudangBarang, Long> {
}
