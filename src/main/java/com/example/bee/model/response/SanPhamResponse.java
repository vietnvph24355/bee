package com.example.bee.model.response;

import com.example.bee.common.CommonEnum;
import com.example.bee.entity.ChiTietSanPham;
import com.example.bee.entity.HinhAnhSanPham;
import com.example.bee.entity.ThuongHieu;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class SanPhamResponse {
    private Long id;

    private String ma;

    private String ten;

    private String moTa;

    private LocalDateTime ngayTao;

    private LocalDateTime ngaySua;

    private CommonEnum.TrangThaiSanPham trangThai;

    private ThuongHieu thuongHieu;

    private List<ChiTietSanPham> listChiTietSanPham;

    private List<HinhAnhSanPham> listHinhAnhSanPham;

}
