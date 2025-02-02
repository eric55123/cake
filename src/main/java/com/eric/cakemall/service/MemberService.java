package com.eric.cakemall.service;

import com.eric.cakemall.dto.MemberDTO;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    MemberDTO registerMember(MemberDTO memberDTO);
    List<MemberDTO> getAllMembers();
    Optional<MemberDTO> getMemberById(Integer memberNo);
    MemberDTO updateMember(Integer memberNo, MemberDTO memberDTO);
    void deleteMember(Integer memberNo);
}

