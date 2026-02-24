package com.example.DesWeb.config;

import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;    
import org.springframework.security.config.Customizer;
import org.springframework.http.HttpMethod;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       http
           .csrf(csrf -> csrf.disable())
           .authorizeHttpRequests(auth -> auth
            //sin auth
               .requestMatchers(HttpMethod.GET,"/api/usuarios").permitAll()
               .requestMatchers(HttpMethod.GET,"/api/params").permitAll()

               //con auth
               .requestMatchers(HttpMethod.POST,"/api/usuarios").authenticated()
               .requestMatchers(HttpMethod.GET,"/api/usuarios/*").authenticated()

               .anyRequest().denyAll()
           )
           .httpBasic(Customizer.withDefaults());
                
           return http.build();
    }

  // Usuario fijo para autenticación""""BUSCAR BIEN""""
    @Bean
    UserDetailsService users(PasswordEncoder encoder) {
        UserDetails admin = User.withUsername("admin")
            .password(encoder.encode("admin123"))
            .roles("ADMIN")
            .build();
        return new InMemoryUserDetailsManager(admin);
    }
    
   @Bean
    PasswordEncoder passwordEncoder() {
         return new BCryptPasswordEncoder();
    }
    
}
