package com.example.bee.repository;

import com.example.bee.common.CommonEnum;
import com.example.bee.entity.TimeLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimelineRepository extends JpaRepository<TimeLine, Long> {

    @Query("SELECT t FROM TimeLine t WHERE t.hoaDon.id = :idHoaDon ORDER BY t.ngayTao ASC ")
    List<TimeLine> findTimeLinesByHoaDonId(@Param("idHoaDon") Long id);

    Boolean existsTimeLineByTrangThaiAndHoaDonId(CommonEnum.TrangThaiHoaDon trangThaiHoaDon, Long id);

    TimeLine findTimeLinesByHoaDonIdAndAndTrangThai(Long idHoaDon, CommonEnum.TrangThaiHoaDon trangThaiHoaDon);

}
