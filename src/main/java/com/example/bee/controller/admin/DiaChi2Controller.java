package com.example.bee.controller.admin;

import com.example.bee.service.DiaChiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin/api/dia-chi")
public class DiaChi2Controller {
    @Autowired
    private DiaChiService diaChiService;

    @GetMapping("/list")
    public ResponseEntity<?> findList(@RequestParam(value = "idTaiKhoan", required = false) Long idTaiKhoan) {
        return ResponseEntity.ok(diaChiService.findByListDiaChi(idTaiKhoan));
    }

    @GetMapping("/default")
    public ResponseEntity<?> getDiaChiDefaultByIdtaiKhoan(@RequestParam(name = "idTaiKhoan") Long idTaiKhoan) {
        return ResponseEntity.ok(diaChiService.getDiaChiDefaultByIDTaiKhoan(idTaiKhoan));
    }
}
