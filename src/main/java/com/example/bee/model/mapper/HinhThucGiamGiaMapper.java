package com.example.bee.model.mapper;

import com.example.bee.entity.HinhThucGiamGia;
import com.example.bee.model.request.create_request.CreatedHinhThucGiamGiaRequest;
import com.example.bee.model.request.update_request.UpdatedHinhThucGiamGiaRequest;
import com.example.bee.model.response.HinhThucGiamGiaResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HinhThucGiamGiaMapper {

    @Autowired
    private ModelMapper modelMapper;

    public HinhThucGiamGiaResponse convertEntityToResponse(HinhThucGiamGia hinhThucGiamGia) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(hinhThucGiamGia, HinhThucGiamGiaResponse.class);
    }

    public HinhThucGiamGia convertCreateRequestToEntity(CreatedHinhThucGiamGiaRequest request) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return  modelMapper.map(request, HinhThucGiamGia.class);
    }

    public void convertUpdateRequestToEntity(UpdatedHinhThucGiamGiaRequest request, HinhThucGiamGia hinhThucGiamGia) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        modelMapper.map(request, hinhThucGiamGia);
    }

    public List<HinhThucGiamGiaResponse> listNhanVienEntityToNhanVienResponse(List<HinhThucGiamGia> hinhThucGiamGiaList) {
        List<HinhThucGiamGiaResponse> list = new ArrayList<>(hinhThucGiamGiaList.size());
        for (HinhThucGiamGia ht : hinhThucGiamGiaList) {
            list.add(convertEntityToResponse(ht));
        }
        return list;
    }

}
