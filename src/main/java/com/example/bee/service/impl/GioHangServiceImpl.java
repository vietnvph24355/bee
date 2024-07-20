package com.example.bee.service.impl;

import com.example.bee.entity.GioHang;
import com.example.bee.exception.NotFoundException;
import com.example.bee.model.mapper.GioHangMapper;
import com.example.bee.model.request.create_request.CreatedGioHangRequest;
import com.example.bee.model.request.update_request.UpdatedGioHangRequest;
import com.example.bee.model.response.GioHangResponse;
import com.example.bee.repository.GioHangRepository;
import com.example.bee.service.GioHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class GioHangServiceImpl implements GioHangService {

    @Autowired
    private GioHangRepository repository;

    @Autowired
    private GioHangMapper mapper;


    @Override
    public GioHangResponse create(CreatedGioHangRequest request) {
        GioHang createdGioHang = mapper.convertCreateRequestToEntity(request);

        // Tự GEN mã
        String prefix = "GH";
        String code = prefix;

        // Tạo một mảng boolean để theo dõi các số đã sử dụng
        boolean[] usedNumbers = new boolean[10];

        // Tạo một đối tượng Random để tạo số ngẫu nhiên
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            int digit;
            do {
                // Tạo số ngẫu nhiên từ 0 đến 9
                digit = random.nextInt(10);
            } while (usedNumbers[digit]);

            // Đánh dấu số đã sử dụng
            usedNumbers[digit] = true;

            // Thêm số vào mã
            code += digit;
        }
        createdGioHang.setMaGioHang(code);
        createdGioHang.setTrangThai(1);
        GioHang savedGioHang = this.repository.save(createdGioHang);
        return mapper.convertEntityToResponse(savedGioHang);
    }

    @Override
    public GioHangResponse findByIdTK(Long id) {
        GioHang optional = repository.getOne(id);
        if (optional == null) {
            throw new NotFoundException("Giỏ hàng không tồn tại");
        }
        return mapper.convertEntityToResponse(optional);
    }

    @Override
    public GioHangResponse findById(Long id) {
        Optional<GioHang> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Giỏ hàng không tồn tại");
        }
        return mapper.convertEntityToResponse(optional.get());
    }

    @Override
    public GioHangResponse update(Long id, UpdatedGioHangRequest request) {
        Optional<GioHang> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException(("Giỏ hàng không tồn tại"));
        }
        GioHang gioHang = optional.get();
        mapper.convertUpdateRequestToEntity(request, gioHang);
        return mapper.convertEntityToResponse(repository.save(gioHang));
    }
}
