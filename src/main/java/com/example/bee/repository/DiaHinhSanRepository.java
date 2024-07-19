package com.example.bee.repository;


import com.example.bee.common.CommonEnum;
import com.example.bee.entity.DiaHinhSan;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaHinhSanRepository extends JpaRepository<DiaHinhSan, Long> {
    @Query("SELECT dhs FROM DiaHinhSan dhs WHERE dhs.trangThai = 'ACTIVE' ORDER BY dhs.ngayTao DESC")
    List<DiaHinhSan> getDiaHinhSanByNgayTaoDESC();

    @Query("SELECT obj FROM DiaHinhSan obj WHERE (obj.ten LIKE %:searchText%) AND (:trangThai IS NULL OR obj.trangThai = :trangThai)")
    Page<DiaHinhSan> findByAll(Pageable pageable, String searchText, CommonEnum.TrangThaiThuocTinh trangThai);

    boolean existsByTen(String ten);

    @Query("SELECT DISTINCT dhs FROM ChiTietSanPham ctsp JOIN DiaHinhSan dhs ON ctsp.diaHinhSan.id = dhs.id  WHERE ctsp.sanPham.id = :idSanPham AND dhs.trangThai = 'ACTIVE'")
    List<DiaHinhSan> getDiaHinhSanKhongLap(Long idSanPham);

    @Query(value = "SELECT dhs.id FROM DiaHinhSan dhs WHERE dhs.trangThai = 'ACTIVE' ")
    List<Long> findByIdIn();
}
