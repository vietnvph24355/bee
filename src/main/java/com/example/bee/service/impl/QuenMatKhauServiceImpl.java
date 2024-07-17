package com.example.bee.service.impl;

import com.example.poly.datn.entity.TaiKhoan;
import com.example.poly.datn.model.request.QuenMatKhauRequest.QuenMatKhauRequest;
import com.example.poly.datn.repository.TaiKhoanRepository;
import com.example.poly.datn.service.QuenMatKhauService;
import com.example.poly.datn.utils.EmailSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class QuenMatKhauServiceImpl implements QuenMatKhauService {
    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private EmailSend emailSend;

    @Override
    public void sendEmail(TaiKhoan taiKhoan) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("beesport.fpoly@gmail.com");
        String subject = "[BEE SPORT] Chào mừng bạn đến với bee sport, đây là mật khẩu của bạn";
        String content = "Mật khẩu: " + emailSend.randomPasswords();
        content = content.replace("Mật khẩu: ", "");
        message.setTo(taiKhoan.getEmail());
        message.setSubject(subject);
        message.setText("Mật khẩu: " + content);
        taiKhoan.setMatKhau(new BCryptPasswordEncoder().encode(content));
        taiKhoanRepository.save(taiKhoan);

        emailSender.send(message);

    }

    @Override
    public TaiKhoan oldPassword(QuenMatKhauRequest request) {
        return taiKhoanRepository.findTaiKhoanByEmail(request.getEmail());
    }
}
