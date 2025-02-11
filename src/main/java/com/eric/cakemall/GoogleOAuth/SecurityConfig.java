package com.eric.cakemall.GoogleOAuth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())                         // 關閉 CSRF 保護
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()                      // 允許所有請求通過
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")                           // 自訂登入頁面（不會使用）
                        .defaultSuccessUrl("/oauth2/success", true)    // 成功後導向
                        .failureUrl("/login?error=true")               // 失敗後導向
                )
                .logout(logout -> logout.permitAll());                 // 允許登出
        return http.build();
    }
}
