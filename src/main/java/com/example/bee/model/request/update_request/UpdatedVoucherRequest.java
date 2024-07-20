package com.example.bee.model.request.update_request;

import com.example.bee.common.CommonEnum;
import com.example.bee.entity.HinhThucGiamGia;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class UpdatedVoucherRequest {

    private Long id;

    private String ten;

    @Enumerated(EnumType.STRING)
    private CommonEnum.LoaiVoucher loaiVoucher;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ngayBatDau;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ngayKetThuc;

    private HinhThucGiamGia hinhThucGiam;

    private BigDecimal donToiThieu;

    private BigDecimal giaTriGiam;

    private BigDecimal giamToiDa;

    @Enumerated(EnumType.STRING)
    private CommonEnum.TrangThaiVoucher trangThai;

    private boolean cancelled;
}
