package com.example.bee.controller.ac;

import com.example.bee.service.impl.AwS3Service;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin/api/file")
public class AwS3Controller {

    @Autowired
    private AwS3Service storageService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file) {
        String imageUrl = storageService.uploadFile(file); // Tải ảnh lên Amazon S3
        return new ResponseEntity<>(imageUrl, HttpStatus.OK);
    }

    @GetMapping("/view/{fileName}")
    public ResponseEntity<ByteArrayResource> viewFile(@PathVariable String fileName) {
        byte[] data = storageService.downloadFile(fileName);
        if (data != null) {
            String extension = FilenameUtils.getExtension(fileName).toLowerCase(); // Sử dụng thư viện Apache Commons IO
            String contentType = getContentTypeByExtension(extension);

            if (contentType != null) {
                ByteArrayResource resource = new ByteArrayResource(data);
                return ResponseEntity
                        .ok()
                        .contentLength(data.length)
                        .header("Content-type", contentType)
                        .body(resource);
            }
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
        byte[] data = storageService.downloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }
    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        return new ResponseEntity<>(storageService.deleteFile(fileName), HttpStatus.OK);
    }

    private String getContentTypeByExtension(String extension) {
        switch (extension) {
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "png":
                return "image/png";
            case "gif":
                return "image/gif";
            // Thêm các định dạng khác nếu cần
            default:
                return null;
        }
    }
}
