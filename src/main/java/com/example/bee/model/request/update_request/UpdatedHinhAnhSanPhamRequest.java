package com.example.bee.model.request.update_request;

import com.example.bee.common.CommonEnum;
import com.example.bee.entity.MauSac;
import com.example.bee.entity.SanPham;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatedHinhAnhSanPhamRequest {
    private Long id;

    private String duongDan;

    private CommonEnum.TrangThaiHinhAnh trangThai;

    private SanPham sanPham;

    private MauSac mauSac;
}
