package com.example.bee.service.impl;

import com.example.bee.common.CommonEnum;
import com.example.bee.common.GenCode;
import com.example.bee.entity.GioHang;
import com.example.bee.entity.TaiKhoan;
import com.example.bee.entity.VaiTro;
import com.example.bee.exception.BadRequestException;
import com.example.bee.model.dto.JwtAuthenticationResponse;
import com.example.bee.model.dto.RefreshTokenRequest;
import com.example.bee.model.dto.SignUpRequest;
import com.example.bee.model.dto.SigninRequest;
import com.example.bee.repository.GioHangRepository;
import com.example.bee.repository.TaiKhoanRepository;
import com.example.bee.repository.VaiTroRepository;
import com.example.bee.security.TaiKhoanInfoDetailsServices;
import com.example.bee.service.AuthenticationService;
import com.example.bee.service.JWTService;
import com.example.bee.utils.EmailSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class AuthenticationServiceIplm implements AuthenticationService {

    @Autowired
    private TaiKhoanRepository userRepository;

    @Autowired
    private VaiTroRepository roleRepository;

    @Autowired
    private GioHangRepository gioHangRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private TaiKhoanInfoDetailsServices service;

    private JWTService jwtService;

    private AuthenticationManager authenticationManager;

    @Autowired
    private EmailSend sendEmailService;


    public AuthenticationServiceIplm(TaiKhoanInfoDetailsServices service,AuthenticationManager authenticationManager,  JWTService jwtService) {
        this.service = service;
        this.authenticationManager= authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public TaiKhoan signup(SignUpRequest signUpRequest) {
        TaiKhoan email = userRepository.findTaiKhoanByEmail(signUpRequest.getEmail());
        if (email != null) {
            throw new BadRequestException("E-Mail đã tồn tại trong hệ thống!");
        }
        if(signUpRequest.getGioiTinh()==null){
            signUpRequest.setGioiTinh(CommonEnum.GioiTinh.OTHER);
        }
        TaiKhoan user = new TaiKhoan();

        user.setHoVaTen(signUpRequest.getHoVaTen());
        user.setSoDienThoai(signUpRequest.getSoDienThoai());
        user.setEmail(signUpRequest.getEmail());
        user.setVaiTro(roleRepository.findId(Long.valueOf(3)));
        user.setTrangThai(CommonEnum.TrangThaiThuocTinh.ACTIVE);
        user.setAnhDaiDien("defaultAvatar.jpg");
        if(user.getGioiTinh()==null){
            user.setGioiTinh(CommonEnum.GioiTinh.OTHER);
        }
        user.setMatKhau(passwordEncoder.encode(signUpRequest.getMatKhau()));
        TaiKhoan taiKhoan = userRepository.save(user);
        taiKhoan.setMatKhau(signUpRequest.getMatKhau());
        sendEmailService.sendEmail(taiKhoan);
        GioHang gioHang = new GioHang();
        gioHang.setMaGioHang(GenCode.generateGioHangCode());
        gioHang.setTrangThai(1);
        gioHang.setTaiKhoan(userRepository.getOne(taiKhoan.getId()));
        gioHangRepository.save(gioHang);
        taiKhoan.setMatKhau(passwordEncoder.encode(taiKhoan.getMatKhau()));
        userRepository.save(taiKhoan);
        return taiKhoan;
    }

    @Override
    public boolean addAdmin() {
        Optional<VaiTro> roleId = roleRepository.findById(Long.valueOf(1));
        VaiTro role = roleId.get();
        TaiKhoan user = new TaiKhoan();
        user.setHoVaTen("NguyenViet");
        user.setSoDienThoai("0867291082");
        user.setEmail("viet01232003@gmail.com");
        user.setVaiTro(role);
        user.setTrangThai(CommonEnum.TrangThaiThuocTinh.ACTIVE);
        user.setAnhDaiDien("defaultAvatar.jpg");
        if(user.getGioiTinh()==null){
            user.setGioiTinh(CommonEnum.GioiTinh.OTHER);
        }
        user.setMatKhau(new BCryptPasswordEncoder().encode("Aa123123@"));
        userRepository.save(user);
//        GioHang gioHang = new GioHang();
//        gioHang.setMaGioHang(GenCode.generateGioHangCode());
//        gioHang.setTrangThai(1);
//        gioHang.setTaiKhoan(userRepository.getOne(user.getId()));
//        gioHangRepository.save(gioHang);
        return true;
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest signinRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(),
                    signinRequest.getPassword()));
        } catch (AuthenticationException e) {
            // Invalid credentials
            throw new BadRequestException("Tài khoản hoặc mật khẩu không tồn tại.");
        }

        TaiKhoan taiKhoan = userRepository.findGmail1(signinRequest.getEmail());
//        GioHang gioHang = gioHangRepository.getOne(taiKhoan.getId());
        var userToke = service.loadUserByUsername(signinRequest.getEmail());
        var jwt = jwtService.generateToken(userToke);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), userToke);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setTokens(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        jwtAuthenticationResponse.setRoleId(taiKhoan.getVaiTro().getId());
        jwtAuthenticationResponse.setAcountId(taiKhoan.getId());
        jwtAuthenticationResponse.setGmail(taiKhoan.getEmail());
        jwtAuthenticationResponse.setTen(taiKhoan.getHoVaTen());
        jwtAuthenticationResponse.setAvatar(taiKhoan.getAnhDaiDien());
//        jwtAuthenticationResponse.setIdGioHang(gioHang.getId());
        return jwtAuthenticationResponse;
    }

    @Override
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        UserDetails userToken = service.loadUserByUsername(userEmail);

        if(jwtService.isTokenValid(refreshTokenRequest.getToken(),userToken)){
            var jwt = jwtService.generateToken(userToken);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setTokens(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());

            return jwtAuthenticationResponse;
        }


        return null;
    }
}
