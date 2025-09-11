package com.project.toyProject2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

@Configuration
public class WebConfig {
//   spring security 주입
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/", "/member/login","product/detail/*", "/product/list", "/member/join", "/css/**", "/js/**")
                    .permitAll()
                    .anyRequest().authenticated() // 나머지 요청은 인증 필요
            ).formLogin(form -> form
                    .loginPage("/member/login")
                    .loginProcessingUrl("/member/login")
                    .usernameParameter("memberLoginId")
                    .passwordParameter("memberPassword")
                    .successHandler((request, response, authentication) -> {
                        // 로그인 성공 시 원래 요청한 URI로 이동
                        SavedRequest savedRequest =
                                new HttpSessionRequestCache().getRequest(request, response);

                        if (savedRequest != null) {
                            String targetUrl = savedRequest.getRedirectUrl();
                            response.sendRedirect(targetUrl);
                        } else {
                            response.sendRedirect("/"); // 기본 경로
                        }
                    })
            ).logout(logout -> logout
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
