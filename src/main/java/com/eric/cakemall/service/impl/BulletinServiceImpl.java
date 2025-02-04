package com.eric.cakemall.service.impl;

import com.eric.cakemall.dto.BulletinDTO;
import com.eric.cakemall.exception.BulletinNotFoundException;
import com.eric.cakemall.model.Bulletin;
import com.eric.cakemall.repository.BulletinRepository;
import com.eric.cakemall.service.BulletinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BulletinServiceImpl implements BulletinService {

    @Autowired
    private BulletinRepository bulletinRepository;

    @Override
    public BulletinDTO createBulletin(BulletinDTO bulletinDTO) {
        Bulletin bulletin = convertToEntity(bulletinDTO);
        bulletin.setBulletinUpdateTime(LocalDateTime.now());  // 設置公告的創建/更新時間
        Bulletin savedBulletin = bulletinRepository.save(bulletin);
        return convertToDTO(savedBulletin);
    }

    @Override
    public List<BulletinDTO> getAllBulletins() {
        return bulletinRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BulletinDTO getBulletinById(Integer bulletinNo) {
        Bulletin bulletin = bulletinRepository.findById(bulletinNo)
                .orElseThrow(() -> new BulletinNotFoundException("找不到公告 ID：" + bulletinNo));
        return convertToDTO(bulletin);
    }

    @Override
    public BulletinDTO updateBulletin(Integer bulletinNo, BulletinDTO bulletinDTO) {
        Bulletin bulletin = bulletinRepository.findById(bulletinNo)
                .orElseThrow(() -> new BulletinNotFoundException("找不到公告 ID：" + bulletinNo));

        bulletin.setBulletinTitle(bulletinDTO.getBulletinTitle());
        bulletin.setBulletinContent(bulletinDTO.getBulletinContent());
        bulletin.setBulletinUpdateTime(LocalDateTime.now());

        Bulletin updatedBulletin = bulletinRepository.save(bulletin);
        return convertToDTO(updatedBulletin);
    }

    @Override
    public void deleteBulletin(Integer bulletinNo) {
        Bulletin bulletin = bulletinRepository.findById(bulletinNo)
                .orElseThrow(() -> new BulletinNotFoundException("找不到公告 ID：" + bulletinNo));
        bulletinRepository.delete(bulletin);
    }

    // 將 DTO 轉換為實體
    private Bulletin convertToEntity(BulletinDTO dto) {
        Bulletin bulletin = new Bulletin();
        bulletin.setBulletinNo(dto.getBulletinNo());
        bulletin.setBulletinTitle(dto.getBulletinTitle());
        bulletin.setBulletinContent(dto.getBulletinContent());
        bulletin.setAdminId(dto.getAdminId());
        return bulletin;
    }

    // 將實體轉換為 DTO
    private BulletinDTO convertToDTO(Bulletin bulletin) {
        BulletinDTO dto = new BulletinDTO();
        dto.setBulletinNo(bulletin.getBulletinNo());
        dto.setBulletinTitle(bulletin.getBulletinTitle());
        dto.setBulletinContent(bulletin.getBulletinContent());
        dto.setBulletinUpdateTime(bulletin.getBulletinUpdateTime());
        dto.setAdminId(bulletin.getAdminId());
        return dto;
    }
}
