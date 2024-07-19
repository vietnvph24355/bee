package com.example.bee.controller.admin;


import com.example.bee.model.request.create_request.CreatedLoaiDeRequest;
import com.example.bee.model.request.update_request.UpdatedLoaiDeRequest;
import com.example.bee.service.LoaiDeService;
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

@CrossOrigin("*")
@RestController
@RequestMapping("/admin/api/loai-de")
public class LoaiDeController {

    @Autowired
    private LoaiDeService service;

    @GetMapping("/list")
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(service.getLoaiDeByNgayTaoDESC());
    }

    @GetMapping()
    public ResponseEntity<?> getAll(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(name = "sortField", required = false) String sortField,
            @RequestParam(name = "sortOrder", defaultValue = "", required = false) String sortOrder,
            @RequestParam(name = "searchText", defaultValue = "") String searchText,
            @RequestParam(name = "trangThai", required = false) String trangThaiString
    ) {
        return ResponseEntity.ok(service.getAll(page, pageSize, sortField, sortOrder, searchText, trangThaiString));
    }

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody CreatedLoaiDeRequest request) {
        return new ResponseEntity<>(service.add(request), HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody UpdatedLoaiDeRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

}
