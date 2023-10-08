package apap.ti.silogistik2106751732.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadGudangResponseDTO {
    private Long idGudang;
    private String namaGudang;
    private String alamatGudang;
    private List<ReadGudangBarangResponseDTO> listBarangDimuatGudang;
}
