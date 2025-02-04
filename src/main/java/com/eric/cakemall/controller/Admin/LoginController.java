package com.eric.cakemall.controller.Admin;


import com.eric.cakemall.dto.AdminDTO;
import com.eric.cakemall.service.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class LoginController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AdminDTO adminDTO, HttpSession session) {
        AdminDTO loggedInAdmin = adminService.authenticateAdmin(adminDTO.getAdminAccount(), adminDTO.getAdminPassword());
        if (loggedInAdmin != null) {
            session.setAttribute("adminId", loggedInAdmin.getAdminId());
            Map<String, String> response = new HashMap<>();
            response.put("message", "登入成功");
            return ResponseEntity.ok(response);
        } else {
            throw new IllegalArgumentException("帳號或密碼錯誤");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(HttpSession session) {
        session.invalidate();
        Map<String, String> response = new HashMap<>();
        response.put("message", "已登出");
        return ResponseEntity.ok(response);
    }
}
