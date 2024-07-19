package com.example.bee.service.impl;

import com.example.bee.common.CommonEnum;
import com.example.bee.entity.LoaiDe;
import com.example.bee.exception.BadRequestException;
import com.example.bee.exception.NotFoundException;
import com.example.bee.model.mapper.LoaiDeMapper;
import com.example.bee.model.request.create_request.CreatedLoaiDeRequest;
import com.example.bee.model.request.update_request.UpdatedLoaiDeRequest;
import com.example.bee.model.response.LoaiDeResponse;
import com.example.bee.repository.LoaiDeRepository;
import com.example.bee.service.LoaiDeService;
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
public class LoaiDeServiceImpl implements LoaiDeService {
    @Autowired
    private LoaiDeRepository repository;

    @Autowired
    private LoaiDeMapper mapper;

    @Override
    public List<LoaiDeResponse> getLoaiDeByNgayTaoDESC() {
        List<LoaiDe> list = repository.getLoaiDeByNgayTaoDESC();
        return list.stream()
                .map(mapper::convertEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Page<LoaiDeResponse> getAll(Integer page, Integer pageSize, String sortField, String sortOrder, String searchText, String trangThaiString) {
        Sort sort;
        if ("ascend".equals(sortOrder)) {
            sort = org.springframework.data.domain.Sort.by(sortField).ascending();
        } else if ("descend".equals(sortOrder)) {
            sort = org.springframework.data.domain.Sort.by(sortField).descending();
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
        Page<LoaiDe> loaiDePage = repository.findByAll(pageable, searchText, trangThai);
        return loaiDePage.map(mapper::convertEntityToResponse);
    }

    @Override
    public LoaiDeResponse add(CreatedLoaiDeRequest request) {
        if (repository.existsByTen(request.getTen())) {
            throw new BadRequestException("Tên loại đế đã tồn tại trong hệ thống!");
        }
        LoaiDe createdLoaiDe = mapper.convertCreateResponseToEntity(request);
        createdLoaiDe.setTrangThai(CommonEnum.TrangThaiThuocTinh.ACTIVE);
        LoaiDe savedLoaiDe = repository.save(createdLoaiDe);
        return mapper.convertEntityToResponse(savedLoaiDe);
    }

    @Override
    public LoaiDeResponse update(Long id, UpdatedLoaiDeRequest request) {
        Optional<LoaiDe> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Loại đế không tồn tại");
        }

        if (!request.getTen().equals(optional.get().getTen()) && repository.existsByTen(request.getTen())) {
            throw new BadRequestException("Tên loại đế đã tồn tại trong hệ thống!");
        }

        LoaiDe loaiDe = optional.get();
        mapper.convertUpdateRequestToEntity(request, loaiDe);
        return mapper.convertEntityToResponse(repository.save(loaiDe));
    }

    @Override
    public void delete(Long id) {
        Optional<LoaiDe> optional = repository.findById(id);

        if (optional.isEmpty()) {
            throw new NotFoundException("Loại đế không tồn tại");
        }

        LoaiDe loaiDe = optional.get();
        repository.delete(loaiDe);
    }

    @Override
    public LoaiDeResponse findById(Long id) {
        Optional<LoaiDe> optional = repository.findById(id);

        if (optional.isEmpty()) {
            throw new NotFoundException("Loại đế không tồn tại");
        }

        LoaiDe loaiDe = optional.get();
        return mapper.convertEntityToResponse(loaiDe);
    }

    @Override
    public List<LoaiDeResponse> getLoaiDeKhongLap(Long idSanPham) {
        List<LoaiDe> listLoaiDe = repository.getLoaiDeKhongLap(idSanPham);

        List<LoaiDeResponse> loaiDeResponse = listLoaiDe.stream()
                .map(mapper::convertEntityToResponse)
                .collect(Collectors.toList());

        return loaiDeResponse;
    }
}
