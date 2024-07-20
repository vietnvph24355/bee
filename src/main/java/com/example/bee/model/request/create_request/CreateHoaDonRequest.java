package com.example.bee.model.request.create_request;


import com.example.bee.common.CommonEnum;
import com.example.bee.entity.GiaoDich;
import com.example.bee.entity.HoaDonChiTiet;
import com.example.bee.entity.TaiKhoan;
import com.example.bee.entity.Voucher;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateHoaDonRequest {

    private Long id;

    private String ma;

    @Enumerated(EnumType.STRING)
    private CommonEnum.LoaiHoaDon loaiHoaDon;

    private LocalDateTime ngayThanhToan;

    private BigDecimal phiShip;

    private BigDecimal tongTien;

    private BigDecimal tongTienKhiGiam;

    private BigDecimal giamGia;

    private String ghiChu;

    private String nguoiNhan;

    private String sdtNguoiNhan;

    private LocalDateTime ngayShip;

    private String diaChiNguoiNhan;

    private String emailNguoiNhan;

    private LocalDateTime ngayNhan;

    private LocalDateTime ngayMongMuon;

    private LocalDateTime ngayTao;

    private LocalDateTime ngaySua;

    private String nguoiTao;

    private String nguoiSua;

    @Enumerated(EnumType.STRING)
    private CommonEnum.TrangThaiHoaDon trangThaiHoaDon;

    private Voucher voucher;

    private TaiKhoan taiKhoan;

    private List<GiaoDich> giaoDichList;

    private List<HoaDonChiTiet> hoaDonChiTietList;

    private String ghiChuTimeLine;

    private Long idPhuongThuc;

}
