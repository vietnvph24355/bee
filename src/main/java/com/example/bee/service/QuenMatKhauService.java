package com.example.bee.service;

import com.example.poly.datn.entity.TaiKhoan;
import com.example.poly.datn.model.request.QuenMatKhauRequest.QuenMatKhauRequest;

public interface QuenMatKhauService {
    void sendEmail(TaiKhoan taiKhoan);

    TaiKhoan oldPassword(QuenMatKhauRequest request);

}
