package com.eric.cakemall.repository;

import com.eric.cakemall.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByAdminAccount(String adminAccount);
}
