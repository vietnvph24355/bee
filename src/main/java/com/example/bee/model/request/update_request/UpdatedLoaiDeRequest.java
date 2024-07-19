package com.example.bee.model.request.update_request;

import com.example.bee.common.CommonEnum;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatedLoaiDeRequest {

    @NotBlank(message = "Vui lòng điền tên loại đế")
    private String ten;

    private LocalDate ngayTao;

    private LocalDate ngaySua;

    private CommonEnum.TrangThaiThuocTinh trangThai;

}
