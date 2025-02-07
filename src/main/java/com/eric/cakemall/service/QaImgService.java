package com.eric.cakemall.service;

import com.eric.cakemall.dto.QaImgDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface QaImgService {
    QaImgDTO uploadQaImage(MultipartFile file);
    List<QaImgDTO> getAllQaImages();
    QaImgDTO getQaImageById(Integer qaNo);
    void deleteQaImage(Integer qaNo);
}
