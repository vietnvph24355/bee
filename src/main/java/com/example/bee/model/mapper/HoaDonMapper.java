package com.example.bee.model.mapper;

import com.example.bee.entity.HoaDon;
import com.example.bee.model.request.create_request.CreateHoaDonRequest;
import com.example.bee.model.request.update_request.UpdatedHoaDonRequest;
import com.example.bee.model.response.HoaDonResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HoaDonMapper {

    @Autowired
    private ModelMapper modelMapper;

    public HoaDon convertHoaDonResponseToEntity(HoaDonResponse hoaDonResponse){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(hoaDonResponse, HoaDon.class);
    }

    public HoaDonResponse convertHoaDonEntityToHoaDonResponse(HoaDon hoaDon){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(hoaDon, HoaDonResponse.class);
    }

    public HoaDon convertCreateHoaDonRequestToHoaDonEntity(CreateHoaDonRequest createHoaDonRequest){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(createHoaDonRequest, HoaDon.class);
    }

    public HoaDon convertUpdatedHoaDonRequestToHoaDonEntity(UpdatedHoaDonRequest updatedHoaDonRequest){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(updatedHoaDonRequest, HoaDon.class);
    }

    public List<HoaDonResponse> convertListHoaDonEntityToHoaDonResponse(List<HoaDon> hoaDonList){
        List<HoaDonResponse> list = new ArrayList<>(hoaDonList.size());
        for (HoaDon hd : hoaDonList){
            list.add(convertHoaDonEntityToHoaDonResponse(hd));
        }
        return list;
    }

}
