package com.project.toyProject2.repository.member;

import com.project.toyProject2.domain.member.MemberVO;
import com.project.toyProject2.mapper.member.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberDAO {
    private final MemberMapper memberMapper;
// 회원가입
    public void insert(MemberVO memberVO) {
        memberMapper.insert(memberVO);
    }
}
