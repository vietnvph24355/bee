package com.example.bee.entity;

import com.example.bee.common.CommonEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "voucher")
@Entity
public class Voucher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ten")
    private String ten;

    @Column(name = "loai_voucher")
    @Enumerated(EnumType.STRING)
    private CommonEnum.LoaiVoucher loaiVoucher;

    @Column(name = "ngay_bat_dau",columnDefinition = "TIMESTAMP")
    private LocalDateTime ngayBatDau;

    @Column(name = "ngay_ket_thuc",columnDefinition = "TIMESTAMP")
    private LocalDateTime ngayKetThuc;

    @ManyToOne
    @JoinColumn(name = "hinh_thuc_giam_gia_id", referencedColumnName = "id")
    private HinhThucGiamGia hinhThucGiamGia;

    @Column(name = "gia_tri_giam")
    private BigDecimal giaTriGiam;

    @Column(name = "don_toi_thieu")
    private BigDecimal donToiThieu;

    @Column(name = "giam_toi_da")
    private BigDecimal giamToiDa;

    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "ngay_tao", columnDefinition = "TIMESTAMP", updatable = false)
    private LocalDateTime ngayTao;

    @UpdateTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Column(name = "ngay_sua", columnDefinition = "TIMESTAMP")
    private LocalDateTime ngaySua;

    @Column(name = "trang_thai")
    @Enumerated(EnumType.STRING)
    private CommonEnum.TrangThaiVoucher trangThai;

    @JsonIgnore
    @OneToMany(mappedBy = "voucher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HoaDon> hoaDonList;

    @JsonIgnore
    @OneToMany(mappedBy = "voucher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VoucherChiTiet> voucherChiTietList;
}
