package com.example.bee.controller.admin;

import com.example.bee.model.request.create_request.CreateHoaDonChiTietRequest;
import com.example.bee.model.request.create_request.CreateHoaDonRequest;
import com.example.bee.model.request.update_request.UpdatedHoaDonChiTietRequest;
import com.example.bee.model.request.update_request.UpdatedHoaDonRequest;
import com.example.bee.model.response.HoaDonChiTietResponse;
import com.example.bee.model.response.HoaDonResponse;
import com.example.bee.service.HoaDonChiTietService;
import com.example.bee.service.HoaDonService;
import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin/api/hoa-don")
public class HoaDonController {

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;

//    @Autowired
//    private ChiTietSanPhamService chiTietSanPhamService;

//    @Autowired
//    private PDFExportService pdfExportService;

    @GetMapping()
    public ResponseEntity<?> getAll(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "searchText", defaultValue = "", required = false) String searchText,
            @RequestParam(value = "sortField", defaultValue = "", required = false) String sorter,
            @RequestParam(value = "sortOrder", defaultValue = "", required = false) String sortOrder,
            @RequestParam(value = "loaiHoaDon", defaultValue = "", required = false) String loaiHoaDon,
            @RequestParam(value = "trangThaiHoaDon", defaultValue = "", required = false) String trangThaiHoaDon
    ) {
        return ResponseEntity.ok(hoaDonService.getAll(page, pageSize, searchText, sorter, sortOrder, loaiHoaDon, trangThaiHoaDon));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(hoaDonService.findById(id));
    }


    @PostMapping()
    public ResponseEntity<?> add(@RequestBody CreateHoaDonRequest createHoaDonRequest) {
        return new ResponseEntity<>(hoaDonService.add(createHoaDonRequest), HttpStatus.CREATED);
    }

    @PostMapping("/add-san-pham/{id}")
    public ResponseEntity<?> addHoaDonChiTiet(@PathVariable(name = "id") Long id, @RequestBody CreateHoaDonChiTietRequest createHoaDonChiTietRequest) {
        return new ResponseEntity<>(hoaDonChiTietService.add(createHoaDonChiTietRequest, id), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody UpdatedHoaDonRequest updatedHoaDonRequest, @PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(hoaDonService.update(id, updatedHoaDonRequest));
    }

    @PutMapping("/hoa-don-chi-tiet/{id}")
    public ResponseEntity<?> updateHoaDonChiTiet(@RequestBody List<UpdatedHoaDonChiTietRequest> updatedHoaDonChiTietRequest) {
        hoaDonChiTietService.updateHoaDonChiTiet(updatedHoaDonChiTietRequest);
        return ResponseEntity.ok("chạy xong");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        HoaDonChiTietResponse hoaDonChiTiet = hoaDonChiTietService.findById(id);
        hoaDonChiTietService.delete(id);
        return ResponseEntity.ok(hoaDonChiTiet);
    }

//    @GetMapping("/test-hoa-don/{id}")
//    public ResponseEntity<?> detail(
//            @PathVariable(name = "id") Long id,
//            @RequestParam(value = "page", defaultValue = "1") Integer page,
//            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
//            @RequestParam(value = "searchText", defaultValue = "", required = false) String searchText,
//            @RequestParam(value = "sortField", defaultValue = "", required = false) String sorter,
//            @RequestParam(value = "sortOrder", defaultValue = "", required = false) String sortOrder
//    ) {
//
//        Page<HoaDonChiTietResponse> hoaDonChiTietResponses = hoaDonChiTietService.getPageAllByIdHoaDon(page, pageSize, searchText, sorter, sortOrder, id);
//        HoaDonResponse hoaDonResponse = hoaDonService.findById(id);
//
//        HoaDonHoaDonChiTietListResponseDTO hoaDonHoaDonChiTietListResponseDTO = new HoaDonHoaDonChiTietListResponseDTO();
//        hoaDonHoaDonChiTietListResponseDTO.setHoaDonResponse(hoaDonResponse);
//        hoaDonHoaDonChiTietListResponseDTO.setHoaDonChiTietResponsePage(hoaDonChiTietResponses);
//        return ResponseEntity.ok(hoaDonHoaDonChiTietListResponseDTO);
//    }

//    @GetMapping("/ctsp/{id}")
//    public ResponseEntity<?> getOneCtsp(@PathVariable(name = "id") Long id) {
//        return ResponseEntity.ok(chiTietSanPhamService.getOneCtspById(id));
//    }

    @GetMapping("/hoa-don-cho")
    public ResponseEntity<?> get7HoaDonPendingByDate() {
        return ResponseEntity.ok(hoaDonService.get7HoaDonPendingByDateNew());
    }

    @GetMapping("/so-luong-hoa-don-cho")
    public ResponseEntity<?> getSoluongHoaDonCho() {
        return ResponseEntity.ok(hoaDonService.getSoLuongHoaDonCho());
    }

//    @GetMapping("/export/pdf")
//    public void exportToPDF(HttpServletResponse response, @Param("id") Long id) throws DocumentException, IOException {
//        response.setContentType("application/pdf");
//        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
//        String currentDateTime = dateFormatter.format(new Date());
//
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
//        response.setHeader(headerKey, headerValue);
//
//        pdfExportService.exportPDF(response, id);
//    }

    @GetMapping("/cancel/{id}")
    public ResponseEntity<?> cancelHoaDon(
            @PathVariable(name = "id") Long id,
            @RequestParam(value = "ghiChu", required = false) String ghiChu
    ) {
        ;
        return ResponseEntity.ok(hoaDonService.cancelHoaDon(id, ghiChu));
    }

}
