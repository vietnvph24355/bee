package com.example.bee.model.mapper;


import com.example.bee.entity.PhuongThucThanhToan;
import com.example.bee.model.request.create_request.CreatePhuongThucThanhToanRequest;
import com.example.bee.model.request.update_request.UpdatedPhuongThucThanhToanRequest;
import com.example.bee.model.response.PhuongThucThanhToanResponse;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PhuongThucThanhToanMapper {

    @Autowired
    private ModelMapper modelMapper;

    public PhuongThucThanhToan convertPhuongThucThanhToanResponseToEntity(PhuongThucThanhToanResponse phuongThucThanhToanResponse) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(phuongThucThanhToanResponse, PhuongThucThanhToan.class);
    }

    public PhuongThucThanhToanResponse convertPhuongThucThanhToanEntityToPhuongThucThanhToanResponse(PhuongThucThanhToan phuongThucThanhToan) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(phuongThucThanhToan, PhuongThucThanhToanResponse.class);
    }

    public PhuongThucThanhToan convertCreatePhuongThucThanhToanRequestToPhuongThucThanhToanEntity(CreatePhuongThucThanhToanRequest createPhuongThucThanhToanRequest) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(createPhuongThucThanhToanRequest, PhuongThucThanhToan.class);
    }

    public PhuongThucThanhToan convertUpdatedPhuongThucThanhToanRequestToPhuongThucThanhToanEntity(UpdatedPhuongThucThanhToanRequest updatedPhuongThucThanhToanRequest) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(updatedPhuongThucThanhToanRequest, PhuongThucThanhToan.class);
    }

    public List<PhuongThucThanhToanResponse> convertListPhuongThucThanhToanEntityToPhuongThucThanhToanResponse(List<PhuongThucThanhToan> phuongThucThanhToanList) {
        List<PhuongThucThanhToanResponse> list = new ArrayList<>(phuongThucThanhToanList.size());
        for (PhuongThucThanhToan pttt : phuongThucThanhToanList) {
            list.add(convertPhuongThucThanhToanEntityToPhuongThucThanhToanResponse(pttt));
        }
        return list;
    }

}
