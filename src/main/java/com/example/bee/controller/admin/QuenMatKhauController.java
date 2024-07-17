package com.example.bee.controller.admin;

import com.example.bee.entity.TaiKhoan;
import com.example.bee.model.request.QuenMatKhauRequest.QuenMatKhauRequest;
import com.example.bee.service.QuenMatKhauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class QuenMatKhauController {

    @Autowired
    private QuenMatKhauService quenMatKhauService;


    @PostMapping("/forgot-password")
    public ResponseEntity<?> fotgotPassword(@RequestBody QuenMatKhauRequest request){
        TaiKhoan taiKhoan = quenMatKhauService.oldPassword(request);

        if (taiKhoan != null) {
            quenMatKhauService.sendEmail(taiKhoan);
            System.out.println(taiKhoan.getEmail());
            return ResponseEntity.ok("Email sent successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Không tìm thấy tài khoản với email đã cung cấp.");

        }
    }
}
