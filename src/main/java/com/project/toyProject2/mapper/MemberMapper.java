package com.project.toyProject2.mapper;

import com.project.toyProject2.domain.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberMapper {
//    회원가입
    public void insert(MemberVO memberVO);
//    회원 조회
    public Optional<MemberVO> selectMemberById(String memberLoginId);
    public MemberVO selectMemberByPk(Long memberId);
}
