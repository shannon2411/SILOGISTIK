package apap.ti.silogistik2106751732.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "permintaan_pengiriman")
public class PermintaanPengiriman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT", nullable = false)
    private Long idPermintaanPengiriman;

    @Column(name = "nomor_pengiriman", length = 16, nullable = false, unique = true)
    private String nomorPengiriman;

    @Column(name = "is_cancelled", nullable = false)
    private Boolean isCancelled;

    @Column(name = "nama_penerima", nullable = false)
    private String namaPenerima;

    @Column(name = "alamat_penerima", nullable = false)
    private String alamatPenerima;

    @Column(name = "tanggal_pengiriman", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggalPengiriman;

    @Column(name = "biaya_pengiriman", nullable = false)
    private Integer biayaPengiriman;

    @Column(name = "jenis_layanan", nullable = false)
    private Integer jenisLayanan;

    @Column(name = "waktu_permintaan", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime waktuPermintaan;

    @OneToMany(mappedBy = "idPermintaanPengiriman", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PermintaanPengirimanBarang> listBarangPermintaan;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_karyawan", referencedColumnName = "idKaryawan", nullable = false)
    private Karyawan karyawan;
}
