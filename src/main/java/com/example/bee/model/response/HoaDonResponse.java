package com.example.bee.model.response;

import com.example.bee.common.CommonEnum;
import com.example.bee.entity.GiaoDich;
import com.example.bee.entity.HoaDonChiTiet;
import com.example.bee.entity.TaiKhoan;
import com.example.bee.entity.TimeLine;
import com.example.bee.entity.Voucher;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class HoaDonResponse {

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

    private List<TimeLine> timeLineList;

}
