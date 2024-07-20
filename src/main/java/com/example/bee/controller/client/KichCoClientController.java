package com.example.bee.controller.client;


import com.example.bee.service.KichCoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin/api/kich-co")
public class KichCoClientController {

    @Autowired
    private KichCoService service;

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getKichCoKhongLap(@PathVariable(name = "id") Long idSanPham) {
        return ResponseEntity.ok(service.getKichCoKhongLap(idSanPham));
    }

}
