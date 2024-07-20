package com.example.bee.model.mapper;


import com.example.bee.entity.Voucher;
import com.example.bee.model.request.create_request.CreatedVoucherRequest;
import com.example.bee.model.request.update_request.UpdatedVoucherRequest;
import com.example.bee.model.response.VoucherResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VoucherMapper {

    @Autowired
    private ModelMapper modelMapper;

    public VoucherResponse convertEntityToResponse(Voucher voucher) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(voucher, VoucherResponse.class);
    }

    public Voucher convertCreateRequestToEntity(CreatedVoucherRequest createdVoucherRequest) {
        return modelMapper.map(createdVoucherRequest, Voucher.class);
    }

    public Voucher convertUpdateRequestToEntity(UpdatedVoucherRequest updateRequest, Voucher detail) {
        modelMapper.map(updateRequest, detail);
        return detail;
    }

}
