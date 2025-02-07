package com.eric.cakemall.controller;

import com.eric.cakemall.dto.CakeIllustrateDTO;
import com.eric.cakemall.service.CakeIllustrateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cake_illustrates")
public class CakeIllustrateController {

    @Autowired
    private CakeIllustrateService cakeIllustrateService;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("file") MultipartFile file) {
        cakeIllustrateService.uploadImage(file);

        Map<String, String> response = new HashMap<>();
        response.put("message", "圖片上傳成功");
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CakeIllustrateDTO>> getAllIllustrations() {
        return ResponseEntity.ok(cakeIllustrateService.getAllIllustrations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CakeIllustrateDTO> getIllustrationById(@PathVariable Integer id) {
        return ResponseEntity.ok(cakeIllustrateService.getIllustrationById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteIllustration(@PathVariable Integer id) {
        cakeIllustrateService.deleteIllustration(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "圖片刪除成功");
        return ResponseEntity.ok(response);
    }
}
