package com.example.bee.service.impl;

import com.example.bee.common.CommonEnum;
import com.example.bee.entity.KichCo;
import com.example.bee.exception.BadRequestException;
import com.example.bee.exception.NotFoundException;
import com.example.bee.model.mapper.KichCoMapper;
import com.example.bee.model.request.create_request.CreatedKichCoRequest;
import com.example.bee.model.request.update_request.UpdatedKichCoRequest;
import com.example.bee.model.response.KichCoResponse;
import com.example.bee.repository.KichCoRepository;
import com.example.bee.service.KichCoService;
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
public class KichCoServiceImpl implements KichCoService {
    @Autowired
    private KichCoRepository repository;

    @Autowired
    private KichCoMapper mapper;

    @Override
    public List<KichCoResponse> listKichCo() {
        List<KichCo> list = repository.getKichCoByNgayTaoDESC();
        return list.stream()
                .map(mapper::convertEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Page<KichCoResponse> getAll(Integer page, Integer pageSize, String sortField, String sortOrder, String searchText, String trangThaiString) {
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
        Page<KichCo> kichCoPage = repository.findByAll(pageable, searchText,trangThai);
        return kichCoPage.map(mapper::convertEntityToResponse);
    }

    @Override
    public KichCoResponse add(CreatedKichCoRequest request) {
        if (repository.existsByKichCo(request.getKichCo())) {
            throw new BadRequestException("Kích cỡ đã tồn tại trong hệ thống!");
        }
        KichCo createdKichCo = mapper.convertCreateRequestToEntity(request);
        createdKichCo.setTrangThai(CommonEnum.TrangThaiThuocTinh.ACTIVE);
        KichCo savedKichCo = this.repository.save(createdKichCo);
        return mapper.convertEntityToResponse(savedKichCo);
    }

    @Override
    public KichCoResponse update(Long id, UpdatedKichCoRequest request) {
        Optional<KichCo> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Kích cỡ không tồn tại");
        }

        if (!request.getKichCo().equals(optional.get().getKichCo()) && repository.existsByKichCo(request.getKichCo())) {
            throw new BadRequestException("Tên loại đế đã tồn tại trong hệ thống!");
        }

        KichCo kichCo = optional.get();
        mapper.convertUpdateRequestToEntity(request, kichCo);
        return mapper.convertEntityToResponse(repository.save(kichCo));
    }

    @Override
    public void delete(Long id) {
        Optional<KichCo> optional = repository.findById(id);

        if (optional.isEmpty()) {
            throw new NotFoundException("Kích cỡ không tồn tại");
        }

        KichCo kichCo = optional.get();
        repository.delete(kichCo);
    }

    @Override
    public KichCoResponse findById(Long id) {
        Optional<KichCo> optional = repository.findById(id);

        if (optional.isEmpty()) {
            throw new NotFoundException("Kích cỡ không tồn tại");
        }

        KichCo kichCo = optional.get();
        return mapper.convertEntityToResponse(kichCo);
    }

    @Override
    public List<KichCoResponse> getKichCoKhongLap(Long id) {
        List<KichCo> listKichCo = repository.getKichCoKhongLap(id);

        List<KichCoResponse> kichCoResponse = listKichCo.stream()
                .map(mapper::convertEntityToResponse)
                .collect(Collectors.toList());

        return kichCoResponse;
    }
}
