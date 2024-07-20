package com.example.bee.model.request.create_request;


import com.example.bee.entity.TaiKhoan;
import com.example.bee.entity.Voucher;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CreatedVoucherChiTietRequest {

    private Integer soLanSuDung;

    private Voucher voucher;

    private TaiKhoan taiKhoan;


}
