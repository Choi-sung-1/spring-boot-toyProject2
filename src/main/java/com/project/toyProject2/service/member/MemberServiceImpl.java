package com.project.toyProject2.service.member;

import com.project.toyProject2.domain.member.MemberVO;
import com.project.toyProject2.repository.member.MemberDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberDAO memberDAO;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void join(MemberVO memberVO) {
//      비밀번호 암호화
        memberVO.setMemberPassword(passwordEncoder.encode(memberVO.getMemberPassword()));
        memberVO.setMemberRole("USER");
        memberVO.setMemberRegisterDate(LocalDateTime.now());
        memberVO.setMemberUpdateDate(LocalDateTime.now());

        memberDAO.insert(memberVO);
    }
}
