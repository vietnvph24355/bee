package com.example.bee.repository;


import com.example.bee.common.CommonEnum;
import com.example.bee.entity.PhuongThucThanhToan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PhuongThucThanhToanRepository extends JpaRepository<PhuongThucThanhToan, Long> {

    @Query("SELECT obj FROM PhuongThucThanhToan obj WHERE (obj.ten LIKE %:searchText%) AND (:trangThai IS NULL OR obj.trangThai = :trangThai)")
    Page<PhuongThucThanhToan> findByAll(Pageable pageable, String searchText, CommonEnum.TrangThaiThuocTinh trangThai);

    PhuongThucThanhToan findPhuongThucThanhToanById(Long id);

}

