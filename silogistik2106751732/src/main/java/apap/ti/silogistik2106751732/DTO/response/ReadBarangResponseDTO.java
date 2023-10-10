package apap.ti.silogistik2106751732.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadBarangResponseDTO {
    private String sku;
    private Integer tipeBarang;
    private String merk;
    private Long hargaBarang;
    private Integer totalStok;
    private List<ReadBarangGudangResponseDTO> listGudangMemuatBarang;
}
