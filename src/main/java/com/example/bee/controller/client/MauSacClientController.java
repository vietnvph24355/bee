package com.example.bee.controller.client;


import com.example.bee.service.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin/api/mau-sac")
public class MauSacClientController {

    @Autowired
    private MauSacService service;

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getMauSacKhongLap(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(service.getMauSacKhongLap(id));
    }

}