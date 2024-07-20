package com.example.bee.service.impl;

import com.example.bee.entity.TimeLine;
import com.example.bee.model.mapper.HoaDonMapper;
import com.example.bee.model.mapper.TimelineMapper;
import com.example.bee.model.response.TimelineResponse;
import com.example.bee.repository.TimelineRepository;
import com.example.bee.service.TimeLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class TimeLineServiceImpl implements TimeLineService {
    @Autowired
    private HoaDonMapper hoaDonMapper;

    @Autowired
    private TimelineRepository timelineRepository;

    @Autowired
    private TimelineMapper timelineMapper;

    @Override
    public List<TimelineResponse> getAllList(Long id) {
        List<TimeLine> list = timelineRepository.findTimeLinesByHoaDonId(id);
        return list.stream()
                .map(timelineMapper::convertEntityToResponse)
                .collect(Collectors.toList());
    }
}
