package apap.ti.silogistik2106751732.DTO.request;

import apap.ti.silogistik2106751732.model.PermintaanPengiriman;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateKaryawanRequestDTO {
    private String nama;

    private Integer jenisKelamin;

    private Date tanggalLahir;

    private List<PermintaanPengiriman> listPermintaanPengiriman;
}
