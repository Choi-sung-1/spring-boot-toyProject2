package com.project.toyProject2.service;

import com.project.toyProject2.domain.vo.MemberVO;

import java.util.Optional;

public interface MemberService {
//    회원가입
    public void join(MemberVO memberVO);
//    회원 조회
    public Optional<MemberVO> findMember(String memberLoginId);
}
