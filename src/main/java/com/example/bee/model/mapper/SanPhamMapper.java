package com.example.bee.model.mapper;

import com.example.bee.entity.SanPham;
import com.example.bee.entity.ThuongHieu;
import com.example.bee.model.request.create_request.CreatedSanPhamRequest;
import com.example.bee.model.request.update_request.UpdatedSanPhamRequest;
import com.example.bee.model.response.SanPhamResponse;
import com.example.bee.repository.SanPhamRepository;
import com.example.bee.repository.ThuongHieuRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SanPhamMapper {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ThuongHieuRepository thuongHieuRepository;

    public SanPhamResponse convertEntityToResponse(SanPham sanPham) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(sanPham, SanPhamResponse.class);
    }

    public SanPham convertCreateRequestToEntity(CreatedSanPhamRequest request) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(request, SanPham.class);
    }
//    public SanPham convertUpdateRequestToEntity(UpdatedSanPhamRequest request, SanPham sanPham) {
//        modelMapper.map(request, sanPham);
//        return sanPham;
//    }
    public SanPham convertUpdateRequestToEntity(UpdatedSanPhamRequest request, SanPham sanPham) {
        if (request.getThuongHieuId() != null) {
            ThuongHieu thuongHieu = thuongHieuRepository.findById(request.getThuongHieuId())
                    .orElseThrow(() -> new RuntimeException("Thương hiệu không tồn tại"));
            sanPham.setThuongHieu(thuongHieu);
        }
        modelMapper.map(request, sanPham);
        return sanPham;
}
}