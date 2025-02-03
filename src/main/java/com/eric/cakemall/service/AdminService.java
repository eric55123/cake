package com.eric.cakemall.service;

import com.eric.cakemall.dto.AdminDTO;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    AdminDTO registerAdmin(AdminDTO adminDTO);
    List<AdminDTO> getAllAdmins();
    Optional<AdminDTO> getAdminById(Integer adminId);
    AdminDTO updateAdmin(Integer adminId, AdminDTO adminDTO);
    void deleteAdmin(Integer adminId);
}
