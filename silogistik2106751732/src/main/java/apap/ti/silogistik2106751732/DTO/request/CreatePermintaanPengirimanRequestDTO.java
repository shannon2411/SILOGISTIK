package apap.ti.silogistik2106751732.DTO.request;

import apap.ti.silogistik2106751732.model.Karyawan;
import apap.ti.silogistik2106751732.model.PermintaanPengirimanBarang;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreatePermintaanPengirimanRequestDTO {
    private String namaPenerima;
    private String alamatPenerima;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggalPengiriman;
    private Integer biayaPengiriman;
    private Integer jenisLayanan;
    private List<PermintaanPengirimanBarang> listBarangPermintaan;
    private Karyawan karyawan;
}
