package com.example.bee.repository;


import com.example.bee.entity.HinhAnhSanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HinhAnhSanPhamRepository extends JpaRepository<HinhAnhSanPham, Long> {
//    @Query("SELECT ha FROM HinhAnhSanPham ha WHERE ha.sanPham.id = :idSanPham AND (ha.mauSac.id = :idMauSac OR :idMauSac IS NULL OR :idMauSac = '')")
//    List<HinhAnhSanPham> getAll(@Param("idSanPham") Long idSanPham, @Param("idMauSac") Long idMauSac);
//
    @Query("SELECT ha FROM HinhAnhSanPham ha WHERE ha.sanPham.id = :idSanPham AND " +
            "(CASE WHEN :idMauSac IS NULL THEN TRUE ELSE ha.mauSac.id = :idMauSac END)")
    List<HinhAnhSanPham> getAll(@Param("idSanPham") Long idSanPham, @Param("idMauSac") Long idMauSac);


}
