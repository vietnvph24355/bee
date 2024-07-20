package com.example.bee.service.impl;

import com.example.bee.entity.HoaDon;
import com.example.bee.exception.NotFoundException;
import com.example.bee.repository.HoaDonRepository;
import com.example.bee.service.PDFExportService;
import com.example.bee.utils.PDFExporter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PDFExportServiceImpl implements PDFExportService {
    @Autowired
    private PDFExporter pdfExporter;

    @Autowired
    private HoaDonRepository hoaDonRepository;
    @Override
    public void exportPDF(HttpServletResponse httpServletResponse, Long id) {

        HoaDon hoaDon = hoaDonRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy hóa đơn có id " + id));
        try {
            pdfExporter.export(httpServletResponse,hoaDon);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
