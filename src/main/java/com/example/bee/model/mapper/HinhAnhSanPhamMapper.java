package com.example.bee.model.mapper;

import com.example.bee.entity.HinhAnhSanPham;
import com.example.bee.model.request.create_request.CreatedHinhAnhSanPhamRequest;
import com.example.bee.model.request.update_request.UpdatedHinhAnhSanPhamRequest;
import com.example.bee.model.response.HinhAnhSanPhamResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HinhAnhSanPhamMapper {
    @Autowired
    private ModelMapper mapper;

    public HinhAnhSanPhamResponse convertEntityToResponse(HinhAnhSanPham hinhAnh) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return mapper.map(hinhAnh, HinhAnhSanPhamResponse.class);
    }

    public HinhAnhSanPham convertCreateRequestToEntity(CreatedHinhAnhSanPhamRequest request) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return mapper.map(request, HinhAnhSanPham.class);
    }

    public void convertUpdateRequestToEntity(UpdatedHinhAnhSanPhamRequest request, HinhAnhSanPham hinhAnh) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        mapper.map(request, hinhAnh);
    }

}
