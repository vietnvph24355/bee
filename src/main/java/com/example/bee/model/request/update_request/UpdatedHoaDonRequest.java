package com.example.bee.model.request.update_request;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.example.bee.common.CommonEnum;
import com.example.bee.entity.GiaoDich;
import com.example.bee.entity.HoaDonChiTiet;
import com.example.bee.entity.TaiKhoan;

import com.example.bee.entity.Voucher;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class UpdatedHoaDonRequest {

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
