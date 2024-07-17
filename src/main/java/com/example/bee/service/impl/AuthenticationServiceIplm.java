package com.example.bee.service.impl;

import com.example.bee.common.CommonEnum;
import com.example.bee.entity.TaiKhoan;
import com.example.bee.entity.VaiTro;
import com.example.bee.exception.BadRequestException;
import com.example.bee.model.dto.JwtAuthenticationResponse;
import com.example.bee.model.dto.RefreshTokenRequest;
import com.example.bee.model.dto.SignUpRequest;
import com.example.bee.model.dto.SigninRequest;
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
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private GioHangRepository gioHangRepository;

    private TaiKhoanInfoDetailsServices user;

    private AuthenticationManager authenticationManager;


    private JWTService jwtService;

    @Autowired
    private EmailSend emailSender;

    public AuthenticationServiceIplm(TaiKhoanInfoDetailsServices user,AuthenticationManager authenticationManager,  JWTService jwtService) {
        this.user = user;
        this.authenticationManager= authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public TaiKhoan signup(SignUpRequest signUpRequest) {
        TaiKhoan soDienThoai = userRepository.findBySoDienThoai(signUpRequest.getSoDienThoai());
        if (soDienThoai != null) {
            throw new BadRequestException("Số điện thoại đã tồn tại trong hệ thống!");
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
        emailSender.sendEmail(taiKhoan);
//        GioHang gioHang = new GioHang();
//        gioHang.setMaGioHang(GenCode.generateGioHangCode());
//        gioHang.setTrangThai(1);
//        gioHang.setTaiKhoan(userRepository.getOne(taiKhoan.getId()));
//        gioHangRepository.save(gioHang);
        taiKhoan.setMatKhau(passwordEncoder.encode(taiKhoan.getMatKhau()));
        userRepository.save(taiKhoan);
        return taiKhoan;
    }

    @Override
    public boolean addAdmin() {
        Optional<VaiTro> roleId = roleRepository.findById(Long.valueOf(1));
        VaiTro role = roleId.get();
        TaiKhoan user = new TaiKhoan();
        user.setHoVaTen("hoanggiang");
        user.setSoDienThoai("0348079280");
        user.setEmail("giangminh0302@gmail.com");
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
                    signinRequest.getMatKhau()));
        } catch (AuthenticationException e) {
            // Invalid credentials
            throw new BadRequestException("Tài khoản hoặc mật khẩu không tồn tại.");
        }

        TaiKhoan taiKhoan = userRepository.findBySoDienThoai(signinRequest.getEmail());
//        GioHang gioHang = gioHangRepository.getOne(taiKhoan.getId());
        var userToke = user.loadUserByUsername(signinRequest.getEmail());
        var jwt = jwtService.generateToken(userToke);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), userToke);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        jwtAuthenticationResponse.setRoleId(taiKhoan.getVaiTro().getId());
        jwtAuthenticationResponse.setAcountId(taiKhoan.getId());
        jwtAuthenticationResponse.setSdt(taiKhoan.getSoDienThoai());
        jwtAuthenticationResponse.setSdt(taiKhoan.getEmail());
        jwtAuthenticationResponse.setTen(taiKhoan.getHoVaTen());
//        jwtAuthenticationResponse.setIdGioHang(gioHang.getId());
        return jwtAuthenticationResponse;
    }

    @Override
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        UserDetails userToken = user.loadUserByUsername(userEmail);

        if(jwtService.isTokenValid(refreshTokenRequest.getToken(),userToken)){
            var jwt = jwtService.generateToken(userToken);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());

            return jwtAuthenticationResponse;
        }


        return null;
    }
}
