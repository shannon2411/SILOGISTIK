package apap.ti.silogistik2106751732.model;

import apap.ti.silogistik2106751732.model.idgenerator.SkuGenerator;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "barang")
public class Barang {
    @Id
    @Column(name = "sku", length = 7, nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sku")
    @GenericGenerator(name = "sku", strategy = "apap.ti.silogistik2106751732.model.idgenerator.SkuGenerator")
    private String sku;

    @Column(name = "tipe_barang", nullable = false)
    private Integer tipeBarang;

    @Column(name = "merk", nullable = false)
    private String merk;

    @Column(name = "harga_barang", columnDefinition = "BIGINT", nullable = false)
    private Long hargaBarang;

    @OneToMany(mappedBy = "skuBarang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<GudangBarang> listGudangMemuatBarang;

    @OneToMany(mappedBy = "skuBarang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PermintaanPengirimanBarang> listPermintaanBarang;

}
