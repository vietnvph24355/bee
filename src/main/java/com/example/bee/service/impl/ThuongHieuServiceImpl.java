package com.example.bee.service.impl;

import com.example.bee.common.CommonEnum;
import com.example.bee.entity.ThuongHieu;
import com.example.bee.exception.BadRequestException;
import com.example.bee.exception.NotFoundException;
import com.example.bee.model.mapper.ThuongHieuMapper;
import com.example.bee.model.request.create_request.CreatedThuongHieuRequest;
import com.example.bee.model.request.update_request.UpdatedThuongHieuRequest;
import com.example.bee.model.response.ThuongHieuResponse;
import com.example.bee.repository.ThuongHieuRepository;
import com.example.bee.service.ThuongHieuService;
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
public class ThuongHieuServiceImpl implements ThuongHieuService {

    @Autowired
    private ThuongHieuRepository repository;

    @Autowired
    private ThuongHieuMapper mapper;

    @Override
    public List<ThuongHieuResponse> getThuongHieuByNgayTaoDESC() {
        List<ThuongHieu> list = repository.getThuongHieuByNgayTaoDESC();
        return list.stream()
                .map(mapper::convertEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ThuongHieuResponse> getAll(Integer page, Integer pageSize, String sortField, String sortOrder, String searchText, String trangThaiString) {
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
        Page<ThuongHieu> thuongHieuPage = repository.findByAll(pageable, searchText, trangThai);
        return thuongHieuPage.map(mapper::convertEntityToResponse);
    }

    @Override
    public ThuongHieuResponse add(CreatedThuongHieuRequest request) {
        if (repository.existsByTen(request.getTen())) {
            throw new BadRequestException("Tên thương hiệu đã tồn tại trong hệ thống!");
        }

        ThuongHieu createdThuongHieu = mapper.convertCreateRequestToEntity(request);
        createdThuongHieu.setTrangThai(CommonEnum.TrangThaiThuocTinh.ACTIVE);
        ThuongHieu savedThuongHieu = repository.save(createdThuongHieu);
        return mapper.convertEntityToResponse(savedThuongHieu);
    }

    @Override
    public void delete(Long id) {
        Optional<ThuongHieu> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Thương hiệu không tồn tại");
        }

        ThuongHieu thuongHieu = optional.get();
        repository.delete(thuongHieu);
    }

    @Override
    public ThuongHieuResponse update(Long id, UpdatedThuongHieuRequest request) {
        Optional<ThuongHieu> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Thương hiệu không tồn tại");
        }
        if (!request.getTen().equals(optional.get().getTen()) && repository.existsByTen(request.getTen())) {
            throw new BadRequestException("Tên thương hiệu đã tồn tại trong hệ thống!");
        }
        ThuongHieu thuongHieu = optional.get();
        mapper.convertUpdateRequestToEntity(request, thuongHieu);
        return mapper.convertEntityToResponse(repository.save(thuongHieu));
    }

    @Override
    public ThuongHieuResponse findById(Long id) {
        Optional<ThuongHieu> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Thương hiệu không tồn tại");
        }

        ThuongHieu thuongHieu = optional.get();
        return mapper.convertEntityToResponse(thuongHieu);
    }
}

