package com.eric.cakemall.controller;

import com.eric.cakemall.dto.MemberDTO;
import com.eric.cakemall.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerMember(@RequestBody MemberDTO memberDTO) {
        if (memberDTO == null || memberDTO.getMemberPwd() == null || memberDTO.getMemberPwd().isEmpty()) {
            throw new IllegalArgumentException("密碼不可為空");
        }

        memberService.registerMember(memberDTO);
        Map<String, String> response = new HashMap<>();
        response.put("message", "註冊成功");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping
    public ResponseEntity<List<MemberDTO>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getMemberById(@PathVariable Integer id) {
        Optional<MemberDTO> member = memberService.getMemberById(id);
        if (member.isPresent()) {
            return ResponseEntity.ok(member.get());
        } else {
            return ResponseEntity.status(404).body("會員不存在");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberDTO> updateMember(@PathVariable Integer id, @RequestBody MemberDTO memberDTO) {
        return ResponseEntity.ok(memberService.updateMember(id, memberDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Integer id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }
}
