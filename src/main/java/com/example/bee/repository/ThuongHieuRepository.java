package com.example.bee.repository;


import com.example.bee.common.CommonEnum;
import com.example.bee.entity.ThuongHieu;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ThuongHieuRepository extends JpaRepository<ThuongHieu, Long> {

    @Query("SELECT th FROM ThuongHieu th WHERE th.trangThai = 'ACTIVE' ORDER BY th.ngayTao DESC")
    List<ThuongHieu> getThuongHieuByNgayTaoDESC();

    @Query("SELECT obj FROM ThuongHieu obj WHERE (obj.ten LIKE %:searchText%) AND (:trangThai IS NULL OR obj.trangThai = :trangThai)")
    Page<ThuongHieu> findByAll(Pageable pageable, String searchText, CommonEnum.TrangThaiThuocTinh trangThai);

    @Query(value = "SELECT th.id FROM ThuongHieu th WHERE th.trangThai = 'ACTIVE' ")
    List<Long> findByIdIn();

    boolean existsByTen (String ten);

}
