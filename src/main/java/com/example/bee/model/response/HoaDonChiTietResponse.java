package com.example.bee.model.response;


import com.example.bee.common.CommonEnum;
import com.example.bee.entity.ChiTietSanPham;
import com.example.bee.entity.HoaDon;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class HoaDonChiTietResponse {

    private Long id;

    private Integer soLuong;

    private BigDecimal donGia;

    private String ghiChu;

    private LocalDateTime ngayTao;

    private LocalDateTime ngaySua;

    private String nguoiTao;

    private String nguoiSua;

    private CommonEnum.TrangThaiHoaDonChiTiet trangThaiHoaDonChiTiet;

    private HoaDon hoaDon;

    private ChiTietSanPham chiTietSanPham;


}
