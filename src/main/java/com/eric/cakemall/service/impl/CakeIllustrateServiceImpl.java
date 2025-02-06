package com.eric.cakemall.service.impl;

import com.eric.cakemall.dto.CakeIllustrateDTO;
import com.eric.cakemall.exception.CakeIllustrateNotFoundException;
import com.eric.cakemall.model.CakeIllustrate;
import com.eric.cakemall.repository.CakeIllustrateRepository;
import com.eric.cakemall.service.CakeIllustrateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CakeIllustrateServiceImpl implements CakeIllustrateService {

    @Autowired
    private CakeIllustrateRepository repository;

    @Override
    public CakeIllustrateDTO createIllustration(CakeIllustrateDTO illustrationDTO) {
        CakeIllustrate illustration = new CakeIllustrate();
        illustration.setCakeImgUrl(illustrationDTO.getCakeImgUrl());

        CakeIllustrate savedIllustration = repository.save(illustration);
        return convertToDTO(savedIllustration);
    }

    @Override
    public List<CakeIllustrateDTO> getAllIllustrations() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CakeIllustrateDTO getIllustrationById(Integer id) {
        CakeIllustrate illustration = repository.findById(id)
                .orElseThrow(() -> new CakeIllustrateNotFoundException("找不到 ID 為 " + id + " 的圖片"));
        return convertToDTO(illustration);
    }

    @Transactional
    @Override
    public void deleteIllustration(Integer id) {
        CakeIllustrate illustration = repository.findById(id)
                .orElseThrow(() -> new CakeIllustrateNotFoundException("找不到 ID 為 " + id + " 的圖片"));

        // 刪除實體檔案
        String uploadDir = "C:\\Users\\user\\Desktop\\mall\\test\\";  // 你的圖片目錄
        String filePath = uploadDir + new File(illustration.getCakeImgUrl()).getName();  // 取得檔案名稱後拼接路徑

        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            if (!file.delete()) {
                throw new CakeIllustrateNotFoundException("無法刪除圖片檔案：" + filePath);
            }
        }

        // 刪除資料庫中的紀錄
        repository.deleteById(id);
    }

    private CakeIllustrateDTO convertToDTO(CakeIllustrate illustration) {
        CakeIllustrateDTO dto = new CakeIllustrateDTO();
        dto.setCakeIllustrateId(illustration.getCakeIllustrate());
        dto.setCakeImgUrl(illustration.getCakeImgUrl());
        return dto;
    }
}
