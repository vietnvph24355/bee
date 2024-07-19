package com.example.bee.model.mapper;

import com.example.bee.entity.MauSac;
import com.example.bee.model.request.create_request.CreatedMauSacRequest;
import com.example.bee.model.request.update_request.UpdatedMauSacRequest;
import com.example.bee.model.response.MauSacResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MauSacMapper {

    @Autowired
    private ModelMapper modelMapper;

    public MauSacResponse convertEntityToResponse(MauSac mauSac) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(mauSac, MauSacResponse.class);
    }

    public MauSac convertCreateRequestToEntity(CreatedMauSacRequest request) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(request, MauSac.class);
    }

    public void convertUpdatedRequestToEntity(UpdatedMauSacRequest request, MauSac mauSac) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        modelMapper.map(request, mauSac);
    }

}
