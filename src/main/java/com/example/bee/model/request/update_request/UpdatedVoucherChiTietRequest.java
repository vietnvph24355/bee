package com.example.bee.model.request.update_request;


import com.example.bee.common.CommonEnum;
import com.example.bee.entity.TaiKhoan;
import com.example.bee.entity.Voucher;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatedVoucherChiTietRequest {

    private Long id;

    private Integer SoLanSuDung;

    private CommonEnum.TrangThaiThuocTinh trangThai;

    private Voucher voucher;

    private TaiKhoan taiKhoan;

}
