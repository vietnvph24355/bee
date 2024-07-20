package com.example.bee.model.request.update_request;

import com.example.bee.common.CommonEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class UpdatedHinhThucGiamGiaRequest {

    @NotBlank(message = "Vui lòng điền tên hình thức giảm giá")
    private String ten;

    private LocalDateTime ngayTao;

    private LocalDateTime ngaySua;

    private CommonEnum.HinhThucGiam trangThai;

}
