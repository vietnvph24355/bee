package com.example.bee.model.request.update_request;

import com.example.bee.common.CommonEnum;
import com.example.bee.entity.ThuongHieu;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatedSanPhamRequest {
    @NotBlank(message = "Vui lòng điền tên sản phẩm")
    private String ten;

    private String moTa;

    @NotNull(message = "Vui lòng chọn thương hiệu")
    private Long thuongHieuId;

    private CommonEnum.TrangThaiThuocTinh trangThai;

}
