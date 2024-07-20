package com.example.bee.repository;


import com.example.bee.entity.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, Long> {
    List<GioHangChiTiet> findGioHangChiTietsByGioHangId(Long id);

    @Query("SELECT ghct FROM GioHangChiTiet ghct WHERE ghct.chiTietSanPham.id = :idChiTietSanPham AND (ghct.gioHang.id = :idGioHang)")
    GioHangChiTiet existGioHangChiTiet(Long idChiTietSanPham, Long idGioHang);

}
