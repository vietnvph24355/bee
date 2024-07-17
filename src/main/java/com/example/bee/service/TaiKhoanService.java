package com.example.bee.service;

import com.example.bee.entity.TaiKhoan;
import com.example.bee.model.dto.PasswordRequest;
import com.example.bee.model.request.create_request.CreatedTaiKhoanRequest;
import com.example.bee.model.request.update_request.UpdatedTaiKhoanRequest;
import com.example.bee.model.response.TaiKhoanResponse;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface TaiKhoanService {
    Page<TaiKhoanResponse> getAll(Integer page, Integer pageSize, String sortField, String sortOrder, String gioiTinhString, String searchText, String trangThaiString);

    List<TaiKhoan> getAllKhachHang1();

    Page<TaiKhoanResponse> getAllKhachHang(Integer page, Integer pageSize, String sortField, String sortOrder,String gioiTinhString, String searchText, String trangThaiString);

    TaiKhoanResponse add(CreatedTaiKhoanRequest request);

    TaiKhoanResponse update(Long id, UpdatedTaiKhoanRequest request);

    TaiKhoanResponse updateKhachHang(Long id, UpdatedTaiKhoanRequest request);

    void  delete(Long id);

    TaiKhoanResponse findById(Long id);

    TaiKhoanResponse addKhachHang(CreatedTaiKhoanRequest request);

    TaiKhoan getAllTaiKhoan(String email);

    String changePassword(PasswordRequest passwordRequest);

    byte[] exportExcelTaiKhoan() throws IOException;

    byte[] exportExcelTaiKhoan1() throws IOException;
    List<TaiKhoan> getAllTaiKhoan();
}
