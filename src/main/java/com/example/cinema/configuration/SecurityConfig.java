package com.example.cinema.configuration;

import com.example.cinema.dtos.response.MyUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
//endpoints public
    protected static final String SINGER_KEY ="sZ23z2HCJbBarooMFcwW2A4eIqfJ9p04hPhRl6fp+0/BVWBQI3CmGebw7fnWftca";
    private final String[] PUBLIC_ENDPOINTS = {"/users/adduser","/auth/login","/auth/introspect","/home"};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(HttpMethod.POST,PUBLIC_ENDPOINTS).permitAll()
                .requestMatchers(HttpMethod.GET, "/users").hasAuthority("SCOPE_ROLE_ADMIN")
                .requestMatchers(HttpMethod.GET, "/users").hasAuthority("SCOPE_ROLE_ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/schedules/{id}").hasAuthority("SCOPE_ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/schedules/{id}").hasAuthority("SCOPE_ROLE_ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/statistics/discounts").hasAuthority("SCOPE_ROLE_ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/statistis/schedules").hasAuthority("SCOPE_ROLE_ADMIN")

                .anyRequest().authenticated())
        ;
//check oauth
        http.oauth2ResourceServer(oauth2r ->
            oauth2r.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder()))
        );

        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    JwtDecoder jwtDecoder(){
        SecretKeySpec secretKeySpec = new SecretKeySpec(SINGER_KEY.getBytes(),"HS512");
        return NimbusJwtDecoder
                .withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build()
                ;
    }
    public static MyUser getPrincipal() {
        return (MyUser) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
    }
}
