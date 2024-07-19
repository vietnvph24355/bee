package com.example.bee.model.request.update_request;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatedMauSacRequest {

    @NotBlank(message = "Vui lòng điền mã màu")
    private String ma;

    @NotBlank(message = "Vui lòng điền tên màu")
    private String ten;

    private LocalDate ngayTao;

    private LocalDate ngaySua;

    private String trangThai;

}
