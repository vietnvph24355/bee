package com.example.bee.controller.admin;

import com.example.bee.entity.DiaChi;
import com.example.bee.model.request.create_request.CreatedDiaChiRequest;
import com.example.bee.model.request.update_request.UpdateDCReuest;
import com.example.bee.model.request.update_request.UpdatedDiaChiRequest;
import com.example.bee.service.DiaChiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/dia-chi")
public class DiaChiController {
    @Autowired
    private DiaChiService diaChiService;

    @GetMapping()
    public ResponseEntity<?> getAll(
            @RequestParam(value = "currentPage", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "searchText", defaultValue = "", required = false) String searchText,
            @RequestParam(value = "trangThaiDiaChi", defaultValue = "", required = false) String trangThaiDiaChi,
            @RequestParam(value = "sortField", defaultValue = "", required = false) String sorter,
            @RequestParam(value = "sortOrder", defaultValue = "", required = false) String sortOrder,
            @RequestParam("taiKhoanId") Long taiKhoanId
    ) {
        return ResponseEntity.ok(diaChiService.getAll(page, pageSize, sorter, sortOrder, trangThaiDiaChi, searchText, taiKhoanId));
    }

    //
    @PostMapping("/add")
    public ResponseEntity<?> addDiaChi(@RequestParam("id") Long id, @RequestBody CreatedDiaChiRequest createDCRequest) {
        return new ResponseEntity<>(diaChiService.add(id, createDCRequest), HttpStatus.CREATED);
    }

    //
    @GetMapping("/list")
    public ResponseEntity<?> findList(@RequestParam(value = "idTaiKhoan", required = false) Long idTaiKhoan) {
        return ResponseEntity.ok(diaChiService.findByListDiaChi(idTaiKhoan));
    }

    //
    @GetMapping("/edit/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(diaChiService.findById(id));
    }

    //
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody UpdatedDiaChiRequest request) {
        DiaChi diaChi = diaChiService.update(id, request);
        return ResponseEntity.ok(diaChi);
    }

    //
    @PutMapping("/updateTT/{id}")
    public ResponseEntity<?> updateTT(@PathVariable("id") Long id, @RequestBody UpdateDCReuest reuest){
        DiaChi diaChi = diaChiService.updateTrangThai(id, reuest);
        return ResponseEntity.ok(diaChi);
    }

    //
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Long id){
        diaChiService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
