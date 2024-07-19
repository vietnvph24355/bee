package com.example.bee.service;

import com.example.bee.entity.MauSac;
import com.example.bee.model.request.create_request.CreatedMauSacRequest;
import com.example.bee.model.request.update_request.UpdatedMauSacRequest;
import com.example.bee.model.response.MauSacResponse;
import java.util.List;
import org.springframework.data.domain.Page;

public interface MauSacService {
    List<MauSacResponse> getMauSacByNgayTaoDESC();

    Page<MauSacResponse> getAll(Integer page, Integer pageSize, String sortField, String sortOrder,String searchText,String trangThaiString);

    MauSacResponse add(CreatedMauSacRequest request);

    MauSacResponse update(Long id, UpdatedMauSacRequest request);

    void delete(Long id);

    MauSacResponse findById(Long id);

    List<MauSacResponse> getMauSacKhongLap(Long idSanPham);

}
