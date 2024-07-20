package com.example.bee.service.impl;

import com.example.bee.model.request.create_request.CreateHoaDonChiTietRequest;
import com.example.bee.model.request.update_request.UpdatedHoaDonChiTietRequest;
import com.example.bee.model.response.HoaDonChiTietResponse;
import com.example.bee.service.HoaDonChiTietService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HoaDonChiTietServiceImpl implements HoaDonChiTietService {
    @Override
    public Page<HoaDonChiTietResponse> getAll(Integer currentPage, Integer pageSize, String searchText, String sorter, String sortOrder) {
        return null;
    }

    @Override
    public HoaDonChiTietResponse add(CreateHoaDonChiTietRequest createHoaDonChiTietRequest, Long id) {
        return null;
    }

    @Override
    public void addList(List<CreateHoaDonChiTietRequest> requestList) {

    }

    @Override
    public void updateList(List<UpdatedHoaDonChiTietRequest> requestList) {

    }

    @Override
    public HoaDonChiTietResponse findById(Long id) {
        return null;
    }

    @Override
    public HoaDonChiTietResponse update(Long id, UpdatedHoaDonChiTietRequest updatedHoaDonChiTietRequest) {
        return null;
    }

    @Override
    public HoaDonChiTietResponse updateSoLuong(Long id, Integer soLuong) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void updateHoaDonChiTiet(List<UpdatedHoaDonChiTietRequest> updatedHoaDonChiTietRequests) {

    }

    @Override
    public List<HoaDonChiTietResponse> findByHoaDonId(Long id) {
        return null;
    }

    @Override
    public Page<HoaDonChiTietResponse> getPageAllByIdHoaDon(Integer currentPage, Integer pageSize, String searchText, String sorter, String sortOrder, Long id) {
        return null;
    }
}
