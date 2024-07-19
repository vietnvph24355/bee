package com.example.bee.service.impl;

import com.example.bee.common.CommonEnum;
import com.example.bee.entity.DiaHinhSan;
import com.example.bee.exception.BadRequestException;
import com.example.bee.exception.NotFoundException;
import com.example.bee.model.mapper.DiaHinhSanMapper;
import com.example.bee.model.request.create_request.CreatedDiaHinhSanRequest;
import com.example.bee.model.request.update_request.UpdatedDiaHinhSanRequest;
import com.example.bee.model.response.DiaHinhSanResponse;
import com.example.bee.repository.DiaHinhSanRepository;
import com.example.bee.service.DiaHinhSanService;
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
public class DiaHinhSanServiceImpl implements DiaHinhSanService {
    @Autowired
    private DiaHinhSanRepository repository;

    @Autowired
    private DiaHinhSanMapper mapper;

    @Override
    public List<DiaHinhSanResponse> listDiaHinhSan() {
        List<DiaHinhSan> list = repository.getDiaHinhSanByNgayTaoDESC();
        return list.stream()
                .map(mapper::convertEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Page<DiaHinhSanResponse> getAll(Integer page, Integer pageSize, String sortField, String sortOrder, String searchText, String trangThaiString) {
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
        Page<DiaHinhSan> diaHinhSanPage = repository.findByAll(pageable, searchText, trangThai);
        return diaHinhSanPage.map(mapper::convertEntityToResponse);
    }

    @Override
    public DiaHinhSanResponse add(CreatedDiaHinhSanRequest request) {
        if (repository.existsByTen(request.getTen())) {
            throw new BadRequestException("Tên địa hình sân đã tồn tại trong hệ thống!");
        }
        DiaHinhSan createdDiaHinhSan = mapper.convertCreateResponseToEntity(request);
        createdDiaHinhSan.setTrangThai(CommonEnum.TrangThaiThuocTinh.ACTIVE);
        DiaHinhSan savedDiaHinhSan = repository.save(createdDiaHinhSan);
        return mapper.convertEntityToResponse(savedDiaHinhSan);
    }

    @Override
    public DiaHinhSanResponse update(Long id, UpdatedDiaHinhSanRequest request) {
        Optional<DiaHinhSan> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Địa hình sân không tồn tại");
        }

        if (!request.getTen().equals(optional.get().getTen()) && repository.existsByTen(request.getTen())) {
            throw new BadRequestException("Tên địa hình sân đã tồn tại trong hệ thống!");
        }

        DiaHinhSan diaHinhSan = optional.get();
        mapper.convertUpdateRequestToEntity(request, diaHinhSan);
        return mapper.convertEntityToResponse(repository.save(diaHinhSan));
    }

    @Override
    public void delete(Long id) {
        Optional<DiaHinhSan> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Địa hình sân không tồn tại");
        }
        DiaHinhSan diaHinhSan = optional.get();
        repository.delete(diaHinhSan);
    }

    @Override
    public DiaHinhSanResponse findById(Long id) {
        Optional<DiaHinhSan> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Địa hình sân không tồn tại");
        }
        DiaHinhSan diaHinhSan = optional.get();
        return mapper.convertEntityToResponse(diaHinhSan);
    }

    @Override
    public List<DiaHinhSanResponse> getDiaHinhSanKhongLap(Long idSanPham) {
        List<DiaHinhSan> listDiaHinhSan = repository.getDiaHinhSanKhongLap(idSanPham);

        List<DiaHinhSanResponse> diaHinhSanResponse = listDiaHinhSan.stream()
                .map(mapper::convertEntityToResponse)
                .collect(Collectors.toList());

        return diaHinhSanResponse;
    }
}
