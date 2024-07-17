package com.example.bee.service;

import jakarta.servlet.http.HttpServletResponse;

public interface PDFExportService {
    void exportPDF(HttpServletResponse httpServletResponse, Long id);
}
