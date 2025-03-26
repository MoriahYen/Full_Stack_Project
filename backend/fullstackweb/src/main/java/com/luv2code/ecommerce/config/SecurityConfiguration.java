package com.luv2code.ecommerce.config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers("/api/orders/**").authenticated()
                        .anyRequest().permitAll()
        );

        // ✅ 使用新版 DSL 寫法啟用 JWT
        http.oauth2ResourceServer(oauth2 ->
                oauth2.jwt(Customizer.withDefaults())
        );

        // ✅ 正確啟用 CORS（不會 deprecated）
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));

        // ✅ Content Negotiation 設定（讓錯誤訊息回傳 JSON）
        http.setSharedObject(ContentNegotiationStrategy.class,
                new HeaderContentNegotiationStrategy());

        // ✅ Okta 專用設定，讓 401 回傳 JSON 而不是 HTML
        Okta.configureResourceServer401ResponseBody(http);

        // ✅ 關閉 CSRF（前後端分離不需要）
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

    // ✅ CORS 設定：允許從 Angular 前端來的跨域請求
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of("https://localhost:4200")); // ⬅️ 你的前端網址（支援 https）
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true); // 如果需要傳送 cookie 或 Authorization header

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
