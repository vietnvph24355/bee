package com.example.bee.repository;

import com.example.bee.common.CommonEnum;
import com.example.bee.entity.HinhThucGiamGia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HinhThucGiamGiaRepository extends JpaRepository<HinhThucGiamGia, Long> {

    @Query("SELECT obj FROM HinhThucGiamGia obj WHERE (obj.ten LIKE %:searchText%) AND (:trangThai IS NULL OR obj.trangThai = :trangThai)")
    Page<HinhThucGiamGia> findByAll(Pageable pageable, String searchText, CommonEnum.HinhThucGiam trangThai);

    boolean existsByTen ( String ten);

}

