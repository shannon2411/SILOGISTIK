package apap.ti.silogistik2106751732.DTO.request;

import apap.ti.silogistik2106751732.model.GudangBarang;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FilterPermintaanPengirimanRequestDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    private String sku;
}
