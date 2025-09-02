package com.project.toyProject2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebConfig {
//   spring security 주입
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/member/login", "/member/join", "/css/**", "/js/**").permitAll() // 로그인/회원가입 페이지는 모두 접근 가능
                    .anyRequest().authenticated() // 나머지 요청은 인증 필요
            )
            .formLogin(form -> form
                    .loginPage("/member/login")        //GET 로그인 페이지
                    .loginProcessingUrl("/member/login")   //POST 로그인 처리
                    .usernameParameter("memberLoginId")
                    .passwordParameter("memberPassword")
                    .defaultSuccessUrl("/", true)   //로그인 성공 시 이동
            )
            .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
            );

    return http.build();
}

    //    BCryptPassword 비밀번호 암호화 주입
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
