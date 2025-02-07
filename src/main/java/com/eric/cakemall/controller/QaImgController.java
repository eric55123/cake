package com.eric.cakemall.controller;

import com.eric.cakemall.dto.QaImgDTO;
import com.eric.cakemall.exception.QaImgNotFoundException;
import com.eric.cakemall.service.QaImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/qa_images")
public class QaImgController {

    @Autowired
    private QaImgService qaImgService;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadQaImage(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("上傳的檔案不可為空");
        }

        qaImgService.uploadQaImage(file);

        Map<String, String> response = new HashMap<>();
        response.put("message", "圖片上傳成功");
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<QaImgDTO>> getAllQaImages() {
        List<QaImgDTO> images = qaImgService.getAllQaImages();
        if (images.isEmpty()) {
            throw new QaImgNotFoundException("目前沒有任何圖片資料");
        }
        return ResponseEntity.ok(images);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QaImgDTO> getQaImageById(@PathVariable Integer id) {
        return ResponseEntity.ok(qaImgService.getQaImageById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteQaImage(@PathVariable Integer id) {
        qaImgService.deleteQaImage(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "圖片刪除成功");
        return ResponseEntity.ok(response);
    }
}
