package com.example.bee.model.response;


import com.example.bee.common.CommonEnum;
import com.example.bee.entity.HinhThucGiamGia;
import com.example.bee.entity.HoaDon;
import com.example.bee.entity.VoucherChiTiet;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class VoucherResponse {

    private Long id;

    private String ma;

    private String ten;

    @Enumerated(EnumType.STRING)
    private CommonEnum.LoaiVoucher loaiVoucher;

    private LocalDateTime ngayBatDau;

    private LocalDateTime ngayKetThuc;

    private HinhThucGiamGia hinhThucGiam;

    private BigDecimal giaTriGiam;

    private BigDecimal donToiThieu;

    private BigDecimal giamToiDa;

    private LocalDateTime ngayTao;

    private LocalDateTime ngaySua;

    private CommonEnum.TrangThaiVoucher trangThai;

    private List<VoucherChiTiet> voucherChiTietList;

    private  List<HoaDon> hoaDonList;

}
