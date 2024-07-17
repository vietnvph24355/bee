package com.example.bee.model.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String tokens;

    private String refreshToken;

    private String gmail;

    private String name;

    private Long roleId;

    private String avatar;

    private Long acountId;


}
