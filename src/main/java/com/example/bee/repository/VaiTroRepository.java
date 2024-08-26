package com.example.bee.repository;

import com.example.bee.common.CommonEnum;
import com.example.bee.entity.VaiTro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface VaiTroRepository extends JpaRepository<VaiTro, Long> {
    @Query("SELECT obj FROM VaiTro obj WHERE (obj.ten LIKE %:searchText%) AND (:trangThai IS NULL OR obj.trangThai = :trangThai)")
    Page<VaiTro> findByAll(Pageable pageable, String searchText, CommonEnum.TrangThaiThuocTinh trangThai);

    VaiTro findByTen(String ten);

    @Query("SELECT vc FROM VaiTro vc WHERE vc.id =:id")
    VaiTro findId(@Param("id") Long id);

    Optional<VaiTro> findVaiTroById(Long id);
}
