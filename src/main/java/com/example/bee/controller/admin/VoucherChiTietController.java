package com.example.bee.controller.admin;


import com.example.bee.model.request.create_request.CreatedVoucherChiTietRequest;
import com.example.bee.model.request.update_request.UpdatedVoucherChiTietRequest;
import com.example.bee.service.VoucherChiTietService;
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
@RequestMapping("/admin/api/voucher-chi-tiet")
public class VoucherChiTietController {

    @Autowired
    private VoucherChiTietService service;

    @GetMapping("/page")
    public ResponseEntity<?> getAllPage(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        return ResponseEntity.ok(service.getAllPage(page, pageSize));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllList(@RequestParam(name = "idVoucher",required = false)Long idVoucher) {
        return ResponseEntity.ok(service.findByVoucherId(idVoucher));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody List<CreatedVoucherChiTietRequest> requests) {
        return new ResponseEntity<>(service.addList(requests), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody List<UpdatedVoucherChiTietRequest> request) {
        service.update(request);
        return ResponseEntity.ok("Danh sách ChiTietSanPham đã được cập nhật thành công.");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    

}
