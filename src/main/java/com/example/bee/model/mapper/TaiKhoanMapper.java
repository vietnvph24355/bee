package com.example.bee.model.mapper;

import com.example.bee.entity.TaiKhoan;
import com.example.bee.model.request.create_request.CreatedTaiKhoanRequest;
import com.example.bee.model.request.update_request.UpdatedTaiKhoanRequest;
import com.example.bee.model.response.TaiKhoanResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaiKhoanMapper {

    @Autowired
    private ModelMapper modelMapper;

    public TaiKhoanResponse convertEntityToResponse(TaiKhoan taiKhoan) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(taiKhoan, TaiKhoanResponse.class);
    }

    public TaiKhoan convertCreateRequestToEntity(CreatedTaiKhoanRequest createTaiKhoanRequest) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

        TypeMap<CreatedTaiKhoanRequest, TaiKhoan> typeMap = modelMapper.getTypeMap(CreatedTaiKhoanRequest.class, TaiKhoan.class);

        if (typeMap == null) {
            typeMap = modelMapper.createTypeMap(CreatedTaiKhoanRequest.class, TaiKhoan.class);
            typeMap.addMappings(mapper -> {
                mapper.map(source -> source.getVaiTro(), TaiKhoan::setVaiTro);
                // Ánh xạ các thuộc tính khác tại đây nếu cần
            });
        }

        return modelMapper.map(createTaiKhoanRequest, TaiKhoan.class);
    }




    public void convertUpdateRequestToEntity(UpdatedTaiKhoanRequest updateRequest, TaiKhoan detail) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(updateRequest, detail);
    }
}
