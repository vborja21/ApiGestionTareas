package com.apis.gestiontareas.apigestiontareas.jwt;

import com.apis.gestiontareas.apigestiontareas.jwt.filter.JwtAuthenticationFilter;
import com.apis.gestiontareas.apigestiontareas.jwt.filter.JwtValidationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Configuration
@AllArgsConstructor

public class SecurityConfig {

    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    AuthenticationManager getAuthenticationManager() throws Exception {

        return authenticationConfiguration.getAuthenticationManager();

    }

    @Bean
    PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }

    @Bean

    SecurityFilterChain filterChain (HttpSecurity http) throws Exception {

        return http.authorizeHttpRequests((authz) -> authz
                        .requestMatchers(HttpMethod.POST,"/auth/register").permitAll()
                        .requestMatchers("/tarea/**").authenticated()
                        .requestMatchers("/auth/**").authenticated()
                .anyRequest().authenticated())
                .addFilter(new JwtAuthenticationFilter(getAuthenticationManager()))
                .addFilter(new JwtValidationFilter(getAuthenticationManager()))
                .cors(c -> c.configurationSource(corsConfigurationSource()))
                .csrf(config -> config.disable())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();

        }

    @Bean

    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;

    }

}
