package com.example.bee.service;


import com.example.bee.common.CommonEnum;
import com.example.bee.model.request.create_request.CreateHoaDonRequest;
import com.example.bee.model.request.update_request.UpdatedHoaDonRequest;
import com.example.bee.model.response.HoaDonResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HoaDonService {

    Page<HoaDonResponse> getAll(Integer currentPage, Integer pageSize, String searchText, String sorter, String sortOrder, String loaiHoaDonString, String trangThaiHoaDonString);

    List<HoaDonResponse> get7HoaDonPendingByDateNew();

    HoaDonResponse add(CreateHoaDonRequest createHoaDonRequest);

    HoaDonResponse findById(Long id);

    HoaDonResponse update(Long id, UpdatedHoaDonRequest updatedHoaDonRequest);

    HoaDonResponse updateHuyHoaDon(Long id, UpdatedHoaDonRequest updatedHoaDonRequest);

    void delete(Long id);

    void updateTrangThaiHoaDon(Long idHoadon, CommonEnum.TrangThaiHoaDon trangThaiHoaDon, String ghiChu, Long idPhuongThucThanhToan);

    Long getSoLuongHoaDonCho();

    HoaDonResponse cancelHoaDon(Long id, String ghiChuTimeLine);

}
