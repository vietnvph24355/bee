package com.example.bee.model.request.create_request;

import com.example.bee.common.CommonEnum;
import com.example.bee.entity.VaiTro;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class CreatedTaiKhoanRequest {
    @NotBlank(message = "Vui lòng nhập họ và tên")
    @Pattern(message = "Họ và tên không hợp lệ", regexp = "^[\\p{L}\\s]+$")
    private String hoVaTen;

    @NotBlank(message = "Vui lòng điền số căn cước công dân")
    private String canCuocCongDan;

    @NotNull(message = "Vui lòng chọn ngày sinh")
    @PastOrPresent(message = "Ngày sinh không thể trong tương lai")
    private LocalDate ngaySinh;

    @NotNull(message = "Vui lòng chọn giới tính")
    private CommonEnum.GioiTinh gioiTinh;

    @NotBlank(message = "Vui lòng nhập số điện thoại")
    @Pattern(message = "Số điện thoại không hợp lệ", regexp = "^0[35789]\\d{8}$")
    private String soDienThoai;

    @NotBlank(message = "Vui lòng nhập emai")
    @Email(message = "Email không hợp lệ", regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    private String email;

    @NotBlank(message = "Vui lòng chọn tỉnh/thành phố")
    private String thanhPho;

    @NotBlank(message = "Vui lòng chọn quận/huyện")
    private String quanHuyen;

    @NotBlank(message = "Vui lòng chọn phường/xã")
    private String phuongXa;

    @NotBlank(message = "Vui lòng điền địa chỉ cụ thể")
    private String diaChiCuThe;

    private MultipartFile flieAnhDaiDien;

    private String matKhau;

    private CommonEnum.TrangThaiThuocTinh trangThai;

    private VaiTro vaiTro;
}
