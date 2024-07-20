package com.example.bee.service;


import com.example.bee.model.request.create_request.CreatedHinhThucGiamGiaRequest;
import com.example.bee.model.request.update_request.UpdatedHinhThucGiamGiaRequest;
import com.example.bee.model.response.HinhThucGiamGiaResponse;
import org.springframework.data.domain.Page;

public interface HinhThucGiamGiaService {

    Page<HinhThucGiamGiaResponse> getAll(Integer page, Integer pageSize, String sortField, String sortOrder,
                                         String searchText, String trangThaiString);

    HinhThucGiamGiaResponse add(CreatedHinhThucGiamGiaRequest request);

    void delete(Long id);

    HinhThucGiamGiaResponse update(Long id, UpdatedHinhThucGiamGiaRequest request);

    HinhThucGiamGiaResponse findById(Long id);

}

