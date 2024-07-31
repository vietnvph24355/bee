package com.example.bee.controller.client;

import com.example.bee.service.DonHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/client/api/don-hang")
public class DonHangClientController {

    @Autowired
    private DonHangService service;

    @GetMapping()
    public ResponseEntity<?> getAllDonHangCuaTaiKhoan(
            @RequestParam(value = "taiKhoanId") Long id,
            @RequestParam(value = "trangThai", defaultValue = "") String trangThai
    ) {
        return ResponseEntity.ok(service.getAllHoaDonCuaTaiKhoan(id, trangThai));
    }

    @GetMapping("/{ma}")
    public ResponseEntity<?> getOneDonHang(
            @PathVariable(name = "ma")String maHoaDon
    ) {
        return ResponseEntity.ok(service.getOneDonHang(maHoaDon));
    }

    @GetMapping("/count")
    public ResponseEntity<?> countSoHoaDon(
            @RequestParam(value = "taiKhoanId") Long id,
            @RequestParam(value = "trangThai", defaultValue = "") String trangThai
    ) {
        return ResponseEntity.ok(service.countSoHoaDon(id, trangThai));
    }

    @GetMapping("/sendEmail/{id}")
    public ResponseEntity<?> sendEmailDonHang(@PathVariable("id") Long id) {
        service.sendEmailDonHang(id);
        return ResponseEntity.ok("Email sent successfully.");
    }

}
