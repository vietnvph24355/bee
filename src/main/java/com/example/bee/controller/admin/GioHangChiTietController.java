package com.example.bee.controller.admin;

import com.example.bee.model.request.create_request.CreatedGioHangChiTietRequest;
import com.example.bee.model.request.update_request.UpdatedGioHangChiTietRequest;
import com.example.bee.service.GioHangChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin/api/gio-hang-chi-tiet")
public class GioHangChiTietController {
    @Autowired
    private GioHangChiTietService service;

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody CreatedGioHangChiTietRequest request) {
        return new ResponseEntity<>(service.add(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<?> deleteALL(@RequestParam(name = "idGioHang")Long idGioHang) {
        service.deleteAll(idGioHang);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody UpdatedGioHangChiTietRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateGioHang(@RequestBody List<UpdatedGioHangChiTietRequest> request) {
        service.updateGioHang(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/detail-gio-hang/{id}")
    public ResponseEntity<?> getListGioHangChiTietByGioHangId(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(service.getListGioHangChiTietByGioHangId(id));
    }

}
