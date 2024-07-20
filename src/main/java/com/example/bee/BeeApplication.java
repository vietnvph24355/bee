package com.example.bee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeeApplication.class, args);
//        // Tạo mật khẩu và mã hóa nó
//        String rawPassword = "123";
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String encodedPassword = encoder.encode(rawPassword);
//
//        // In ra mật khẩu đã mã hóa
//        System.out.println("Mật khẩu gốc: " + rawPassword);
//        System.out.println("Mật khẩu đã mã hóa: " + encodedPassword);

    }

}
