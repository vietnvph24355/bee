package com.example.bee.model.request.create_request;

import com.example.bee.common.CommonEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CreatedVaiTroRequest {
    private String ten;

    private LocalDateTime ngayTao;

    private LocalDateTime ngaySua;

    private CommonEnum.TrangThaiThuocTinh trangThai;
}
