package com.example.bee.service;

import com.example.bee.model.request.create_request.CreatedKichCoRequest;
import com.example.bee.model.request.update_request.UpdatedKichCoRequest;
import com.example.bee.model.response.KichCoResponse;
import java.util.List;
import org.springframework.data.domain.Page;

public interface KichCoService {
    List<KichCoResponse> listKichCo();

    Page<KichCoResponse> getAll(Integer page, Integer pageSize, String sortField, String sortOrder, String searchText, String trangThaiString);

    KichCoResponse add(CreatedKichCoRequest request);

    KichCoResponse update(Long id, UpdatedKichCoRequest request);

    void delete(Long id);

    KichCoResponse findById(Long id);

    List<KichCoResponse> getKichCoKhongLap(Long id);
}
