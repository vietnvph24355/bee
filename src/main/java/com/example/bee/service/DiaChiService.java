package com.example.bee.service;

import com.example.bee.entity.DiaChi;
import com.example.bee.model.request.create_request.CreatedDiaChiRequest;
import com.example.bee.model.request.update_request.UpdateDCReuest;
import com.example.bee.model.request.update_request.UpdatedDiaChiRequest;
import com.example.bee.model.response.DiaChiReponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DiaChiService {

    Page<DiaChiReponse> getAll(Integer page, Integer pageSize, String sortField, String sortOrder, String trangThaiDiaChi, String searchText, Long idKH);

    DiaChiReponse add(Long id, CreatedDiaChiRequest request);

    List<DiaChiReponse> findByListDiaChi(Long idTaiKhoan);

    DiaChiReponse getDiaChiDefaultByIDTaiKhoan(Long idtaiKhoan);

    DiaChiReponse findById(Long id);

    DiaChi update(Long id, UpdatedDiaChiRequest request);

    DiaChi updateTrangThai(Long id, UpdateDCReuest request);

    void delete(Long id);
}
