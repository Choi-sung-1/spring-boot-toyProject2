package com.project.toyProject2.service.sercurity;

import com.project.toyProject2.domain.member.MemberVO;
import com.project.toyProject2.repository.member.MemberDAO;
import com.project.toyProject2.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final MemberDAO memberDAO;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberVO member = memberDAO.selectMemberById(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당 아이디 없음: " + username));
        return new CustomUserDetails(member);
    }
}
