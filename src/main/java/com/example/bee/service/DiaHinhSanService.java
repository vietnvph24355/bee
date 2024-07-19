package com.example.bee.service;

import com.example.bee.model.request.create_request.CreatedDiaHinhSanRequest;
import com.example.bee.model.request.update_request.UpdatedDiaHinhSanRequest;
import com.example.bee.model.response.DiaHinhSanResponse;
import java.util.List;
import org.springframework.data.domain.Page;

public interface DiaHinhSanService {
    List<DiaHinhSanResponse> listDiaHinhSan();

    Page<DiaHinhSanResponse> getAll(Integer page, Integer pageSize, String sortField, String sortOrder, String searchText, String trangThaiString);

    DiaHinhSanResponse add(CreatedDiaHinhSanRequest request);

    DiaHinhSanResponse update(Long id, UpdatedDiaHinhSanRequest request);

    void delete(Long id);

    DiaHinhSanResponse findById(Long id);

    List<DiaHinhSanResponse> getDiaHinhSanKhongLap(Long idSanPham);
}
