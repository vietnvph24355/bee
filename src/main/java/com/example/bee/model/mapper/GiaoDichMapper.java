package com.example.bee.model.mapper;


import com.example.bee.entity.GiaoDich;
import com.example.bee.model.request.create_request.CreateGiaoDichRequest;
import com.example.bee.model.request.update_request.UpdatedGiaoDichRequest;
import com.example.bee.model.response.GiaoDichResponse;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GiaoDichMapper {

    @Autowired
    private ModelMapper modelMapper;

    public GiaoDich convertGiaoDichResponseToGiaoDichEntity(GiaoDichResponse giaoDichResponse){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(giaoDichResponse, GiaoDich.class);
    }

    public GiaoDichResponse convertGiaoDichEntityToGiaoDichResponse(GiaoDich giaoDich){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(giaoDich, GiaoDichResponse.class);
    }

    public GiaoDich convertCreateGiaoDichRequestToGiaoDichEntity(CreateGiaoDichRequest createGiaoDichRequest){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(createGiaoDichRequest, GiaoDich.class);
    }

    public void convertUpdatedGiaoDichRequestToGiaoDichEntity(UpdatedGiaoDichRequest updatedGiaoDichRequest, GiaoDich giaoDich){
        modelMapper.map(updatedGiaoDichRequest, giaoDich);
    }

    public List<GiaoDichResponse> convertListGiaoDichEntityToGiaoDichResponse(List<GiaoDich> giaoDichList){
        List<GiaoDichResponse> list = new ArrayList<>(giaoDichList.size());
        for (GiaoDich gd : giaoDichList){
            list.add(convertGiaoDichEntityToGiaoDichResponse(gd));
        }
        return list;
    }

}
