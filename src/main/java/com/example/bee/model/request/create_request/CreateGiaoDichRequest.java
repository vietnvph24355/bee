package com.example.bee.model.request.create_request;


import com.example.bee.common.CommonEnum;
import com.example.bee.entity.HoaDon;
import com.example.bee.entity.PhuongThucThanhToan;
import com.example.bee.entity.TaiKhoan;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateGiaoDichRequest {

    private Long id;

    private String maGiaoDich;

    private BigDecimal soTienGiaoDich;

    private LocalDateTime ngayThanhToan;

    private LocalDateTime ngayTao;

    private LocalDateTime ngaySua;

    @Enumerated(EnumType.STRING)
    private CommonEnum.TrangThaiGiaoDich trangThaiGiaoDich;

    private HoaDon hoaDon;

    private TaiKhoan taiKhoan;

    private PhuongThucThanhToan phuongThucThanhToan;


}
