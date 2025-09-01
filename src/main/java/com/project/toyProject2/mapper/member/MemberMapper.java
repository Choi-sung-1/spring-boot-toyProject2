package com.project.toyProject2.mapper.member;

import com.project.toyProject2.domain.member.MemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
//    회원가입
    public void insert(MemberVO memberVO);
}
