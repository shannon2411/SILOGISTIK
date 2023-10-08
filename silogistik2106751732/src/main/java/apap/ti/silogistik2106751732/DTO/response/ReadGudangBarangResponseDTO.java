package apap.ti.silogistik2106751732.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadGudangBarangResponseDTO {
    private Long id;
    private String skuBarang;
    private String merk;
    private Integer stok;
    private Long hargaBarang;
}
