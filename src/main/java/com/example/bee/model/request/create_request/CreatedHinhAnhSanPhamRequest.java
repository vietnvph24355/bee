package com.example.bee.model.request.create_request;


import com.example.bee.common.CommonEnum;
import com.example.bee.entity.MauSac;
import com.example.bee.entity.SanPham;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreatedHinhAnhSanPhamRequest {
    private Long id;

    private String duongDan;

    private LocalDateTime ngayTao;

    private LocalDateTime ngaySua;

    private CommonEnum.TrangThaiHinhAnh trangThai;

    private SanPham sanPham;

    private MauSac mauSac;

}
