package com.eric.cakemall.service;

import com.eric.cakemall.dto.CakeIllustrateDTO;

import java.util.List;

public interface CakeIllustrateService {

    CakeIllustrateDTO createIllustration(CakeIllustrateDTO illustrationDTO);

    List<CakeIllustrateDTO> getAllIllustrations();

    CakeIllustrateDTO getIllustrationById(Integer id);

    void deleteIllustration(Integer id);
}
