package com.example.bee.service.impl;

import com.example.bee.common.CommonEnum;
import com.example.bee.entity.HinhAnhSanPham;
import com.example.bee.model.mapper.HinhAnhSanPhamMapper;
import com.example.bee.model.request.create_request.CreatedHinhAnhSanPhamRequest;
import com.example.bee.model.request.update_request.UpdatedHinhAnhSanPhamRequest;
import com.example.bee.model.response.HinhAnhSanPhamResponse;
import com.example.bee.repository.HinhAnhSanPhamRepository;
import com.example.bee.service.HinhAnhSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HinhAnhSanPhamServiceImpl implements HinhAnhSanPhamService {
    @Autowired
    private HinhAnhSanPhamRepository repository;

    @Autowired
    private HinhAnhSanPhamMapper mapper;
    @Override
    public List<HinhAnhSanPhamResponse> getAll(Long idSanPham, Long idMauSac) {
        List<HinhAnhSanPham> listHinhAnh = repository.getAll(idSanPham, idMauSac);
        return listHinhAnh.stream()
                .map(hinhAnh -> mapper.convertEntityToResponse(hinhAnh))
                .collect(Collectors.toList());
    }

    @Override
    public List<HinhAnhSanPhamResponse> add(List<CreatedHinhAnhSanPhamRequest> request) {
        List<HinhAnhSanPhamResponse> listHinhAnh = new ArrayList<>();

        if (request.size() == 1) {
            CreatedHinhAnhSanPhamRequest createdHinhAnh = request.get(0);
            HinhAnhSanPham entityHinhAnh = mapper.convertCreateRequestToEntity(createdHinhAnh);
            entityHinhAnh.setTrangThai(CommonEnum.TrangThaiHinhAnh.DEFAULT);
            HinhAnhSanPham savedHinhAnh = repository.save(entityHinhAnh);
            HinhAnhSanPhamResponse responseHinhAnh = mapper.convertEntityToResponse(savedHinhAnh);

            listHinhAnh.add(responseHinhAnh);
        } else {
            for (CreatedHinhAnhSanPhamRequest hinhAnh: request) {
                HinhAnhSanPham entityHinhAnh = mapper.convertCreateRequestToEntity(hinhAnh);
                entityHinhAnh.setTrangThai(CommonEnum.TrangThaiHinhAnh.DEFAULT);
                HinhAnhSanPham savedHinhAnh = repository.save(entityHinhAnh);
                HinhAnhSanPhamResponse responseHinhAnh = mapper.convertEntityToResponse(savedHinhAnh);

                listHinhAnh.add(responseHinhAnh);
            }
        }
        return listHinhAnh;
    }

    @Override
    public void delete(List<HinhAnhSanPham> request) {

    }

    @Override
    public HinhAnhSanPham update(List<UpdatedHinhAnhSanPhamRequest> request) {
        return null;
    }
}
