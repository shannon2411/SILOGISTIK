package apap.ti.silogistik2106751732.repository;

import apap.ti.silogistik2106751732.model.PermintaanPengiriman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PermintaanPengirimanDB extends JpaRepository<PermintaanPengiriman, Long> {
//    @Query("SELECT t FROM TagihanModel t JOIN AppointmentModel a ON t.appointment.kode = a.kode" +
//            " JOIN ResepModel r ON a.resep.id = r.id" +
//            " JOIN JumlahObatResepModel jor ON jor.resep.id = r.id" +
//            " JOIN ObatModel o ON o.idObat = jor.obat.idObat" +
//            " WHERE o.idObat = :idObat")
//    List<PermintaanPengiriman> findPermintaanPengirimanByWaktuPengirimanRangeAndBarang(@Param("idObat") String idObat);
    @Query("SELECT pp FROM PermintaanPengiriman pp JOIN PermintaanPengirimanBarang ppb " +
            "ON ppb.idPermintaanPengiriman.idPermintaanPengiriman = pp.idPermintaanPengiriman" +
            " JOIN Barang b ON ppb.skuBarang.sku = b.sku" +
            " WHERE b.sku = :sku AND pp.waktuPermintaan BETWEEN :startDate AND :endDate")
    List<PermintaanPengiriman> findPermintaanPengirimanByWaktuPengirimanRangeAndBarang(@Param("sku") String sku,
                                                                                       @Param("startDate") LocalDateTime startDate,
                                                                                       @Param("endDate") LocalDateTime endDate);
}
