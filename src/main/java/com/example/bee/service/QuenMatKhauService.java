package com.example.bee.service;

import com.example.bee.entity.TaiKhoan;
import com.example.bee.model.request.QuenMatKhauRequest.QuenMatKhauRequest;

public interface QuenMatKhauService {
    void sendEmail(TaiKhoan taiKhoan);

    TaiKhoan oldPassword(QuenMatKhauRequest request);

}
