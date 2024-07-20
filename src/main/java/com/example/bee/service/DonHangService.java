package com.example.bee.service;



import com.example.bee.model.response.HoaDonResponse;

import java.util.List;

public interface DonHangService {

    List<HoaDonResponse> getAllHoaDonCuaTaiKhoan(Long idTaiKhoan, String trangThaiHoaDon);

    HoaDonResponse getOneDonHang(String maHoaDon);

    Long countSoHoaDon(Long taiKhoanId, String trangThaiHoaDon);

    void sendEmailDonHang(Long id);

}
