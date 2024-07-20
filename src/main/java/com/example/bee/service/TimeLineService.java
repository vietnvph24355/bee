package com.example.bee.service;

import com.example.bee.model.response.TimelineResponse;

import java.util.List;

public interface TimeLineService {
    List<TimelineResponse> getAllList(Long id);
}
