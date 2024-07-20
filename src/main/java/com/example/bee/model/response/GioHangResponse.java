package com.example.bee.model.response;

import com.example.bee.entity.GioHangChiTiet;
import com.example.bee.entity.TaiKhoan;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class GioHangResponse {
    private Long id;

    private String maGioHang;

    private String ghiChu;

    private LocalDateTime ngayTao;

    private LocalDateTime ngaySua;

    private Integer trangThai;

    private TaiKhoan taiKhoan;

    private List<GioHangChiTiet> gioHangChiTietList;
}
