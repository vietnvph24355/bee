package com.example.bee.controller.admin;

import com.example.bee.model.request.create_request.CreatedTaiKhoanRequest;
import com.example.bee.model.request.update_request.UpdatedTaiKhoanRequest;
import com.example.bee.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin/api/tai-khoan")
public class TaiKhoanController {

    @Autowired
    private TaiKhoanService taiKhoanService;

    @GetMapping()
    public ResponseEntity<?> getAll(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(name = "sortField", required = false) String sortField,
            @RequestParam(name = "sortOrder", defaultValue = "", required = false) String sortOrder,
            @RequestParam(name = "searchText", defaultValue = "") String searchText,
            @RequestParam(name = "gioiTinh", required = false) String gioiTinhString,
            @RequestParam(name = "trangThai", required = false) String trangThaiString

    ) {
        return ResponseEntity.ok(taiKhoanService.getAll(page, pageSize,sortField,sortOrder, searchText,gioiTinhString, trangThaiString));
    }

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody CreatedTaiKhoanRequest request) {
        return new ResponseEntity<>(taiKhoanService.add(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        taiKhoanService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(taiKhoanService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id,@RequestBody UpdatedTaiKhoanRequest request) {
        return ResponseEntity.ok(taiKhoanService.update(id, request));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll11(@RequestParam("email")String email) {
        return ResponseEntity.ok(taiKhoanService.getAllTaiKhoan(email));
    }
}
