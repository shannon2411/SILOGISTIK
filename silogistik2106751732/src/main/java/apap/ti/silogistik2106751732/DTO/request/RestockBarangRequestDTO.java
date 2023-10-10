package apap.ti.silogistik2106751732.DTO.request;
import apap.ti.silogistik2106751732.model.GudangBarang;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestockBarangRequestDTO {
    @NotBlank(message = "Id gudang tidak boleh kosong")
    private Long idGudang ;
    @NotBlank(message = "Nama gudang tidak boleh kosong")
    private String namaGudang;
    @NotBlank(message = "Alamat gudang tidak boleh kosong")
    private String alamatGudang;

    private List<GudangBarang> listBarangDimuatGudang;
}