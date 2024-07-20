package com.example.bee.service;

import com.example.bee.common.CommonEnum;
import com.example.bee.model.request.create_request.CreateGiaoDichRequest;
import com.example.bee.model.request.update_request.UpdatedGiaoDichRequest;
import com.example.bee.model.response.GiaoDichResponse;
import java.util.List;

public interface GiaoDichService {
    GiaoDichResponse add(CreateGiaoDichRequest request);

    void delete(Long id);

    GiaoDichResponse update(Long id, UpdatedGiaoDichRequest request);

    String updateByMa(String ma, String ngayThanhToan, CommonEnum.TrangThaiGiaoDich trangThaiGiaoDich);

    List<GiaoDichResponse> getListGiaoDich(Long idHoaDon);

    GiaoDichResponse findByMaGiaoDich(String maGiaoDich);

}
