package com.example.bee.service.impl;

import com.example.bee.common.CommonEnum;
import com.example.bee.entity.ChiTietSanPham;
import com.example.bee.entity.SanPham;
import com.example.bee.exception.BadRequestException;
import com.example.bee.exception.NotFoundException;
import com.example.bee.model.mapper.SanPhamMapper;
import com.example.bee.model.request.create_request.CreatedSanPhamRequest;
import com.example.bee.model.request.update_request.UpdatedSanPhamRequest;
import com.example.bee.model.response.*;
import com.example.bee.repository.*;
import com.example.bee.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SanPhamServiceImpl implements SanPhamService {
    @Autowired
    private SanPhamRepository repository;

    @Autowired
    private ThuongHieuRepository  thuongHieuRepository;

    @Autowired
    private MauSacRepository mauSacRepository;

    @Autowired
    private DiaHinhSanRepository diaHinhSanRepository;

    @Autowired
    private LoaiDeRepository loaiDeRepository;

    @Autowired
    private KichCoRepository kichCoRepository;

    @Autowired
    private SanPhamMapper mapper;

    @Override
    public Page<SanPhamResponse> getAll(Integer page, Integer pageSize, String sortField, String sortOrder, String searchText, Long thuongHieuId, String trangThaiString) {
        Sort  sort;
        if ("ascend".equals(sortOrder)) {
            sort = Sort.by(sortField).ascending();
        } else if ("descend".equals(sortOrder)) {
            sort = Sort.by(sortField).descending();
        } else {
            sort = Sort.by("ngayTao").descending();
        }

        CommonEnum.TrangThaiSanPham trangThai;

        if (trangThaiString == null || trangThaiString.equals("")) {
            trangThai = null;
        } else {
            trangThai = CommonEnum.TrangThaiSanPham.valueOf(trangThaiString);
        }

        Pageable pageable = PageRequest.of(page - 1, pageSize, sort);
        Page<SanPham> pageSanPham = repository.findByAll(pageable, searchText, thuongHieuId, trangThai);
        //THÊM MỚI
        pageSanPham.getContent().forEach(sanPham -> {
            // Tính tổng số lượng của tất cả các ChiTietSanPham thuộc về SanPham này
            int tongSoLuong = sanPham.getListChiTietSanPham().stream()
                    .mapToInt(ChiTietSanPham::getSoLuong)
                    .sum();
            // Kiểm tra nếu tổng số lượng <= 0, thì cập nhật trạng thái
            if (tongSoLuong <= 0) {
                sanPham.setTrangThai(CommonEnum.TrangThaiSanPham.INACTIVE);  // Giả sử có phương thức setTrangThai() để cập nhật trạng thái
            }
        });

        return pageSanPham.map(mapper::convertEntityToResponse);
    }

    @Override
    public List<SanPhamResponse> getAllSanPhamNullCTSP() {
        List<SanPham> listSanPham = repository.getAllSanPhamNullCTSP();
        return listSanPham.stream()
                .map(sanPham -> mapper.convertEntityToResponse(sanPham))
                .collect(Collectors.toList());
    }

    @Override
    public SanPhamResponse add(CreatedSanPhamRequest request) {
        if (repository.existsByTen(request.getTen())) {
            throw new BadRequestException("Tên sản phẩm đã tồn tại trong hệ thống!");
        }
        SanPham createdSanPham = mapper.convertCreateRequestToEntity(request);

        // Tự GEN mã
        String prefix = "BS";
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
        createdSanPham.setMa(code);
        createdSanPham.setTrangThai(CommonEnum.TrangThaiSanPham.ACTIVE);
        SanPham savedSanPham = this.repository.save(createdSanPham);
        return mapper.convertEntityToResponse(savedSanPham);
    }

    @Override
    public SanPhamResponse update(Long id, UpdatedSanPhamRequest request) {
        Optional<SanPham> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Sản phẩm không tồn tại!");
        }
        SanPham detail = optional.get();
        if (!request.getTen().equals(optional.get().getTen())&&repository.existsByTen(request.getTen())) {
            throw new BadRequestException("Tên sản phẩm đã tồn tại trong hệ thống!");
        }

        mapper.convertUpdateRequestToEntity(request,detail);
        return mapper.convertEntityToResponse(repository.save(detail));
    }

    @Override
    public void delete(Long id) {
        Optional<SanPham> optional = this.repository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Sản phẩm không tồn tại");
        }
        repository.delete(optional.get());
    }

    @Override
    public SanPhamResponse findById(Long id) {
        Optional<SanPham> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Sản phẩm không tồn tại");
        }

        return mapper.convertEntityToResponse(optional.get());
    }

