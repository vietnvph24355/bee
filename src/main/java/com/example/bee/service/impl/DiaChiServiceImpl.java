package com.example.bee.service.impl;

import com.example.bee.common.CommonEnum;
import com.example.bee.entity.DiaChi;
import com.example.bee.entity.TaiKhoan;
import com.example.bee.exception.NotFoundException;
import com.example.bee.model.mapper.DiaChiMapper;
import com.example.bee.model.request.create_request.CreatedDiaChiRequest;
import com.example.bee.model.request.update_request.UpdateDCReuest;
import com.example.bee.model.request.update_request.UpdatedDiaChiRequest;
import com.example.bee.model.response.DiaChiReponse;
import com.example.bee.repository.DiaChiRepository;
import com.example.bee.repository.TaiKhoanRepository;
import com.example.bee.service.DiaChiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiaChiServiceImpl implements DiaChiService {

    @Autowired
    private DiaChiMapper diaChiMapper;

    @Autowired
    private DiaChiRepository diaChiRepository;

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Override
    public Page<DiaChiReponse> getAll(Integer page, Integer pageSize, String sortField, String sortOrder, String trangThaiDiaChi, String searchText, Long idKH) {
        Sort sort;
        if ("ascend".equals(sortOrder)) {
            sort = Sort.by(sortField).ascending();
        } else if ("descend".equals(sortOrder)) {
            sort = Sort.by(sortField).descending();
        } else {
            sort = Sort.by("ngayTao").descending();
        }

        CommonEnum.TrangThaiDiaChi trangThai;
        if (trangThaiDiaChi == null || trangThaiDiaChi.equals("")) {
            trangThai = null;
        } else {
            trangThai = CommonEnum.TrangThaiDiaChi.valueOf(trangThaiDiaChi);
        }
        Pageable pageable = PageRequest.of(page - 1, pageSize, sort);
        Page<DiaChi> diaChiPage = diaChiRepository.findAllByTaiKhoanId(pageable, searchText, trangThai, idKH);
        return diaChiPage.map(diaChiMapper::convertEntityToResponse);
    }
    @Override
    public DiaChiReponse add(Long taiKhoanId, CreatedDiaChiRequest request) {
        // Chuyển đổi yêu cầu tạo thành thực thể địa chỉ
        DiaChi createdDiaChi = diaChiMapper.convertCreateResponseToEntity(request);

        // Kiểm tra sự tồn tại của tài khoản
        Optional<TaiKhoan> optionalTaiKhoan = taiKhoanRepository.findById(taiKhoanId);
        if (optionalTaiKhoan.isEmpty()) {
            throw new NotFoundException("Tài khoản không tồn tại");
        }
        TaiKhoan taiKhoan = optionalTaiKhoan.get();
        if (request.getTrangThaiDiaChi() == CommonEnum.TrangThaiDiaChi.DEFAULT) {
            // Đặt các địa chỉ khác của tài khoản thành ACTIVE
            diaChiRepository.findDiaChiByTrangThaiDiaChi(CommonEnum.TrangThaiDiaChi.DEFAULT) // Lọc theo tài khoản và không theo id
                    .forEach(address -> {
                        address.setTrangThaiDiaChi(CommonEnum.TrangThaiDiaChi.ACTIVE);
                        diaChiRepository.save(address);
                    });
        }

        // Đặt địa chỉ hiện tại thành trạng thái được yêu cầu
//        createdDiaChi.setTrangThaiDiaChi(request.getTrangThaiDiaChi());
        createdDiaChi.setTaiKhoan(taiKhoan);
        // Lưu địa chỉ mới
        DiaChi savedDC = diaChiRepository.save(createdDiaChi);
        return diaChiMapper.convertEntityToResponse(savedDC);
    }

//    @Override
//    public DiaChiReponse add(Long id, CreatedDiaChiRequest request) {
//        DiaChi createdDiaChi = diaChiMapper.convertCreateResponseToEntity(request);
//        Optional<DiaChi> optional = diaChiRepository.findById(id);
//        if (optional.isEmpty()) {
//            throw new NotFoundException("Địa chỉ không tồn tại");
//        }
//
//        DiaChi detail = optional.get();
//
//        if (request.getTrangThaiDiaChi() == CommonEnum.TrangThaiDiaChi.DEFAULT) {
//            // Đặt các địa chỉ khác thành ACTIVE
//            diaChiRepository.findByTaiKhoanAndIdNot(detail.getTaiKhoan(), id)
//                    .forEach(address -> {
//                        address.setTrangThaiDiaChi(CommonEnum.TrangThaiDiaChi.ACTIVE);
//                        diaChiRepository.save(address);
//                    });
//        }
//
//        // Đặt địa chỉ hiện tại thành trạng thái được yêu cầu
//        createdDiaChi.setTrangThaiDiaChi(request.getTrangThaiDiaChi());
//        createdDiaChi.setTaiKhoan(taiKhoanRepository.findId(id));
//        DiaChi savedDC = diaChiRepository.save(createdDiaChi);
//        return diaChiMapper.convertEntityToResponse(savedDC);
//    }

    @Override
    public List<DiaChiReponse> findByListDiaChi(Long idTaiKhoan) {
        List<DiaChi> diaChis = diaChiRepository.findByListDiaChi(idTaiKhoan);
        return diaChis
                .stream()
                .map(diaChiMapper::convertEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DiaChiReponse getDiaChiDefaultByIDTaiKhoan(Long idtaiKhoan) {
        return diaChiMapper.convertEntityToResponse(diaChiRepository.getDiaChiDefaultByIdTaiKhoan(idtaiKhoan));
    }

    @Override
    public DiaChiReponse findById(Long id) {
        Optional<DiaChi> diaChi = diaChiRepository.findId(id);
        if (diaChi.isEmpty()) {
            throw new NotFoundException("Địa chỉ không tồn tại");
        }
        return diaChiMapper.convertEntityToResponse(diaChi.get());
    }

    @Override
    public DiaChi update(Long id, UpdatedDiaChiRequest request) {
        Optional<DiaChi> optional = diaChiRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Địa chỉ không tồn tại");
        }

        DiaChi detail = optional.get();
        CommonEnum.TrangThaiDiaChi trangThaiCu = detail.getTrangThaiDiaChi();
//        diaChiMapper.convertUpdateRequestToEntity(request, detail);
//        System.out.println(detail);
//        DiaChi diaChi = ;
//        System.out.println(diaChiReponse.getId());
//        System.out.println(diaChiReponse);
        detail.setHoVaTen(request.getHoVaTen());
        detail.setSoDienThoai(request.getSoDienThoai());
        detail.setThanhPho(request.getThanhPho());
        detail.setQuanHuyen(request.getQuanHuyen());
        detail.setPhuongXa(request.getPhuongXa());
        detail.setDiaChiCuThe(request.getDiaChiCuThe());
        detail.setDiaChi(request.getDiaChi());
//        detail.setTrangThaiDiaChi(request.getTrangThaiDiaChi());
        if (request.getTrangThaiDiaChi() == CommonEnum.TrangThaiDiaChi.DEFAULT) {
            List<DiaChi> otherAddresses = diaChiRepository.findByTaiKhoanAndIdNot(detail.getTaiKhoan(), id);
            for (DiaChi otherAddress : otherAddresses) {
                otherAddress.setTrangThaiDiaChi(CommonEnum.TrangThaiDiaChi.ACTIVE);
                diaChiRepository.save(otherAddress);
            }
        }

        // Cập nhật trạng thái mới cho địa chỉ hiện tại
        detail.setTrangThaiDiaChi(request.getTrangThaiDiaChi());
        detail.setTaiKhoan(request.getTaiKhoan());
        detail.setPhuongXa(request.getPhuongXa());
        detail.setEmail(request.getEmail());
        return diaChiRepository.save(detail);
    }

    @Override
    public DiaChi updateTrangThai(Long id, UpdateDCReuest request) {
        Optional<DiaChi> optional = diaChiRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Địa chỉ không tồn tại");
        }

        DiaChi detail = optional.get();

        if (request.getTrangThaiDiaChi() == CommonEnum.TrangThaiDiaChi.DEFAULT) {
            // Đặt các địa chỉ khác thành ACTIVE
            diaChiRepository.findByTaiKhoanAndIdNot(detail.getTaiKhoan(), id)
                    .forEach(address -> {
                        address.setTrangThaiDiaChi(CommonEnum.TrangThaiDiaChi.ACTIVE);
                        diaChiRepository.save(address);
                    });
        }

        // Đặt địa chỉ hiện tại thành trạng thái được yêu cầu
        detail.setTrangThaiDiaChi(request.getTrangThaiDiaChi());
        return diaChiRepository.save(detail);
    }

    @Override
    public void delete(Long id) {
        Optional<DiaChi> optional = diaChiRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Địa chi không tồn tại");
        }
        DiaChi diaChi = optional.get();
        diaChiRepository.delete(diaChi);
    }
}
