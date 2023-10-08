package apap.ti.silogistik2106751732.DTO.request;

import apap.ti.silogistik2106751732.model.GudangBarang;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateGudangRequestDTO {

    private String namaGudang;

    private String alamatGudang;

    private List<GudangBarang> listBarangDimuatGudang;
}
