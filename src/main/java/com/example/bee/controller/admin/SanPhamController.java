package com.example.bee.controller.admin;

import com.example.bee.model.request.create_request.CreatedSanPhamRequest;
import com.example.bee.model.request.update_request.UpdatedSanPhamRequest;
import com.example.bee.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin/api/san-pham")
public class SanPhamController {

    @Autowired
    private SanPhamService service;

    @GetMapping()
    public ResponseEntity<?> getAll(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(name = "sortField", required = false) String sortField,
            @RequestParam(name = "sortOrder", defaultValue = "", required = false) String sortOrder,
            @RequestParam(name = "searchText", defaultValue = "") String searchText,
            @RequestParam(name = "thuongHieuId", defaultValue = "") Long thuongHieuId,
            @RequestParam(name = "trangThai", required = false) String trangThaiString
    ) {
        return ResponseEntity.ok(service.getAll(page, pageSize, sortField, sortOrder, searchText, thuongHieuId, trangThaiString));
    }

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody CreatedSanPhamRequest request) {
        return new ResponseEntity<>(service.add(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody UpdatedSanPhamRequest request) {
//        return ResponseEntity.ok(service.update(id, request));
//    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody UpdatedSanPhamRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
    @GetMapping("/san-pham-moi")
    public ResponseEntity<?> get5SanPhamMoiNhat() {
        return ResponseEntity.ok(service.get5SanPhamMoiNhat());
    }

    @GetMapping("/gia-tien-moi-nhat")
    public ResponseEntity<?> giaTien5SanPhamMoiNhat() {
        return ResponseEntity.ok(service.giaTien5SanPhamMoiNhat());
    }

    @GetMapping("/null-ctsp")
    public ResponseEntity<?> getSanPhamNullCTSP() {
        return ResponseEntity.ok(service.getAllSanPhamNullCTSP());
    }

}
