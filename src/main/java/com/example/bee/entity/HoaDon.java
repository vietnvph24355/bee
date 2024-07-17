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
@Entity
@Table(name = "hoa_don")
public class HoaDon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ma_hoa_don")
    private String ma;

    @Enumerated(EnumType.STRING)
    @Column(name = "loai_hoa_don")
    private CommonEnum.LoaiHoaDon loaiHoaDon;

    @Column(name = "ngay_thanh_toan")
    private LocalDateTime ngayThanhToan;

    @Column(name = "phi_ship")
    private BigDecimal phiShip;

    @Column(name = "tong_tien")
    private BigDecimal tongTien;

    @Column(name = "tong_tien_khi_giam")
    private BigDecimal tongTienKhiGiam;

    @Column(name = "giam_gia")
    private BigDecimal giamGia;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "nguoi_nhan")
    private String nguoiNhan;

    @Column(name = "sdt_nguoi_nhan")
    private String sdtNguoiNhan;

    @Column(name = "ngay_ship")
    private LocalDateTime ngayShip;

    @Column(name = "dia_chi_nguoi_nhan")
    private String diaChiNguoiNhan;

    @Column(name = "email_nguoi_nhan")
    private String emailNguoiNhan;

    @Column(name = "ngay_nhan")
    private LocalDateTime ngayNhan;

    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "ngay_tao", columnDefinition = "TIMESTAMP")
    private LocalDateTime ngayTao;

    @UpdateTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Column(name = "ngay_sua", columnDefinition = "TIMESTAMP")
    private LocalDateTime ngaySua;

    @Column(name = "nguoi_tao")
    private String nguoiTao;

    @Column(name = "nguoi_sua")
    private String nguoiSua;

    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai")
    private CommonEnum.TrangThaiHoaDon trangThaiHoaDon;

    @ManyToOne
    @JoinColumn(name = "voucher_id", referencedColumnName = "id")
    private Voucher voucher;

    @ManyToOne
    @JoinColumn(name = "tai_khoan_id", referencedColumnName = "id")
    private TaiKhoan taiKhoan;

    @JsonIgnore
    @OneToMany(mappedBy = "hoaDon",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<GiaoDich> giaoDichList;

    @JsonIgnore
    @OneToMany(mappedBy = "hoaDon",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<HoaDonChiTiet> hoaDonChiTietList;

    @JsonIgnore
    @OneToMany(mappedBy = "hoaDon",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<TimeLine> timeLineList;
}
