package com.example.bee.model.response;

import com.example.bee.common.CommonEnum;
import com.example.bee.entity.TaiKhoan;
import com.example.bee.entity.Voucher;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VoucherChiTietResponse {

    private Long id;
    private Integer soLanSuDung;
    private LocalDateTime ngayTao;
    private LocalDateTime ngaySua;
    private CommonEnum.TrangThaiThuocTinh trangThai;
    private Voucher voucher;
    private TaiKhoan taiKhoan;

}
