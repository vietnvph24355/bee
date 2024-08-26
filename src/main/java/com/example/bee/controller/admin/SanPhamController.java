package com.example.bee.controller.admin;

import com.example.bee.exception.BadRequestException;
import com.example.bee.model.request.create_request.CreatedSanPhamRequest;
import com.example.bee.model.request.update_request.UpdatedSanPhamRequest;
import com.example.bee.service.SanPhamService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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

@CrossOrigin("*")
@RestController
@RequestMapping("/admin/api/san-pham")
public class SanPhamController {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
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
    public ResponseEntity<?> add(@RequestBody @Valid CreatedSanPhamRequest request, BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMesage = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMesage);
            }
            return new ResponseEntity<>(service.add(request), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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
