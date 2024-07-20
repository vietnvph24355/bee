package com.example.bee.model.response;

import com.example.bee.common.CommonEnum;
import com.example.bee.entity.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ChiTietSanPhamResponse {
    private Long id;

    private Integer soLuong;

    private BigDecimal giaTien;

    private LocalDateTime ngayTao;

    private LocalDateTime ngaySua;

    private String nguoiTao;

    private String nguoiSua;

    private CommonEnum.TrangThaiChiTietSanPham trangThai;

    private LoaiDe loaiDe;

    private DiaHinhSan diaHinhSan;

    private SanPham sanPham;

    private MauSac mauSac;

    private KichCo kichCo;

}
