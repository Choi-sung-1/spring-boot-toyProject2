package com.project.toyProject2.service.member;

import com.project.toyProject2.domain.vo.MemberVO;

import java.util.Optional;

public interface MemberService {
//    회원가입
    public void join(MemberVO memberVO);
//    회원 조회
    public Optional<MemberVO> findMember(String memberLoginId);
    public MemberVO findMemberByPk(Long memberId);
}
