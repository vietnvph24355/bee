package com.example.bee.service;


import com.example.bee.model.request.create_request.CreatedVoucherChiTietRequest;
import com.example.bee.model.request.update_request.UpdatedVoucherChiTietRequest;
import com.example.bee.model.response.VoucherChiTietResponse;
import com.example.bee.model.response.VoucherChiTietResponseMapping;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VoucherChiTietService {

    List<VoucherChiTietResponse> getAllList(Long idVoucher);

    List<VoucherChiTietResponseMapping> findByVoucherId(Long idVoucher);


    Page<VoucherChiTietResponse> getAllPage(Integer page,Integer pageSize);

    List<VoucherChiTietResponse> addList(List<CreatedVoucherChiTietRequest> requests);

    VoucherChiTietResponse update(List<UpdatedVoucherChiTietRequest> requests);

    void delete(Long id);


}