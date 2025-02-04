package com.eric.cakemall.controller.Admin;

import com.eric.cakemall.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminPermissionChecker {

    @Autowired
    private AdminService adminService;

    // 檢查管理員權限
    public void checkAdminPermission(HttpServletRequest request) {
        Integer adminId = (Integer) request.getSession().getAttribute("adminId");
        if (adminId == null) {
            throw new SecurityException("無法取得登入的管理員 ID");
        }

        boolean hasPermission = adminService.hasAdminPermission(adminId);
        if (!hasPermission) {
            throw new SecurityException("您沒有權限執行此操作");
        }
    }
}
