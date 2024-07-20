package com.example.bee.controller.admin;


import com.example.bee.service.TimeLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin/api/timeline")
public class TimelineController {

    @Autowired
    private TimeLineService timeLineService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllListByHoaDonId(@PathVariable(name = "id")Long id) {
        return ResponseEntity.ok(timeLineService.getAllList(id));
    }

}
