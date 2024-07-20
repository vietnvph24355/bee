package com.example.bee.model.dto;

import com.example.bee.model.response.HoaDonChiTietResponse;
import com.example.bee.model.response.HoaDonResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HoaDonHoaDonChiTietListResponseDTO {

    private HoaDonResponse hoaDonResponse;

    private Page<HoaDonChiTietResponse> hoaDonChiTietResponsePage;
}
