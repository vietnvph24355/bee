package com.example.bee.model.mapper;

import com.example.bee.entity.ChiTietSanPham;
import com.example.bee.model.request.create_request.CreatedChiTietSanPhamRequest;
import com.example.bee.model.request.update_request.UpdatedChiTietSanPhamRequest;
import com.example.bee.model.response.ChiTietSanPhamResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ChiTietSanPhamMapper {
    @Autowired
    private ModelMapper mapper;

    public ChiTietSanPhamResponse convertEntityToResponse(ChiTietSanPham chiTietSanPham) {
        if (chiTietSanPham != null) {
            mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
            return mapper.map(chiTietSanPham, ChiTietSanPhamResponse.class);
        } else {
            System.out.println("HQL null");
            return null;
        }
    }
    public List<ChiTietSanPhamResponse> toResponseList(List<ChiTietSanPham> chiTietSanPhams) {
        List<ChiTietSanPhamResponse> responses = new ArrayList<>();
        for (ChiTietSanPham chiTietSanPham : chiTietSanPhams) {
            responses.add(convertEntityToResponse(chiTietSanPham));
        }
        return responses;
    }
    public ChiTietSanPham convertCreateRequestToEntity(CreatedChiTietSanPhamRequest request) {
        ChiTietSanPham chiTietSanPham = new ChiTietSanPham();

        chiTietSanPham.setSoLuong(request.getSoLuong());
        chiTietSanPham.setGiaTien(request.getGiaTien());
        chiTietSanPham.setNgayTao(request.getNgayTao());
        chiTietSanPham.setNgaySua(request.getNgaySua());
        chiTietSanPham.setNguoiTao(request.getNguoiTao());
        chiTietSanPham.setNguoiSua(request.getNguoiSua());
        chiTietSanPham.setTrangThai(request.getTrangThai());
        chiTietSanPham.setLoaiDe(request.getLoaiDe());
        chiTietSanPham.setDiaHinhSan(request.getDiaHinhSan());
        chiTietSanPham.setSanPham(request.getSanPham());
        chiTietSanPham.setMauSac(request.getMauSac());
        chiTietSanPham.setKichCo(request.getKichCo());

        return chiTietSanPham;
    }

    public void convertUpdateRequestToEntity(UpdatedChiTietSanPhamRequest request, ChiTietSanPham chiTietSanPham) {
        // Copy các thuộc tính cơ bản từ request sang entity
        chiTietSanPham.setSoLuong(request.getSoLuong());
        chiTietSanPham.setGiaTien(request.getGiaTien());
        chiTietSanPham.setNguoiTao(request.getNguoiTao());
        chiTietSanPham.setNguoiSua(request.getNguoiSua());
        chiTietSanPham.setTrangThai(request.getTrangThai());
        chiTietSanPham.setLoaiDe(request.getLoaiDe());
        chiTietSanPham.setDiaHinhSan(request.getDiaHinhSan());
        chiTietSanPham.setSanPham(request.getSanPham());
        chiTietSanPham.setMauSac(request.getMauSac());
        chiTietSanPham.setKichCo(request.getKichCo());

        // Cài đặt ngayTao và ngaySua theo logic của bạn
        LocalDateTime now = LocalDateTime.now();
        chiTietSanPham.setNgayTao(chiTietSanPham.getNgayTao());
        chiTietSanPham.setNgaySua(now);
    }

}
