package com.example.bee.service;

import com.example.bee.model.request.QuenMatKhauRequest.UpdatedVaiTroRequest;
import com.example.bee.model.request.create_request.CreatedVaiTroRequest;
import com.example.bee.model.response.VaiTroResponse;
import org.springframework.data.domain.Page;

public interface VaiTroService {
    Page<VaiTroResponse> getAll(Integer page, Integer pageSize, String sortField, String sortOrder, String searchText, String trangThaiString);

    VaiTroResponse add(CreatedVaiTroRequest request);

    VaiTroResponse update(Long id, UpdatedVaiTroRequest request);

    void  delete(Long id);

    VaiTroResponse findById(Long id);
}
