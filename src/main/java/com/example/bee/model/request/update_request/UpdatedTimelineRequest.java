package com.example.bee.model.request.update_request;


import com.example.bee.common.CommonEnum;
import com.example.bee.entity.HoaDon;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class UpdatedTimelineRequest {

    private Long id;

    private String ghiChu;

    private String ma;

    private LocalDateTime ngayTao;

    private CommonEnum.TrangThaiHoaDon trangThai;

    private HoaDon hoaDon;


}
