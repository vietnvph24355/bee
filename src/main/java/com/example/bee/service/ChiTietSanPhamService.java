package com.example.bee.service;

import com.example.bee.model.request.create_request.CreatedChiTietSanPhamRequest;
import com.example.bee.model.request.update_request.UpdatedChiTietSanPhamRequest;
import com.example.bee.model.response.ChiTietSanPhamResponse;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public interface ChiTietSanPhamService {
    List<ChiTietSanPhamResponse> findByAll(Long idSanPham, Long idMauSac, Long idLoaiDe, Long idKichCo);//, Long idDiaHinhSan

    Page<ChiTietSanPhamResponse> findByAllPage(Integer page, Integer pageSize, Long idSanPham, Long idMauSac, Long idKichCo, Long idLoaiDe , BigDecimal minGiaTien, BigDecimal maxGiaTien);//, Long idDiaHinhSan

    List<ChiTietSanPhamResponse> getListChiTietSanPham();

    ChiTietSanPhamResponse findOne(Long idSanPham, Long idMauSac, Long idLoaiDe, Long idKichCo);//, Long idDiaHinhSan

    ChiTietSanPhamResponse add(CreatedChiTietSanPhamRequest request);

    List<ChiTietSanPhamResponse> addList(List<CreatedChiTietSanPhamRequest> requests);

    List<ChiTietSanPhamResponse> getListSanPhamAndMauSac(Long idSanPham, Long idMauSac);

    void delete(Long id);

    ChiTietSanPhamResponse updateTrangThai(Long id);

    void update(List<UpdatedChiTietSanPhamRequest> request);

    ChiTietSanPhamResponse getOneCtspById(Long id);

    Page<ChiTietSanPhamResponse> filterChiTietSanPham(
            Integer page,
            Integer pageSize,
            String sortField,
            String sortOrder,
            String searchText,
            BigDecimal minGiaTien,
            BigDecimal maxGiaTien,
            Long idLoaiDe,
            Long idMauSac,
            Long idKichCo,
//            Long idDiaHinhSan,
            Long idThuongHieu
    );
}
