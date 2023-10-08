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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "permintaan_pengiriman_barang")
public class PermintaanPengirimanBarang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_permintaan_pengiriman", referencedColumnName = "idPermintaanPengiriman", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PermintaanPengiriman idPermintaanPengiriman;

    @ManyToOne
    @JoinColumn(name = "sku_barang", referencedColumnName = "SKU", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Barang skuBarang;

    @Column(name = "kuantitas_pengiriman", nullable = false)
    private Integer kuantitasPengiriman;
}
