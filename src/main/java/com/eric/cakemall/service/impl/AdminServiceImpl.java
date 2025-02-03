package com.eric.cakemall.service.impl;

import com.eric.cakemall.dto.AdminDTO;
import com.eric.cakemall.exception.AdminNotFoundException;
import com.eric.cakemall.exception.DuplicateAdminException;
import com.eric.cakemall.model.Admin;
import com.eric.cakemall.repository.AdminRepository;
import com.eric.cakemall.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;


    @Override
    public AdminDTO registerAdmin(AdminDTO adminDTO) {

        // 檢查帳號是否重複
        if (adminRepository.findByAdminAccount(adminDTO.getAdminAccount()).isPresent()) {
            throw new DuplicateAdminException("帳號已被使用：" + adminDTO.getAdminAccount());
        }

        Admin admin = convertToEntity(adminDTO);
        admin.setAdminCreated(LocalDateTime.now());
        admin.setAdminUpdated(LocalDateTime.now());
        Admin saveAdmin = adminRepository.save(admin);
        return convertToDTO(saveAdmin);
    }

    @Override
    public List<AdminDTO> getAllAdmins() {

        return adminRepository.findAll().stream()
                .map(admin -> convertToDTO(admin))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AdminDTO> getAdminById(Integer adminId) {
        return adminRepository.findById(adminId).map(this::convertToDTO);
    }

    @Override
    public AdminDTO updateAdmin(Integer adminId, AdminDTO adminDTO) {
        return adminRepository.findById(adminId)
                .map(existingAdmin -> {
                    existingAdmin.setAdminAccount(adminDTO.getAdminAccount());
                    existingAdmin.setAdminPassword(adminDTO.getAdminPassword());
                    existingAdmin.setAdminMail(adminDTO.getAdminMail());
                    existingAdmin.setAdminController(adminDTO.getAdmnController());
                    existingAdmin.setAdminStatus(adminDTO.getAdminStatus());
                    existingAdmin.setAdminUpdated(LocalDateTime.now());
                    return convertToDTO(adminRepository.save(existingAdmin));
                })
                .orElseThrow(() -> new AdminNotFoundException("找不到管理員 ID：" + adminId));
    }

    @Override
    public void deleteAdmin(Integer adminId) {
        adminRepository.deleteById(adminId);
    }

    private AdminDTO convertToDTO(Admin admin) {
        AdminDTO dto = new AdminDTO();
        dto.setAdminId(admin.getAdminId());
        dto.setAdminAccount(admin.getAdminAccount());
        dto.setAdminPassword(admin.getAdminPassword());
        dto.setAdminMail(admin.getAdminMail());
        dto.setAdmnController(admin.getAdminController());
        dto.setAdminStatus(admin.getAdminStatus());
        return dto;
    }

    private Admin convertToEntity(AdminDTO dto) {
        Admin admin = new Admin();
        admin.setAdminId(dto.getAdminId());
        admin.setAdminAccount(dto.getAdminAccount());
        admin.setAdminPassword(dto.getAdminPassword());
        admin.setAdminMail(dto.getAdminMail());
        admin.setAdminController(dto.getAdmnController());
        admin.setAdminStatus(dto.getAdminStatus());
        return admin;
    }
}
