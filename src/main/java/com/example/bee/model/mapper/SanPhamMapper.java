package com.example.bee.model.mapper;

import com.example.bee.entity.SanPham;
import com.example.bee.entity.ThuongHieu;
import com.example.bee.model.request.create_request.CreatedSanPhamRequest;
import com.example.bee.model.request.update_request.UpdatedSanPhamRequest;
import com.example.bee.model.response.SanPhamResponse;
import com.example.bee.repository.SanPhamRepository;
import com.example.bee.repository.ThuongHieuRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SanPhamMapper {
    @Autowired
    private ModelMapper modelMapper;

    @PostConstruct
    public void init() {
        // Cấu hình modelMapper để bỏ qua trường id khi map từ UpdatedSanPhamRequest sang SanPham
        modelMapper.addMappings(new PropertyMap<UpdatedSanPhamRequest, SanPham>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });
    }

    public SanPhamResponse convertEntityToResponse(SanPham sanPham) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(sanPham, SanPhamResponse.class);
    }

    public SanPham convertCreateRequestToEntity(CreatedSanPhamRequest request) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(request, SanPham.class);
    }

    public SanPham convertUpdateRequestToEntity(UpdatedSanPhamRequest request, SanPham sanPham) {
        modelMapper.map(request, sanPham);
        return sanPham;
    }
//    public SanPham convertUpdateRequestToEntity(UpdatedSanPhamRequest request, SanPham sanPham) {
//        if (request.getThuongHieuId() != null) {
//            ThuongHieu thuongHieu = thuongHieuRepository.findById(request.getThuongHieuId())
//                    .orElseThrow(() -> new RuntimeException("Thương hiệu không tồn tại"));
//            sanPham.setThuongHieu(thuongHieu);
//        }
//        modelMapper.map(request, sanPham);
//        return sanPham;
//}
}