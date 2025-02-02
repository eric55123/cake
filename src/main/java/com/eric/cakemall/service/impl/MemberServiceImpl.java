package com.eric.cakemall.service.impl;

import com.eric.cakemall.dto.MemberDTO;
import com.eric.cakemall.exception.DuplicateMemberException;
import com.eric.cakemall.model.Member;
import com.eric.cakemall.repository.MemberRepository;
import com.eric.cakemall.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public MemberDTO registerMember(MemberDTO memberDTO) {
        if (memberRepository.findByMemberAccount(memberDTO.getMemberAccount()).isPresent()) {
            throw new DuplicateMemberException("帳號已被使用：" + memberDTO.getMemberAccount());
        }

        if (memberRepository.findByMemberEmail(memberDTO.getMemberEmail()).isPresent()) {
            throw new DuplicateMemberException("電子郵件已被使用：" + memberDTO.getMemberEmail());
        }

        Member member = convertToEntity(memberDTO);
        member.setMemberCreated(LocalDateTime.now());
        member.setMemberUpdated(LocalDateTime.now());
        Member savedMember = memberRepository.save(member);
        return convertToDTO(savedMember);
    }

    @Override
    public List<MemberDTO> getAllMembers() {
        return memberRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MemberDTO> getMemberById(Integer memberNo) {
        return memberRepository.findById(memberNo).map(this::convertToDTO);
    }

    @Override
    public MemberDTO updateMember(Integer memberNo, MemberDTO memberDTO) {
        return memberRepository.findById(memberNo)
                .map(existingMember -> {
                    existingMember.setMemberName(memberDTO.getMemberName());
                    existingMember.setMemberPwd(memberDTO.getMemberPwd());
                    existingMember.setMemberEmail(memberDTO.getMemberEmail());
                    existingMember.setMemberPhone(memberDTO.getMemberPhone());
                    existingMember.setAddress(memberDTO.getAddress());
                    existingMember.setBirthday(memberDTO.getBirthday());
                    existingMember.setMemberStatus(memberDTO.getMemberStatus());
                    return convertToDTO(memberRepository.save(existingMember));
                })
                .orElseThrow(() -> new DuplicateMemberException("找不到會員 ID：" + memberNo));
    }

    @Override
    public void deleteMember(Integer memberNo) {
        memberRepository.deleteById(memberNo);
    }

    private MemberDTO convertToDTO(Member member) {
        MemberDTO dto = new MemberDTO();
        dto.setMemberNo(member.getMemberNo());
        dto.setMemberAccount(member.getMemberAccount());
        dto.setMemberPwd(member.getMemberPwd());
        dto.setMemberEmail(member.getMemberEmail());
        dto.setMemberName(member.getMemberName());
        dto.setMemberPhone(member.getMemberPhone());
        dto.setAddress(member.getAddress());
        dto.setBirthday(member.getBirthday());
        dto.setMemberStatus(member.getMemberStatus());
        dto.setMemberCreated(member.getMemberCreated());
        dto.setMemberUpdated(member.getMemberUpdated());
        return dto;
    }

    private Member convertToEntity(MemberDTO dto) {
        Member member = new Member();
        member.setMemberNo(dto.getMemberNo());
        member.setMemberAccount(dto.getMemberAccount());
        member.setMemberPwd(dto.getMemberPwd());
        member.setMemberEmail(dto.getMemberEmail());
        member.setMemberName(dto.getMemberName());
        member.setMemberPhone(dto.getMemberPhone());
        member.setAddress(dto.getAddress());
        member.setMemberStatus(dto.getMemberStatus());
        member.setBirthday(dto.getBirthday());
        member.setMemberStatus(dto.getMemberStatus());
        return member;
    }
}
