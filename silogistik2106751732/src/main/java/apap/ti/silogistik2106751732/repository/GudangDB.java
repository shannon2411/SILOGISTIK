package apap.ti.silogistik2106751732.repository;

import apap.ti.silogistik2106751732.model.Gudang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GudangDB extends JpaRepository<Gudang, Long> {
}
