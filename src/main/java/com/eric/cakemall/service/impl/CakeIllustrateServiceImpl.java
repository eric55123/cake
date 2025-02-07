package com.eric.cakemall.service.impl;

import com.eric.cakemall.dto.CakeIllustrateDTO;
import com.eric.cakemall.exception.CakeIllustrateNotFoundException;
import com.eric.cakemall.model.CakeIllustrate;
import com.eric.cakemall.repository.CakeIllustrateRepository;
import com.eric.cakemall.service.CakeIllustrateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CakeIllustrateServiceImpl implements CakeIllustrateService {

    @Autowired
    private CakeIllustrateRepository repository;

    private final String uploadDir = "C:\\Users\\user\\Desktop\\mall\\test\\";

    @Override
    public CakeIllustrateDTO uploadImage(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("上傳的檔案不可為空");
        }

        String filePath = uploadDir + file.getOriginalFilename();

        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException("檔案上傳失敗", e);
        }

        // 儲存圖片資訊到資料庫
        CakeIllustrate illustration = new CakeIllustrate();
        illustration.setCakeImgUrl(filePath);
        CakeIllustrate savedIllustration = repository.save(illustration);

        return new CakeIllustrateDTO(savedIllustration.getCakeIllustrate(), savedIllustration.getCakeImgUrl());
    }

    @Override
    public List<CakeIllustrateDTO> getAllIllustrations() {
        return repository.findAll().stream()
                .map(illustration -> new CakeIllustrateDTO(illustration.getCakeIllustrate(), illustration.getCakeImgUrl()))
                .collect(Collectors.toList());
    }

    @Override
    public CakeIllustrateDTO getIllustrationById(Integer id) {
        CakeIllustrate illustration = repository.findById(id)
                .orElseThrow(() -> new CakeIllustrateNotFoundException("找不到 ID 為 " + id + " 的圖片"));
        return new CakeIllustrateDTO(illustration.getCakeIllustrate(), illustration.getCakeImgUrl());
    }

    @Override
    public void deleteIllustration(Integer id) {
        CakeIllustrate illustration = repository.findById(id)
                .orElseThrow(() -> new CakeIllustrateNotFoundException("找不到 ID 為 " + id + " 的圖片"));

        // 刪除實體檔案
        File file = new File(illustration.getCakeImgUrl());
        if (file.exists() && file.isFile()) {
            if (!file.delete()) {
                throw new RuntimeException("無法刪除圖片檔案：" + illustration.getCakeImgUrl());
            }
        }

        // 刪除資料庫紀錄
        repository.deleteById(id);
    }
}
