package com.example.bee.service;

import com.example.bee.model.request.create_request.CreatedVoucherRequest;
import com.example.bee.model.request.update_request.UpdatedVoucherRequest;
import com.example.bee.model.response.VoucherResponse;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

import java.time.LocalDateTime;
import java.util.List;

public interface VoucherService {

    Page<VoucherResponse> getAll(Integer page, Integer pageSize, String sortField, String sortOrder,
                                 String searchText, Long hinhThucGiamGiaId, String trangThaiString,
                                 LocalDateTime ngayBatDau, LocalDateTime ngayKetThuc);

    List<VoucherResponse> getListVoucher(Long id);

    List<VoucherResponse> getListVoucherSuDung(Long id);

    VoucherResponse add(CreatedVoucherRequest request);

    VoucherResponse update(Long id, UpdatedVoucherRequest request);

    VoucherResponse delete(Long id);

    Long soLanDaSuDung(Long idVoucher, LocalDate startDate, LocalDate endDate);
    Long soLanDaSuDungVoucherTaiKhoan(Long idVoucher, Long idTaiKhoan);

    VoucherResponse findById(Long id);

    VoucherResponse findByMa(String ma);

    void updateVoucherStatus();

    void cancelVoucher(Long id);
}

