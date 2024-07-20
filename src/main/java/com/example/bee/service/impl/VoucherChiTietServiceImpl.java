package com.example.bee.service.impl;


import com.example.bee.common.CommonEnum;
import com.example.bee.entity.VoucherChiTiet;
import com.example.bee.model.mapper.VoucherChiTietMapper;
import com.example.bee.model.request.create_request.CreatedVoucherChiTietRequest;
import com.example.bee.model.request.update_request.UpdatedVoucherChiTietRequest;
import com.example.bee.model.response.VoucherChiTietResponse;
import com.example.bee.model.response.VoucherChiTietResponseMapping;
import com.example.bee.repository.VoucherChiTietRepository;
import com.example.bee.service.VoucherChiTietService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VoucherChiTietServiceImpl implements VoucherChiTietService {

    @Autowired
    private VoucherChiTietRepository repository;

    @Autowired
    private VoucherChiTietMapper mapper;

    @Override
    public List<VoucherChiTietResponse> getAllList(Long idVoucher) {
        List<VoucherChiTiet> list = repository.getAllList(idVoucher);
        return list.stream()
                .map(mapper::convertEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<VoucherChiTietResponseMapping> findByVoucherId(Long idVoucher) {
        return repository.findByVoucherId(idVoucher);
    }


    @Override
    public Page<VoucherChiTietResponse> getAllPage(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<VoucherChiTiet> chiTietPage = repository.getAllPage(pageable);
        return chiTietPage.map(mapper::convertEntityToResponse);
    }

    @Override
    @Transactional
    public List<VoucherChiTietResponse> addList(List<CreatedVoucherChiTietRequest> requests) {
        List<VoucherChiTiet> list = new ArrayList<>();
        for (CreatedVoucherChiTietRequest request : requests) {
            VoucherChiTiet voucherChiTiet = mapper.convertCreateRequestToEntity(request);
            voucherChiTiet.setTrangThai(CommonEnum.TrangThaiVoucherChiTiet.ACTIVE);
            list.add(voucherChiTiet);
        }
        repository.saveAll(list);
        return null;
    }

    @Override
    @Transactional
    public VoucherChiTietResponse update(List<UpdatedVoucherChiTietRequest> updateRequests) {
        List<VoucherChiTiet> newVoucherChiTietList = new ArrayList<>();
        List<VoucherChiTiet> updatedVoucherChiTietList = new ArrayList<>();

        for (UpdatedVoucherChiTietRequest updateRequest : updateRequests) {
            if (updateRequest.getId() != null) {
                // If the DTO has an ID, it means it's an update
                Optional<VoucherChiTiet> optional = repository.findById(updateRequest.getId());
                if (optional.isPresent()) {
                    VoucherChiTiet existingVoucherChiTiet = optional.get();
                    updateRequest.setId(existingVoucherChiTiet.getId());
                    mapper.convertUpdateRequestToEntity(updateRequest, existingVoucherChiTiet);
                    updatedVoucherChiTietList.add(existingVoucherChiTiet);
                }
            } else if (updateRequest.getId() == null) {
                VoucherChiTiet newVoucherChiTiet = new VoucherChiTiet();
                newVoucherChiTiet.setTrangThai(CommonEnum.TrangThaiVoucherChiTiet.ACTIVE);
                mapper.convertUpdateRequestToEntity(updateRequest, newVoucherChiTiet);
                newVoucherChiTietList.add(newVoucherChiTiet);
            }
        }
        // Add new objects to the database
        if (!newVoucherChiTietList.isEmpty()) {
            repository.saveAll(newVoucherChiTietList);
        }

        // Update existing objects in the database
        if (!updatedVoucherChiTietList.isEmpty()) {
            repository.saveAll(updatedVoucherChiTietList);
        }

        // You might want to return an appropriate response here based on the operation
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
