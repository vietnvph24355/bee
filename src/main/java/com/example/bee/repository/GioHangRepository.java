package com.example.bee.repository;

import com.example.bee.entity.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GioHangRepository extends JpaRepository<GioHang, Long> {
    @Query("SELECT gh FROM GioHang gh where gh.taiKhoan.id = :taiKhoanId")
    GioHang getOne(Long taiKhoanId);
}
