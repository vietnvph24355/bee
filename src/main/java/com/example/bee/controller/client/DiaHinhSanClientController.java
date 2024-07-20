package com.example.bee.controller.client;


import com.example.bee.service.DiaHinhSanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin/api/dia-hinh-san")
public class DiaHinhSanClientController {

    @Autowired
    private DiaHinhSanService service;

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getDiaHinhSanKhongLap(@PathVariable(name = "id") Long idSanPham) {
        return ResponseEntity.ok(service.getDiaHinhSanKhongLap(idSanPham));
    }

}
