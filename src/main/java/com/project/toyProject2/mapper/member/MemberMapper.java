package com.project.toyProject2.mapper.member;

import com.project.toyProject2.domain.member.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberMapper {
//    회원가입
    public void insert(MemberVO memberVO);
//    로그인
    public Optional<MemberVO> selectMemberById(String memberLoginId);
}
