package com.example.bee.model.request.update_request;

import com.example.bee.common.CommonEnum;
import com.example.bee.entity.TaiKhoan;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdatedDiaChiRequest {
    private String hoVaTen;

    private String soDienThoai;

    private String email;

    private String thanhPho;

    private String quanHuyen;

    private String phuongXa;

    private String diaChiCuThe;

    private String diaChi;


    private CommonEnum.TrangThaiDiaChi trangThaiDiaChi;

    private TaiKhoan taiKhoan;
}
