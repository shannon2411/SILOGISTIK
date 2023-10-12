package apap.ti.silogistik2106751732.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HomeResponseDTO {
    private Integer jumlahGudang;
    private Integer jumlahBarang;
    private Integer jumlahPermintaanPengiriman;
    private Integer jumlahKaryawan;
}
