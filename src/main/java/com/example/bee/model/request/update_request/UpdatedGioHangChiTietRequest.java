package com.example.bee.model.request.update_request;

import com.example.bee.entity.ChiTietSanPham;
import com.example.bee.entity.GioHang;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdatedGioHangChiTietRequest {
    private Long id;

    private Integer soLuong;

    private String ghiChu;

    private LocalDateTime ngayTao;

    private LocalDateTime ngaySua;

    private Integer trangThai;

    private GioHang gioHang;

    private ChiTietSanPham chiTietSanPham;
}
