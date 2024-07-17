package com.example.bee.controller.admin;

import com.example.bee.entity.TaiKhoan;
import com.example.bee.model.dto.*;
import com.example.bee.model.request.update_request.UpdatedTaiKhoanRequest;
import com.example.bee.model.response.TaiKhoanResponse;
import com.example.bee.service.AuthenticationService;
import com.example.bee.service.TaiKhoanService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @Autowired
    private TaiKhoanService taiKhoanService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    // Dang ky
    @PostMapping("/sign-up")
    public ResponseEntity<TaiKhoan> signup(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.signup(signUpRequest));
    }

    //Dang nhap
    @PostMapping("/sign-in")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest signinRequest){
        return ResponseEntity.ok(authenticationService.signin(signinRequest));
    }

    //Them moi admin
    @GetMapping("/admin")
    public ResponseEntity<?> add(){
        return ResponseEntity.ok(authenticationService.addAdmin());
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response, HttpServletRequest request) {
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setDomain(request.getServerName());
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return ResponseEntity.ok("Đăng xuất thành công");
    }

    @PostMapping("/doi-mat-khau")
    public ResponseEntity<?> doiMatKhau(@RequestBody PasswordRequest passwordRequest){
        return ResponseEntity.ok(taiKhoanService.changePassword(passwordRequest));
    }

    @GetMapping("/editTT/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id")Long id){
        return ResponseEntity.ok(taiKhoanService.findById(id));
    }

    @PutMapping("/updateTT/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody UpdatedTaiKhoanRequest request) {
        TaiKhoanResponse taiKhoan = taiKhoanService.updateKhachHang(id, request);
        return ResponseEntity.ok(taiKhoan);
    }
}
