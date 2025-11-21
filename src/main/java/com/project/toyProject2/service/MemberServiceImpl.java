package com.project.toyProject2.service;

import com.project.toyProject2.domain.vo.MemberVO;
import com.project.toyProject2.repository.MemberDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberDAO memberDAO;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void join(MemberVO memberVO) {
//      비밀번호 암호화
        memberVO.setMemberPassword(passwordEncoder.encode(memberVO.getMemberPassword()));
//        memberVO.setMemberRole("USER");
        memberVO.setMemberRole("ADMIN");
        memberVO.setMemberRegisterDate(LocalDateTime.now());
        memberVO.setMemberUpdateDate(LocalDateTime.now());

        memberDAO.insert(memberVO);
    }

    @Override
    public Optional<MemberVO> findMember(String memberLoginId) {
        return memberDAO.selectMemberById(memberLoginId);
    }

    @Override
    public MemberVO findMemberByPk(Long memberId) {
        return memberDAO.selectMemberByPk(memberId);
    }
}
