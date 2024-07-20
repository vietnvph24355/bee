package com.example.bee.service;

import com.example.bee.model.request.create_request.CreatedThuongHieuRequest;
import com.example.bee.model.request.update_request.UpdatedThuongHieuRequest;
import com.example.bee.model.response.ThuongHieuResponse;
import java.util.List;
import org.springframework.data.domain.Page;

public interface ThuongHieuService {

    List<ThuongHieuResponse> getThuongHieuByNgayTaoDESC();

    Page<ThuongHieuResponse> getAll(Integer page, Integer pageSize, String sortField, String sortOrder, String searchText, String trangThaiString);

    ThuongHieuResponse add(CreatedThuongHieuRequest request);

    void delete(Long id);

    ThuongHieuResponse update(Long id, UpdatedThuongHieuRequest request);

    ThuongHieuResponse findById(Long id);

}
