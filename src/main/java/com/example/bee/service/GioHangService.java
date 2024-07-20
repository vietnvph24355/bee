package com.example.bee.service;

import com.example.bee.model.request.create_request.CreatedGioHangRequest;
import com.example.bee.model.request.update_request.UpdatedGioHangRequest;
import com.example.bee.model.response.GioHangResponse;

public interface GioHangService {
    GioHangResponse create(CreatedGioHangRequest request);

    GioHangResponse findByIdTK(Long id);

    GioHangResponse findById(Long id);

    GioHangResponse update(Long id, UpdatedGioHangRequest request);

}
