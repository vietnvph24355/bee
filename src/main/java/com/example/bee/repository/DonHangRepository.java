package com.example.bee.repository;

import com.example.bee.common.CommonEnum;
import com.example.bee.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonHangRepository extends JpaRepository<HoaDon, Long> {
    @Query("SELECT h FROM HoaDon h JOIN h.taiKhoan t WHERE t.id = :taiKhoanId AND (h.trangThaiHoaDon = :trangThai OR :trangThai IS NULL) AND h.loaiHoaDon = 'ONLINE' ORDER BY h.ngaySua DESC")
    List<HoaDon> getAllHoaDonCuaTaiKhoan(@Param("taiKhoanId") Long id, @Param("trangThai") CommonEnum.TrangThaiHoaDon trangThaiHoaDon);

    @Query("SELECT h FROM HoaDon h WHERE h.ma = :ma")
    HoaDon getOneDonHang(@Param("ma") String maHoaDon);

    @Query("SELECT COUNT(h) FROM HoaDon h WHERE h.taiKhoan.id = :taiKhoanId AND (h.trangThaiHoaDon = :trangThai OR :trangThai IS NULL)  AND h.loaiHoaDon = 'ONLINE'")
    Long countSoHoaDon(@Param("taiKhoanId") Long taiKhoanId, @Param("trangThai") CommonEnum.TrangThaiHoaDon trangThaiHoaDon);

}
