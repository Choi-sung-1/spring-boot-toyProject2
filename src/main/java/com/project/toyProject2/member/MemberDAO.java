package com.project.toyProject2.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberDAO {
    private final MemberMapper memberMapper;
//   회원가입
    public void insert(MemberVO memberVO) {
        memberMapper.insert(memberVO);
    }
//    로그인
    public Optional<MemberVO> selectMemberById(String memberLoginId) {
        return memberMapper.selectMemberById(memberLoginId);
    }
}
