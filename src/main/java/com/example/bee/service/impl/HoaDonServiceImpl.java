package com.example.bee.service.impl;

import com.example.bee.common.CommonEnum;
import com.example.bee.model.request.create_request.CreateHoaDonRequest;
import com.example.bee.model.request.update_request.UpdatedHoaDonRequest;
import com.example.bee.model.response.HoaDonResponse;
import com.example.bee.service.HoaDonService;
import org.springframework.data.domain.Page;

import java.util.List;

public class HoaDonServiceImpl implements HoaDonService {
    @Override
    public Page<HoaDonResponse> getAll(Integer currentPage, Integer pageSize, String searchText, String sorter, String sortOrder, String loaiHoaDonString, String trangThaiHoaDonString) {
        return null;
    }

    @Override
    public List<HoaDonResponse> get7HoaDonPendingByDateNew() {
        return null;
    }

    @Override
    public HoaDonResponse add(CreateHoaDonRequest createHoaDonRequest) {
        return null;
    }

    @Override
    public HoaDonResponse findById(Long id) {
        return null;
    }

    @Override
    public HoaDonResponse update(Long id, UpdatedHoaDonRequest updatedHoaDonRequest) {
        return null;
    }

    @Override
    public HoaDonResponse updateHuyHoaDon(Long id, UpdatedHoaDonRequest updatedHoaDonRequest) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void updateTrangThaiHoaDon(Long idHoadon, CommonEnum.TrangThaiHoaDon trangThaiHoaDon, String ghiChu, Long idPhuongThucThanhToan) {

    }

    @Override
    public Long getSoLuongHoaDonCho() {
        return null;
    }

    @Override
    public HoaDonResponse cancelHoaDon(Long id, String ghiChuTimeLine) {
        return null;
    }
}
