package com.example.bee.model.mapper;


import com.example.bee.entity.LoaiDe;
import com.example.bee.model.request.create_request.CreatedLoaiDeRequest;
import com.example.bee.model.request.update_request.UpdatedLoaiDeRequest;
import com.example.bee.model.response.LoaiDeResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoaiDeMapper {

    @Autowired
    private ModelMapper mapper;

    public LoaiDeResponse convertEntityToResponse(LoaiDe loaiDe) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return mapper.map(loaiDe, LoaiDeResponse.class);
    }

    public LoaiDe convertCreateResponseToEntity(CreatedLoaiDeRequest request) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return mapper.map(request, LoaiDe.class);
    }

    public void convertUpdateRequestToEntity(UpdatedLoaiDeRequest request, LoaiDe loaiDe) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        mapper.map(request, loaiDe);
    }

}
