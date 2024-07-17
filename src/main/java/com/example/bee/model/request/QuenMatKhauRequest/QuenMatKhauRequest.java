package com.example.bee.model.request.QuenMatKhauRequest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuenMatKhauRequest {
    @NotBlank(message = "Vui lòng nhập emai")
    @Email(message = "Email không hợp lệ", regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    private String email;
}