//    @Override
//    public SanPhamResponse findById(Long id) {
//        return null;
//    }

    @Override
    public Page<SanPhamFilterResponse> filterSanPham(Integer page, Integer pageSize, Integer sapXep, BigDecimal minPrice, BigDecimal maxPrice, List<Long> listThuongHieu, List<Long> listMauSac, List<Long> listKichCo, List<Long> listLoaiDe, List<Long> listDiaHinhSan, String search) {//
        Sort sort;
        if (sapXep == 3) {
            sort = Sort.by("ten").ascending();
        } else if (sapXep == 4) {
            sort = Sort.by("ten").descending();
        } else if (sapXep == 5) {
            sort = Sort.by("ngayTao").ascending();
        } else {
            sort = Sort.by("ngayTao").descending();
        }

        if (listThuongHieu == null || listThuongHieu.isEmpty()){
            listThuongHieu = thuongHieuRepository.findByIdIn();
        }
        if (listDiaHinhSan == null || listDiaHinhSan.isEmpty()){
            listDiaHinhSan = diaHinhSanRepository.findByIdIn();
        }
        if (listMauSac == null || listMauSac.isEmpty()){
            listMauSac = mauSacRepository.findByIdIn();
        }
        if (listKichCo == null || listKichCo.isEmpty()){
            listKichCo = kichCoRepository.findByIdIn();
        }
        if (listLoaiDe == null || listLoaiDe.isEmpty()){
            listLoaiDe = loaiDeRepository.findByIdIn();
        }

        System.out.println(search);

        Pageable pageable = PageRequest.of(page - 1, pageSize, sort);

        Page<SanPhamFilterResponse> sanPhamPage = repository.filterSanPham(pageable, minPrice, maxPrice, listThuongHieu,listMauSac, listDiaHinhSan, listKichCo, listLoaiDe, search);//
        return sanPhamPage;
    }

    @Override
    public List<SanPhamResponse> get5SanPhamMoiNhat() {
        List<SanPham> sanPhamMoiNhat = repository.get5SanPhamMoiNhat();

        List<SanPhamResponse> sanPhamResponses = sanPhamMoiNhat
                .stream()
                .map(mapper::convertEntityToResponse)
                .collect(Collectors.toList());

        return sanPhamResponses
                .stream()
                .limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public List<SanPhamMoiNhatResponse> giaTien5SanPhamMoiNhat() {
        List<SanPhamMoiNhatResponse> giaTien5SanPhamMoiNhat = repository.findAllSanPhamMoiNhat();

        List<SanPhamMoiNhatResponse> chiTietSanPhamResponse = giaTien5SanPhamMoiNhat
                .stream()
                .collect(Collectors.toList());

        return chiTietSanPhamResponse
                .stream()
                .limit(10)
                .collect(Collectors.toList());
    }

    @Override
    public List<SanPhamBanChayResponse> get5SanPhamBanChayNhat() {
        List<SanPhamBanChayResponse> banChayNhat = repository.findAllSanPhamBanChay();

        List<SanPhamBanChayResponse> chiTietSanPhamResponse = banChayNhat
                .stream()
                .collect(Collectors.toList());

        return chiTietSanPhamResponse
                .stream()
                .limit(5)
                .collect(Collectors.toList());
    }


    @Override
    public SanPhamDetailResponse getSanPhamDetail(Long id) {
        SanPhamDetailResponse detail = repository.getDetailSanPham(id);
        return detail;
    }
}
