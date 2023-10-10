package apap.ti.silogistik2106751732.DTO.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateBarangRequestDTO {
    @NotBlank(message = "Merk tidak boleh kosong")
    private String merk;
    @NotBlank(message = "Tipe barang tidak boleh kosong")
    private Integer tipeBarang;
    @NotBlank(message = "Harga barang tidak boleh kosong")
    private Long hargaBarang;
}
