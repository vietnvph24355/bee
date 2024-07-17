package com.example.bee.service.impl;

import com.example.bee.common.CommonEnum;
import com.example.bee.entity.VaiTro;
import com.example.bee.model.mapper.VaiTroMapper;
import com.example.bee.model.request.QuenMatKhauRequest.UpdatedVaiTroRequest;
import com.example.bee.model.request.create_request.CreatedVaiTroRequest;
import com.example.bee.model.response.VaiTroResponse;
import com.example.bee.repository.VaiTroRepository;
import com.example.bee.service.VaiTroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VaiTroServiceImpl implements VaiTroService {

    @Autowired
    private VaiTroRepository vaiTroRepository;

    @Autowired
    private VaiTroMapper vaiTroMapper;

    @Override
    public Page<VaiTroResponse> getAll(Integer page, Integer pageSize, String sortField, String sortOrder, String searchText, String trangThaiString) {
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
        Page<VaiTro> vaiTroPage = vaiTroRepository.findByAll(pageable, searchText,trangThai);
        return vaiTroPage.map(vaiTroMapper::convertEntityToResponse);
    }

    @Override
    public VaiTroResponse add(CreatedVaiTroRequest request) {
        VaiTro createdVaiTro = vaiTroMapper.convertCreateRequestToEntity(request);
        createdVaiTro.setTen("CUSTOMER");
        createdVaiTro.setTrangThai(CommonEnum.TrangThaiThuocTinh.ACTIVE);
        VaiTro savedVaiTro = this.vaiTroRepository.save(createdVaiTro);
        return vaiTroMapper.convertEntityToResponse(savedVaiTro);
    }

    @Override
    public VaiTroResponse update(Long id, UpdatedVaiTroRequest request) {
        Optional<VaiTro> optional = vaiTroRepository.findById(id);
        VaiTro vaiTro = optional.get();
        vaiTroMapper.convertUpdateRequestToEntity(request,vaiTro);
        return vaiTroMapper.convertEntityToResponse(vaiTroRepository.save(vaiTro));
    }

    @Override
    public void delete(Long id) {
        Optional<VaiTro> optional = this.vaiTroRepository.findById(id);
        vaiTroRepository.delete(optional.get());
    }

    @Override
    public VaiTroResponse findById(Long id) {
        Optional<VaiTro> optional = vaiTroRepository.findById(id);
        return vaiTroMapper.convertEntityToResponse(optional.get());
    }
}
