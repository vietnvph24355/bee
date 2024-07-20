package com.example.bee.model.request.create_request;


import com.example.bee.common.CommonEnum;
import com.example.bee.entity.HoaDon;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CreatedTimelineRequest {

    private Long id;

    private String ghiChu;

    private String ma;

    private LocalDateTime ngayTao;

    private CommonEnum.TrangThaiHoaDon trangThai;

    private HoaDon hoaDon;


}
