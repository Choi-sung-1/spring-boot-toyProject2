package com.project.toyProject2.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer {
//   spring security 주입
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    // RequestCache 객체 생성
    HttpSessionRequestCache requestCache = new HttpSessionRequestCache();

    http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/", "/member/join", "/member/login","/productImages/**",
                            "/product/detail/**", "/product/list",
                            "/css/**", "/js/**").permitAll()
                    .anyRequest().authenticated()
            )
            // RequestCache 등록
            .requestCache(cache -> cache.requestCache(requestCache))
            .formLogin(form -> form
                    .loginPage("/member/login")
                    .loginProcessingUrl("/member/login")
                    .usernameParameter("memberLoginId")
                    .passwordParameter("memberPassword")
                    .successHandler((request, response, authentication) -> {

                        // 등록된 requestCache 사용
                        SavedRequest savedRequest = requestCache.getRequest(request, response);

                        if (savedRequest != null) {
                            String targetUrl = savedRequest.getRedirectUrl();
                            response.sendRedirect(targetUrl);
                        } else {
                            response.sendRedirect("/product/list");
                        }
                    })
            )
            .logout(logout -> logout
                    .logoutUrl("/member/logout")
                    .logoutSuccessUrl("/")
            );

    return http.build();
}

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/productImages/**")
                .addResourceLocations("file:/Users/ChoiSungWon/Documents/Develope/spring/project/toyProject2/src/main/resources/static/images");
    }

    //    BCryptPassword 비밀번호 암호화 주입
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
