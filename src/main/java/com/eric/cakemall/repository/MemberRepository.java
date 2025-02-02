package com.eric.cakemall.repository;

import com.eric.cakemall.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    // 查詢會員帳號 (Member Account)
    Optional<Member> findByMemberAccount(String memberAccount);

    // 查詢會員電子郵件 (Member Email)
    Optional<Member> findByMemberEmail(String memberEmail);
}
