package com.example.bee.model.mapper;


import com.example.bee.entity.KichCo;
import com.example.bee.model.request.create_request.CreatedKichCoRequest;
import com.example.bee.model.request.update_request.UpdatedKichCoRequest;
import com.example.bee.model.response.KichCoResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KichCoMapper {

    @Autowired
    private ModelMapper mapper;

    public KichCoResponse convertEntityToResponse(KichCo kichCo) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return mapper.map(kichCo, KichCoResponse.class);
    }

    public KichCo convertCreateRequestToEntity(CreatedKichCoRequest request) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return mapper.map(request, KichCo.class);
    }

    public void convertUpdateRequestToEntity(UpdatedKichCoRequest request, KichCo kichCo) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        mapper.map(request, kichCo);
    }

}
