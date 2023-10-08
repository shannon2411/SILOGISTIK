package apap.ti.silogistik2106751732.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gudang")
public class Gudang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gudang", columnDefinition = "BIGINT", nullable = false)
    private Long idGudang;

    @Column(name = "nama_gudang", length = 255, nullable = false)
    private String namaGudang;

    @Column(name = "alamat_gudang", length = 255, nullable = false)
    private String alamatGudang;

    @OneToMany(mappedBy = "idGudang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<GudangBarang> listBarangDimuatGudang;
}
