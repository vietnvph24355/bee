package com.example.bee.model.mapper;

import com.example.bee.entity.GioHangChiTiet;
import com.example.bee.model.request.create_request.CreatedGioHangChiTietRequest;
import com.example.bee.model.request.update_request.UpdatedGioHangChiTietRequest;
import com.example.bee.model.response.GioHangChiTietResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GioHangChiTietMapper {
    @Autowired
    private ModelMapper mapper;

    public GioHangChiTietResponse convertEntityToResponse(GioHangChiTiet gioHangChiTiet) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return mapper.map(gioHangChiTiet, GioHangChiTietResponse.class);
    }

    public GioHangChiTiet convertCreateRequestToEntity(CreatedGioHangChiTietRequest request) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return mapper.map(request, GioHangChiTiet.class);
    }

    public void convertUpdateRequestToEntity(UpdatedGioHangChiTietRequest request, GioHangChiTiet gioHangChiTiet) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        mapper.map(request, GioHangChiTiet.class);
    }

}
