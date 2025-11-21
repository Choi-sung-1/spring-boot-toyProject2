package com.project.toyProject2.repository;

import com.project.toyProject2.mapper.MemberMapper;
import com.project.toyProject2.domain.vo.MemberVO;
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
//    회원 조회
    public Optional<MemberVO> selectMemberById(String memberLoginId) {
        return memberMapper.selectMemberById(memberLoginId);
    }
    public MemberVO selectMemberByPk(Long memberId){
        return memberMapper.selectMemberByPk(memberId);
    }
}
