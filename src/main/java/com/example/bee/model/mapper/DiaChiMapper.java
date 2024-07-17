package com.example.bee.model.mapper;

import com.example.bee.entity.DiaChi;
import com.example.bee.model.request.create_request.CreatedDiaChiRequest;
import com.example.bee.model.request.update_request.UpdatedDiaChiRequest;
import com.example.bee.model.response.DiaChiReponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DiaChiMapper {
    @Autowired
    private ModelMapper mapper;

    public DiaChiReponse convertEntityToResponse(DiaChi diaChi) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return mapper.map(diaChi, DiaChiReponse.class);
    }


    public DiaChi convertCreateResponseToEntity(CreatedDiaChiRequest request) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return mapper.map(request, DiaChi.class);
    }

    public DiaChi convertUpdateRequestToEntity(UpdatedDiaChiRequest request, DiaChi diaChi) {
        mapper.map(request, diaChi);
        return diaChi;
    }
}
