package com.example.bee.controller.admin;

import com.example.bee.exception.BadRequestException;
import com.example.bee.model.request.create_request.CreateHoaDonChiTietRequest;
import com.example.bee.model.request.update_request.UpdatedHoaDonChiTietRequest;
import com.example.bee.service.HoaDonChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin/api/hoa-don-chi-tiet")
public class HoaDonChiTietController {

    @Autowired
    private HoaDonChiTietService service;

    @PostMapping("/add-list")
    public ResponseEntity<?> addList(@RequestBody List<CreateHoaDonChiTietRequest> requestList) {
        service.addList(requestList);
        return ResponseEntity.ok("Thành công");
    }

    @PutMapping("/update-list")
    public ResponseEntity<?> updateList(@RequestBody List<UpdatedHoaDonChiTietRequest> requestList) {
        System.out.println(requestList);
        service.updateList(requestList);
        return ResponseEntity.ok("Thành công");
    }

    @GetMapping("/so-luong/{id}")
    public ResponseEntity<?> updateHoaDonChiTietSoLuong(@PathVariable(name = "id") Long id, @RequestParam(name = "soLuong", defaultValue = "0") Integer soLuong) {
        try {
            return ResponseEntity.ok(service.updateSoLuong(id, soLuong));
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}

