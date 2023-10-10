package apap.ti.silogistik2106751732.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadBarangGudangResponseDTO {
    private Long idGudang;
    private String namaGudang;
    private String alamatGudang;
    private Integer stok;
}
