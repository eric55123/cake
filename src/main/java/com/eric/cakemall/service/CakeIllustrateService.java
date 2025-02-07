package com.eric.cakemall.service;

import com.eric.cakemall.dto.CakeIllustrateDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CakeIllustrateService {
    CakeIllustrateDTO uploadImage(MultipartFile file);
    List<CakeIllustrateDTO> getAllIllustrations();
    CakeIllustrateDTO getIllustrationById(Integer id);
    void deleteIllustration(Integer id);
}
