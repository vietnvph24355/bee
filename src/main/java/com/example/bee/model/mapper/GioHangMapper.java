package com.example.bee.model.mapper;

import com.example.bee.entity.GioHang;
import com.example.bee.model.request.create_request.CreatedGioHangRequest;
import com.example.bee.model.request.update_request.UpdatedGioHangRequest;
import com.example.bee.model.response.GioHangResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GioHangMapper {
    @Autowired
    private ModelMapper mapper;

    public GioHangResponse convertEntityToResponse(GioHang gioHang) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return mapper.map(gioHang, GioHangResponse.class);
    }

    public GioHang convertCreateRequestToEntity(CreatedGioHangRequest request) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

        // Bỏ qua ánh xạ cho trường id
        mapper.typeMap(CreatedGioHangRequest.class, GioHang.class).addMappings(mapping -> {
            mapping.skip(GioHang::setId);
        });

        return mapper.map(request, GioHang.class);
    }

    public void convertUpdateRequestToEntity(UpdatedGioHangRequest request, GioHang gioHang) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        mapper.typeMap(UpdatedGioHangRequest.class, GioHang.class).addMappings(mapping -> {
            mapping.skip(GioHang::setId);
        });
        mapper.map(request, gioHang);
    }
}
