package com.example.bee.model.response;


import com.example.bee.common.CommonEnum;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KichCoResponse {

    private Long id;

    private Float kichCo;

    private LocalDateTime ngayTao;

    private LocalDateTime ngaySua;

    private CommonEnum.TrangThaiThuocTinh trangThai;

}
