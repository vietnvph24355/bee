package com.example.bee.service.impl;

import com.example.bee.common.CommonEnum;
import com.example.bee.common.GenCode;
import com.example.bee.entity.GiaoDich;
import com.example.bee.entity.TaiKhoan;
import com.example.bee.exception.NotFoundException;
import com.example.bee.model.mapper.GiaoDichMapper;
import com.example.bee.model.request.create_request.CreateGiaoDichRequest;
import com.example.bee.model.request.update_request.UpdatedGiaoDichRequest;
import com.example.bee.model.response.GiaoDichResponse;
import com.example.bee.repository.GiaoDichRepository;
import com.example.bee.repository.TaiKhoanRepository;
import com.example.bee.service.GiaoDichService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GiaoDichServiceImpl implements GiaoDichService {

    @Autowired
    private GiaoDichRepository giaoDichRepository;

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private GiaoDichMapper giaoDichMapper;

    @Override
    public GiaoDichResponse add(CreateGiaoDichRequest request) {
        GiaoDich createGiaoDich = giaoDichMapper.convertCreateGiaoDichRequestToGiaoDichEntity(request);

        if (request.getTaiKhoan() != null) {
            TaiKhoan taiKhoan = taiKhoanRepository.findById(request.getTaiKhoan().getId()).orElse(null);
            createGiaoDich.setTaiKhoan(taiKhoan);
        }
        if(request.getNgayThanhToan()==null && request.getTrangThaiGiaoDich()== CommonEnum.TrangThaiGiaoDich.SUCCESS){
            createGiaoDich.setNgayThanhToan(LocalDateTime.now());
        }

        createGiaoDich.setMaGiaoDich(GenCode.generateGiaoDichCode());
        GiaoDich savedGiaoDich = giaoDichRepository.save(createGiaoDich);
        return giaoDichMapper.convertGiaoDichEntityToGiaoDichResponse(savedGiaoDich);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public GiaoDichResponse update(Long id, UpdatedGiaoDichRequest request) {
        GiaoDich giaoDich = giaoDichRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Giao dich không tồn tại"));
        if (request.getMaGiaoDich() != null) {
            giaoDich.setMaGiaoDich(request.getMaGiaoDich());
        }
        if (request.getSoTienGiaoDich() != null) {
            giaoDich.setSoTienGiaoDich(request.getSoTienGiaoDich());
        }
        if (request.getTrangThaiGiaoDich() != null) {
            giaoDich.setTrangThaiGiaoDich(request.getTrangThaiGiaoDich());
        }
        if (request.getHoaDon() != null) {
            giaoDich.setHoaDon(request.getHoaDon());
        }
        if (request.getTaiKhoan() != null) {
            giaoDich.setTaiKhoan(request.getTaiKhoan());
        }
        if (request.getPhuongThucThanhToan() != null) {
            giaoDich.setPhuongThucThanhToan(request.getPhuongThucThanhToan());
        }
        if(request.getNgayThanhToan()==null && request.getTrangThaiGiaoDich()== CommonEnum.TrangThaiGiaoDich.SUCCESS){
            giaoDich.setNgayThanhToan(LocalDateTime.now());
        }
        return giaoDichMapper.convertGiaoDichEntityToGiaoDichResponse(giaoDichRepository.save(giaoDich));
    }

    @Override
    public String updateByMa(String ma, String ngayThanhToan, CommonEnum.TrangThaiGiaoDich trangThaiGiaoDich) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        // Chuyển đổi chuỗi thành LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.parse(ngayThanhToan, formatter);
        Optional<GiaoDich> detail = giaoDichRepository.findByMaGiaoDich(ma);
        if (detail.isEmpty()) {
            throw new NotFoundException("Giao dịch không tồn tại trong hệ thống!");
        }
        GiaoDich giaoDich = detail.get();
        giaoDich.setTrangThaiGiaoDich(trangThaiGiaoDich);
        giaoDich.setNgayThanhToan(localDateTime);
        giaoDichRepository.save(giaoDich);
        if (giaoDich.getHoaDon().getLoaiHoaDon().getTen().equals("ONLINE") && giaoDich.getTaiKhoan() != null) {
            return "http://localhost:5173/don-hang";
        } else if (giaoDich.getHoaDon().getLoaiHoaDon().getTen().equals("ONLINE") && giaoDich.getTaiKhoan() == null) {
            return "http://localhost:5173/thong-tin-don-hang";
        } else if (giaoDich.getHoaDon().getLoaiHoaDon().getTen().equals("COUNTER")) {
            return "http://localhost:5173/admin/ban-hang-tai-quay";
        }
        return null;
    }


    @Override
    public List<GiaoDichResponse> getListGiaoDich(Long idHoaDon) {
        List<GiaoDich> giaoDichList = giaoDichRepository.findGiaoDichesByHoaDonId(idHoaDon);
        return giaoDichList
                .stream()
                .map(giaoDichMapper::convertGiaoDichEntityToGiaoDichResponse)
                .collect(Collectors.toList());
    }

    @Override
    public GiaoDichResponse findByMaGiaoDich(String maGiaoDich) {
        Optional<GiaoDich> optional = giaoDichRepository.findByMaGiaoDich(maGiaoDich);
        if (optional.isEmpty()) {
            throw new NotFoundException("Giao dịch không tồn tại");
        }

        return giaoDichMapper.convertGiaoDichEntityToGiaoDichResponse(optional.get());
    }

}