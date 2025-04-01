package edu.eci.cvds.reservas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * Security configuration class for setting up Spring Security.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Habilita CORS
                .csrf(AbstractHttpConfigurer::disable) // Deshabilita CSRF para evitar problemas con solicitudes desde el frontend
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Configura sesiones sin estado
                .authorizeHttpRequests(requests -> requests
                    .requestMatchers("/api/v1/auth/**").permitAll()
                    .requestMatchers("/api/v1/laboratory/all").hasAnyRole("USER", "ADMIN")
                    .requestMatchers("/api/v1/laboratory/getLaboratory/**").hasAnyRole("ADMIN", "USER")
                    .requestMatchers("/api/v1/laboratory/create/**").hasRole("ADMIN")
                    .requestMatchers("/api/v1/reservation/create").permitAll() // Permitir acceso sin autenticación
                    .requestMatchers("/api/v1/user/all").hasRole("ADMIN")
                    .requestMatchers("/api/v1/user/getByUsername/**").hasAnyRole("ADMIN", "USER")
                    .anyRequest().authenticated()
                ) // Requiere autenticación para cualquier otra solicitud
                .addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class); // Agrega el filtro JWT antes del filtro de autenticación predeterminado

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(List.of("http://localhost:3000")); // Permitir solicitudes desde el frontend
        configuration.setAllowedHeaders(List.of("Origin", "Content-Type", "Accept", "Authorization"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
    
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    /**
     * Bean for password encoding using BCrypt.
     *
     * @return a PasswordEncoder instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean for authentication manager.
     *
     * @param authenticationConfiguration the authentication configuration
     * @return an AuthenticationManager instance
     * @throws Exception if an error occurs during authentication manager creation
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Bean for JWT request filter.
     *
     * @return a JwtRequestFilter instance
     */
    @Bean
    public JwtRequestFilter jwtRequestFilter() {
        return new JwtRequestFilter();
    }
}