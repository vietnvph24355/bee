package com.example.bee.service.impl;

import com.example.bee.common.CommonEnum;
import com.example.bee.common.GenCode;
import com.example.bee.entity.Voucher;
import com.example.bee.exception.BadRequestException;
import com.example.bee.exception.NotFoundException;
import com.example.bee.model.mapper.VoucherMapper;
import com.example.bee.model.request.create_request.CreatedVoucherRequest;
import com.example.bee.model.request.update_request.UpdatedVoucherRequest;
import com.example.bee.model.response.VoucherResponse;
import com.example.bee.repository.VoucherRepository;
import com.example.bee.service.VoucherService;
import com.example.bee.utils.VoucherUtils;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class VoucherServiceImpl implements VoucherService {

    @Autowired
    private VoucherRepository repository;

    @Autowired
    private VoucherMapper mapper;

    @Autowired
    private VoucherUtils voucherUtils;


    @Override
    public Page<VoucherResponse> getAll(Integer page, Integer pageSize, String sortField, String sortOrder,
                                        String searchText, Long hinhThucGiamGiaId, String trangThaiString,
                                        LocalDateTime ngayBatDau, LocalDateTime ngayKetThuc) {
        Sort sort;
        if ("ascend".equals(sortOrder)) {
            sort = Sort.by(sortField).ascending();
        } else if ("descend".equals(sortOrder)) {
            sort = Sort.by(sortField).descending();
        } else {
            sort = Sort.by("ngayTao").descending();
        }
        CommonEnum.TrangThaiVoucher trangThai;
        if (trangThaiString == null || trangThaiString.equals("")) {
            trangThai = null;
        } else {
            trangThai = CommonEnum.TrangThaiVoucher.valueOf(trangThaiString);
        }
        Pageable pageable = PageRequest.of(page - 1, pageSize, sort);
        Page<Voucher> voucherPage = repository.findByALl(pageable, searchText, hinhThucGiamGiaId, trangThai, ngayBatDau, ngayKetThuc);
        return voucherPage.map(mapper::convertEntityToResponse);
    }

    @Override
    public List<VoucherResponse> getListVoucher(Long id) {

        List<Voucher> list = repository.getListVoucherOK(id);
        return list
                .stream()
                .map(mapper::convertEntityToResponse)
                .collect(Collectors.toList());

    }

    @Override
    public List<VoucherResponse> getListVoucherSuDung(Long id) {
        List<Voucher> list = repository.getListVoucherSuDung(id);
        return list
                .stream()
                .map(mapper::convertEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public VoucherResponse add(CreatedVoucherRequest request) {
        if (request.getGiaTriGiam().doubleValue() > 50 && request.getHinhThucGiam().getId() == 2) {
            throw new BadRequestException("Số % giảm tối đa không được lớn hơn 50%");
        }
        if (repository.existsByTen(request.getTen())) {
            throw new BadRequestException("Tên voucher đã tồn tại trong hệ thống!");
        }

        Voucher createdVoucher = mapper.convertCreateRequestToEntity(request);
        createdVoucher.setMa(GenCode.generateVoucherCode());
        CommonEnum.TrangThaiVoucher status = voucherUtils.setTrangThaiVoucher(
                createdVoucher.getNgayBatDau(),
                createdVoucher.getNgayKetThuc()
        );
        createdVoucher.setTrangThai(status);
        Voucher savedVoucher = this.repository.save(createdVoucher);
        return mapper.convertEntityToResponse(savedVoucher);
    }

    @Override
    public VoucherResponse update(Long id, UpdatedVoucherRequest request) {
        Optional<Voucher> optional = repository.findById(id);
        if (request.getGiaTriGiam().doubleValue() > 50 && request.getHinhThucGiam().getId() == 2) {
            throw new BadRequestException("Số % giảm tối đa không được lớn hơn 50%");
        }
        if (optional.isEmpty()) {
            throw new NotFoundException("Voucher không tồn tại");
        }
        if (!request.getTen().equals(optional.get().getTen()) && repository.existsByTen(request.getTen())) {
            throw new BadRequestException("Tên voucher đã tồn tại trong hệ thống!");
        }
        Voucher detail = optional.get();
        CommonEnum.TrangThaiVoucher status = voucherUtils.setTrangThaiVoucher(
                request.getNgayBatDau(), request.getNgayKetThuc()
        );
        request.setId(detail.getId());
        request.setTrangThai(status);
        mapper.convertUpdateRequestToEntity(request, detail);
        Voucher savedVoucher = this.repository.save(detail);
        return mapper.convertEntityToResponse(savedVoucher);
    }

    @Override
    public VoucherResponse delete(Long id) {
        Optional<Voucher> optional = this.repository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Voucher không tồn tại");
        }
        repository.delete(optional.get());
        return mapper.convertEntityToResponse(optional.get());
    }

    @Override
    public Long soLanDaSuDung(Long idVoucher, LocalDate startDate, LocalDate endDate) {
        return repository.soLanDaSuDung(idVoucher, startDate, endDate);
    }

    @Override
    public Long soLanDaSuDungVoucherTaiKhoan(Long idVoucher, Long idTaiKhoan) {
        return repository.soLanDaSuDungVoucherTaiKhoan(idVoucher, idTaiKhoan);
    }

    @Override
    public VoucherResponse findById(Long id) {
        Optional<Voucher> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Voucher không tồn tại");
        }
        return mapper.convertEntityToResponse(optional.get());
    }

    @Override
    public VoucherResponse findByMa(String ma) {
        Optional<Voucher> optional = repository.findByMa(ma);
        if (optional.isEmpty()) {
            throw new NotFoundException("Voucher không tồn tại");
        }
        return mapper.convertEntityToResponse(optional.get());
    }

    public void updateVoucherStatus() {
        List<Voucher> vouchers = repository.danhSachVoucherKhongHuy();
        LocalDateTime now = LocalDateTime.now();

        for (Voucher voucher : vouchers) {
            CommonEnum.TrangThaiVoucher oldStatus = voucher.getTrangThai();

            if (now.isBefore(voucher.getNgayBatDau())) {
                voucher.setTrangThai(CommonEnum.TrangThaiVoucher.UPCOMING);
            } else if (now.isEqual(voucher.getNgayBatDau()) || (now.isAfter(voucher.getNgayBatDau()) && now.isBefore(voucher.getNgayKetThuc()))) {
                if (now.isAfter(voucher.getNgayKetThuc().minus(1, ChronoUnit.DAYS))) {
                    voucher.setTrangThai(CommonEnum.TrangThaiVoucher.ENDING_SOON);
                } else {
                    voucher.setTrangThai(CommonEnum.TrangThaiVoucher.ONGOING);
                }
            } else if (now.isAfter(voucher.getNgayKetThuc())) {
                voucher.setTrangThai(CommonEnum.TrangThaiVoucher.EXPIRED);
            } else {
                // Thêm điều kiện để kiểm tra nếu voucher đã bị hủy
                voucher.setTrangThai(CommonEnum.TrangThaiVoucher.CANCELLED);
            }

            CommonEnum.TrangThaiVoucher newStatus = voucher.getTrangThai();
            if (oldStatus != newStatus) {
                System.out.println("Voucher ID: " + voucher.getId() + " - Status changed from " + oldStatus + " to " + newStatus);
            }
        }

        repository.saveAll(vouchers);
    }

    @Override
    public void cancelVoucher(Long id) {
        Voucher voucher = repository.findById(id).orElse(null);
        voucher.setTrangThai(CommonEnum.TrangThaiVoucher.CANCELLED);
        repository.save(voucher);
    }

}
