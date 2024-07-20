package com.example.bee.repository;

import com.example.bee.common.CommonEnum;
import com.example.bee.entity.MauSac;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MauSacRepository extends JpaRepository<MauSac, Long> {
    @Query("SELECT ms FROM MauSac ms WHERE ms.trangThai = 'ACTIVE' ORDER BY ms.ngayTao DESC")
    List<MauSac> getMauSacByNgayTaoDESC();

    @Query("SELECT obj FROM MauSac obj WHERE (obj.ma LIKE %:searchText% OR obj.ten LIKE %:searchText%) AND (:trangThai IS NULL OR obj.trangThai = :trangThai)")
    Page<MauSac> findByAll(Pageable pageable, String searchText, CommonEnum.TrangThaiThuocTinh trangThai);

    boolean existsByMa (String ma);

    boolean existsByTen ( String ten);

    @Query(value = "SELECT ms.id FROM MauSac ms WHERE ms.trangThai = 'ACTIVE' ")
    List<Long> findByIdIn();

    @Query("SELECT DISTINCT ms FROM ChiTietSanPham ctsp JOIN MauSac ms ON ctsp.mauSac.id = ms.id  WHERE ctsp.sanPham.id = :idSanPham AND ms.trangThai = 'ACTIVE'")
    List<MauSac> getMauSacKhongLap(Long idSanPham);

}
