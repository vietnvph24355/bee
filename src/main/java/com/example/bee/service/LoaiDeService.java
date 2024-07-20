package com.example.bee.service;

import com.example.bee.model.request.create_request.CreatedLoaiDeRequest;
import com.example.bee.model.request.update_request.UpdatedLoaiDeRequest;
import com.example.bee.model.response.LoaiDeResponse;
import java.util.List;
import org.springframework.data.domain.Page;

public interface LoaiDeService {
    List<LoaiDeResponse> getLoaiDeByNgayTaoDESC();

    Page<LoaiDeResponse> getAll(Integer page, Integer pageSize, String sortField, String sortOrder, String searchText, String trangThaiString);

    LoaiDeResponse add(CreatedLoaiDeRequest request);

    LoaiDeResponse update(Long id, UpdatedLoaiDeRequest request);

    void delete(Long id);

    LoaiDeResponse findById(Long id);

    List<LoaiDeResponse> getLoaiDeKhongLap(Long idSanPham);
}
