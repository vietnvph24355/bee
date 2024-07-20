package com.example.bee.model.request.create_request;

import com.example.bee.common.CommonEnum;
import com.example.bee.entity.ThuongHieu;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreatedSanPhamRequest {
    private Long id;

    private String ma;

    @NotBlank(message = "Vui lòng điền tên sản phẩm")
    private String ten;

    private String moTa;

    private LocalDateTime ngayTao;

    private LocalDateTime ngaySua;

    @NotNull(message = "Vui lòng chọn thương hiệu")
    private ThuongHieu thuongHieu;

    private CommonEnum.TrangThaiSanPham trangThai;

}
