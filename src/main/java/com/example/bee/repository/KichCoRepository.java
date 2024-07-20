package com.example.bee.repository;


import com.example.bee.common.CommonEnum;
import com.example.bee.entity.KichCo;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KichCoRepository extends JpaRepository<KichCo, Long> {
    @Query("SELECT kc FROM KichCo kc WHERE kc.trangThai = 'ACTIVE' ORDER BY kc.kichCo ASC, kc.ngayTao DESC")
    List<KichCo> getKichCoByNgayTaoDESC();

    @Query("SELECT obj FROM KichCo obj WHERE (CAST(obj.kichCo AS string) LIKE %:searchText%) AND (:trangThai IS NULL OR obj.trangThai = :trangThai)")
    Page<KichCo> findByAll(Pageable pageable, String searchText, CommonEnum.TrangThaiThuocTinh trangThai);

    boolean existsByKichCo(Float kichCo);

    @Query("SELECT DISTINCT kc FROM ChiTietSanPham ctsp JOIN KichCo kc ON ctsp.kichCo.id = kc.id  WHERE ctsp.sanPham.id = :idSanPham AND kc.trangThai = 'ACTIVE'")
    List<KichCo> getKichCoKhongLap(Long idSanPham);

    @Query(value = "SELECT kc.id FROM KichCo kc WHERE kc.trangThai = 'ACTIVE' ")
    List<Long> findByIdIn();
}
