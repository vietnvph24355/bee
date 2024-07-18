package com.example.bee.security;

import com.example.bee.entity.TaiKhoan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TaiKhoanInfoDetails implements UserDetails {
    private String gmail;
    private String password;
    List<GrantedAuthority> authorities;

    public TaiKhoanInfoDetails(TaiKhoan taiKhoan){
        this.gmail = taiKhoan.getEmail();
        this.password = taiKhoan.getMatKhau();
        this.authorities = Arrays.stream(taiKhoan.getVaiTro().getTen().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return gmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
