package com.example.bee.service.impl;

import com.example.bee.common.CommonEnum;
import com.example.bee.entity.MauSac;
import com.example.bee.exception.BadRequestException;
import com.example.bee.exception.NotFoundException;
import com.example.bee.model.mapper.MauSacMapper;
import com.example.bee.model.request.create_request.CreatedMauSacRequest;
import com.example.bee.model.request.update_request.UpdatedMauSacRequest;
import com.example.bee.model.response.MauSacResponse;
import com.example.bee.repository.MauSacRepository;
import com.example.bee.service.MauSacService;
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
public class MauSacServiceImpl implements MauSacService {

    @Autowired
    private MauSacRepository repository;

    @Autowired
    private MauSacMapper mapper;

    @Override
    public List<MauSacResponse> getMauSacByNgayTaoDESC() {
        List<MauSac> list = repository.getMauSacByNgayTaoDESC();
        return list.stream()
                .map(mapper::convertEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Page<MauSacResponse> getAll(Integer page, Integer pageSize, String sortField, String sortOrder, String searchText, String trangThaiString) {
        Sort sort;
        if ("ascend".equals(sortOrder)) {
            sort = Sort.by(sortField).ascending();
        } else if ("descend".equals(sortOrder)) {
            sort = Sort.by(sortField).descending();
        } else {
            sort = Sort.by("ngayTao").descending();
        }

        CommonEnum.TrangThaiThuocTinh trangThai;

        if (trangThaiString == null || trangThaiString.equals("")) {
            trangThai = null;
        } else {
            trangThai = CommonEnum.TrangThaiThuocTinh.valueOf(trangThaiString);
        }

        Pageable pageable = PageRequest.of(page - 1, pageSize, sort);
        Page<MauSac> mauSacPage = repository.findByAll(pageable, searchText,trangThai);
        return mauSacPage.map(mapper::convertEntityToResponse);
    }

    @Override
    public MauSacResponse add(CreatedMauSacRequest request) {
        if (repository.existsByMa(request.getMa())) {
            throw new BadRequestException("Mã màu sắc đã tồn tại trong hệ thống!");
        }
        if (repository.existsByTen(request.getTen())) {
            throw new BadRequestException("Tên màu sắc đã tồn tại trong hệ thống!");
        }
        MauSac createdMauSac = mapper.convertCreateRequestToEntity(request);
        createdMauSac.setTrangThai(CommonEnum.TrangThaiThuocTinh.ACTIVE);
        MauSac savedMauSac = this.repository.save(createdMauSac);
        return mapper.convertEntityToResponse(savedMauSac);
    }

    @Override
    public MauSacResponse update(Long id, UpdatedMauSacRequest request) {
        Optional<MauSac> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Màu sắc không tồn tại!");
        }

        // Chuẩn hóa mã và tên màu sắc để so sánh
        String normalizedMa = request.getMa().trim();
        String normalizedTen = request.getTen().trim().toLowerCase();

        // Kiểm tra mã màu sắc (phân biệt chữ hoa chữ thường)
        if (!normalizedMa.equals(optional.get().getMa().trim()) && repository.existsByMa(normalizedMa)) {
            throw new BadRequestException("Mã màu sắc đã tồn tại trong hệ thống!");
        }

        // Kiểm tra tên màu sắc (không phân biệt chữ hoa chữ thường)
        if (!normalizedTen.equals(optional.get().getTen().trim().toLowerCase()) && repository.existsByTen(normalizedTen)) {
            throw new BadRequestException("Tên màu sắc đã tồn tại trong hệ thống!");
        }

        MauSac mauSac = optional.get();

        // Cập nhật mã và tên màu sắc
        mauSac.setMa(normalizedMa);
        mauSac.setTen(request.getTen().trim()); // Lưu tên với trường hợp chữ ban đầu

        mapper.convertUpdatedRequestToEntity(request, mauSac);
        return mapper.convertEntityToResponse(repository.save(mauSac));
    }


    @Override
    public void delete(Long id) {
        Optional<MauSac> optional = this.repository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Màu sắc không tồn tại");
        }
        repository.delete(optional.get());
    }

    @Override
    public MauSacResponse findById(Long id) {
        Optional<MauSac> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Màu sắc không tồn tại");
        }

        return mapper.convertEntityToResponse(optional.get());
    }

    @Override
    public List<MauSacResponse> getMauSacKhongLap(Long idSanPham) {
        List<MauSac> listMauSac = repository.getMauSacKhongLap(idSanPham);

        List<MauSacResponse> mauSacResponses = listMauSac.stream()
                .map(mapper::convertEntityToResponse)
                .collect(Collectors.toList());

        return mauSacResponses;
    }
}
