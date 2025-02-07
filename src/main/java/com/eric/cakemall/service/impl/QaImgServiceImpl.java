package com.eric.cakemall.service.impl;

import com.eric.cakemall.dto.QaImgDTO;
import com.eric.cakemall.exception.QaImgNotFoundException;
import com.eric.cakemall.model.QaImg;
import com.eric.cakemall.repository.QaImgRepository;
import com.eric.cakemall.service.QaImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.multipart.MultipartFile;

@Service
public class QaImgServiceImpl implements QaImgService {

    @Autowired
    private QaImgRepository repository;

    private final String uploadDir = "C:\\Users\\user\\Desktop\\mall\\qa_images\\";

    @Override
    public QaImgDTO uploadQaImage(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("上傳的檔案不可為空");
        }

        // 儲存圖片到指定目錄
        String filePath = Paths.get(uploadDir, file.getOriginalFilename()).toString();
        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException("檔案上傳失敗", e);
        }

        // 儲存圖片資訊到資料庫
        QaImg qaImg = new QaImg();
        qaImg.setQaImgUrl(file.getOriginalFilename());
        QaImg savedQaImg = repository.save(qaImg);

        return convertToDTO(savedQaImg);
    }

    @Override
    public List<QaImgDTO> getAllQaImages() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public QaImgDTO getQaImageById(Integer qaNo) {
        QaImg qaImg = repository.findById(qaNo)
                .orElseThrow(() -> new QaImgNotFoundException("找不到 ID 為 " + qaNo + " 的圖片"));
        return convertToDTO(qaImg);
    }

    @Transactional
    @Override
    public void deleteQaImage(Integer qaNo) {
        QaImg qaImg = repository.findById(qaNo)
                .orElseThrow(() -> new QaImgNotFoundException("找不到 ID 為 " + qaNo + " 的圖片"));

        // 刪除實體檔案
        String filePath = Paths.get(uploadDir, qaImg.getQaImgUrl()).toString();
        File file = new File(filePath);
        if (file.exists() && !file.delete()) {
            throw new RuntimeException("無法刪除圖片檔案：" + filePath);
        }

        // 刪除資料庫中的紀錄
        repository.deleteById(qaNo);
    }

    private QaImgDTO convertToDTO(QaImg qaImg) {
        QaImgDTO dto = new QaImgDTO();
        dto.setQaNo(qaImg.getQaNo());
        dto.setQaImgUrl(qaImg.getQaImgUrl());
        return dto;
    }
}
