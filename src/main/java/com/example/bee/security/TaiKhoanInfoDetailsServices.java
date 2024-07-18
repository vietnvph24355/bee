package com.example.bee.security;

import com.example.bee.entity.TaiKhoan;
import com.example.bee.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TaiKhoanInfoDetailsServices implements UserDetailsService {
    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    private TaiKhoanInfoDetails taiKhoanInfoDetails;

    @Override
    public UserDetails loadUserByUsername(String gmail) throws UsernameNotFoundException {
        Optional<TaiKhoan> optional = taiKhoanRepository.findByEmail(gmail);
        return optional.map(TaiKhoanInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("gmail not found"));
    }
}
