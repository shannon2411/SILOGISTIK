package apap.ti.silogistik2106751732.repository;

import apap.ti.silogistik2106751732.model.Karyawan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KaryawanDB extends JpaRepository<Karyawan, Long> {
}
