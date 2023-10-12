package apap.ti.silogistik2106751732.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HomeResponseDTO {
    private Long jumlahGudang;
    private Long jumlahBarang;
    private Long jumlahPermintaanPengiriman;
    private Long jumlahKaryawan;
}
