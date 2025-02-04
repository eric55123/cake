package com.eric.cakemall.service;

import com.eric.cakemall.dto.BulletinDTO;

import java.util.List;

public interface BulletinService {

    BulletinDTO createBulletin(BulletinDTO bulletinDTO);

    List<BulletinDTO> getAllBulletins();

    BulletinDTO getBulletinById(Integer bulletinNo);

    BulletinDTO updateBulletin(Integer bulletinNo, BulletinDTO bulletinDTO);

    void deleteBulletin(Integer bulletinNo);
}
