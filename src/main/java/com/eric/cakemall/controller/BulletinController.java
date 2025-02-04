package com.eric.cakemall.controller;

import com.eric.cakemall.controller.Admin.AdminPermissionChecker;
import com.eric.cakemall.dto.BulletinDTO;
import com.eric.cakemall.service.BulletinService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bulletins")
public class BulletinController {

    @Autowired
    private BulletinService bulletinService;

    @Autowired
    private AdminPermissionChecker adminPermissionChecker;

    // 新增公告
    @PostMapping
    public ResponseEntity<Map<String, String>> createBulletin(@RequestBody BulletinDTO bulletinDTO, HttpServletRequest request) {
        adminPermissionChecker.checkAdminPermission(request);

        Integer adminId = (Integer) request.getSession().getAttribute("adminId");
        bulletinDTO.setAdminId(adminId);
        bulletinService.createBulletin(bulletinDTO);

        Map<String, String> response = new HashMap<>();
        response.put("message", "公告創建成功");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 更新公告
    @PutMapping("/{id}")
    public ResponseEntity<BulletinDTO> updateBulletin(@PathVariable Integer id, @RequestBody BulletinDTO bulletinDTO, HttpServletRequest request) {
        adminPermissionChecker.checkAdminPermission(request);

        bulletinDTO.setBulletinUpdateTime(LocalDateTime.now());
        return ResponseEntity.ok(bulletinService.updateBulletin(id, bulletinDTO));
    }

    // 刪除公告
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBulletin(@PathVariable Integer id, HttpServletRequest request) {
        adminPermissionChecker.checkAdminPermission(request);

        bulletinService.deleteBulletin(id);
        return ResponseEntity.noContent().build();
    }

    // 取得所有公告
    @GetMapping
    public ResponseEntity<List<BulletinDTO>> getAllBulletins() {
        return ResponseEntity.ok(bulletinService.getAllBulletins());
    }

    // 根據 ID 取得公告
    @GetMapping("/{id}")
    public ResponseEntity<BulletinDTO> getBulletinById(@PathVariable Integer id) {
        return ResponseEntity.ok(bulletinService.getBulletinById(id));
    }
}
