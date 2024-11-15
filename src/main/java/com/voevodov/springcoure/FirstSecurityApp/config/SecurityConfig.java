package com.voevodov.springcoure.FirstSecurityApp.config;

import com.voevodov.springcoure.FirstSecurityApp.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;




@Configuration
public class SecurityConfig {

    private final PersonDetailsService personDetailsService;

    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin(Customizer.withDefaults())
//                .logout(logout -> logout
//                       .logoutSuccessUrl("/login?logout"))
//                .build();

        return http

                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/auth/login", "/error", "/auth/registration").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults())
        //        .formLogin(form -> form
   //                     .loginPage("/auth/login"))
//                        .loginProcessingUrl("/process_login")
//                        .defaultSuccessUrl("/hello", true)
//                        .failureUrl("/auth/login?error "))
               .logout(logout -> logout
                       .logoutSuccessUrl("/auth/login"))
                .build();
        }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
