package com.example.bee.service;

import com.example.bee.entity.HinhAnhSanPham;
import com.example.bee.model.request.create_request.CreatedHinhAnhSanPhamRequest;
import com.example.bee.model.request.update_request.UpdatedHinhAnhSanPhamRequest;
import com.example.bee.model.response.HinhAnhSanPhamResponse;

import java.util.List;

public interface HinhAnhSanPhamService {
    List<HinhAnhSanPhamResponse> getAll(Long idSanPham, Long idMauSac);

    List<HinhAnhSanPhamResponse> add(List<CreatedHinhAnhSanPhamRequest> request);

    void delete(List<HinhAnhSanPham> request);

    HinhAnhSanPham update(List<UpdatedHinhAnhSanPhamRequest> request);

}
