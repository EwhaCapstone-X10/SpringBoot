package DriveMate.spring.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // CSRF 비활성화 (테스트 환경에서만 사용)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/member/signup").permitAll()  // /member/signup 엔드포인트는 인증 없이 허용
                        .anyRequest().authenticated()  // 나머지 요청은 인증 필요
                );
        return http.build();
    }
}
