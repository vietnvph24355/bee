package com.example.bee.model.mapper;

import com.example.bee.entity.HoaDonChiTiet;
import com.example.bee.model.request.create_request.CreateHoaDonChiTietRequest;
import com.example.bee.model.request.update_request.UpdatedHoaDonChiTietRequest;
import com.example.bee.model.response.HoaDonChiTietResponse;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HoaDonChiTietMapper {

    @Autowired
    private ModelMapper modelMapper;

    public HoaDonChiTiet convertHoaDonChiTietResponseToHoaDonChiTietEntity(HoaDonChiTietResponse hoaDonChiTietResponse) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(hoaDonChiTietResponse, HoaDonChiTiet.class);
    }

    public HoaDonChiTietResponse convertHoaDonChiTietEntityToHoaDonChiTietResponse(HoaDonChiTiet hoaDonChiTiet) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(hoaDonChiTiet, HoaDonChiTietResponse.class);
    }

    public HoaDonChiTiet convertCreateHoaDonChiTietRequestToHoaDonChiTietEntity(CreateHoaDonChiTietRequest createHoaDonChiTietRequest) {
        return modelMapper.map(createHoaDonChiTietRequest, HoaDonChiTiet.class);
    }

    public HoaDonChiTiet convertUpdatedHoaDonChiTietRequestToHoaDonChiTietEntity(UpdatedHoaDonChiTietRequest updatedHoaDonChiTietRequest) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(updatedHoaDonChiTietRequest, HoaDonChiTiet.class);
    }

    public HoaDonChiTiet convertUpdateRequestToEntity(UpdatedHoaDonChiTietRequest request, HoaDonChiTiet detail) {
        modelMapper.map(request, detail);
        return detail;
    }

    public List<HoaDonChiTietResponse> convertListHoaDonChiTietEntityToHoaDonChiTietResponse(List<HoaDonChiTiet> hoaDonChiTietList) {
        List<HoaDonChiTietResponse> list = new ArrayList<>(hoaDonChiTietList.size());
        for (HoaDonChiTiet hdct : hoaDonChiTietList) {
            list.add(convertHoaDonChiTietEntityToHoaDonChiTietResponse(hdct));
        }
        return list;
    }

}
