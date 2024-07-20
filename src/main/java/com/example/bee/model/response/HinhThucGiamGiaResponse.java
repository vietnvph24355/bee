package com.example.bee.model.response;

import com.example.bee.common.CommonEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class HinhThucGiamGiaResponse {

    private Long id;

    private String ten;

    private LocalDateTime ngayTao;

    private LocalDateTime ngaySua;

    private CommonEnum.HinhThucGiam trangThai;

}
