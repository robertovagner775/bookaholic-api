package com.bookaholic.backend.config;

import java.util.Arrays;
import java.util.List;

import org.aspectj.weaver.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.security.config.Customizer;


import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Dynamic;

import jakarta.servlet.MultipartConfigElement;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    SecurityFilter securityFilter;


 

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                
                 .cors(Customizer.withDefaults()).
                authorizeHttpRequests(authorize -> 
                 authorize.
              
                 requestMatchers(HttpMethod.POST, "/livro/avaliacao").hasAuthority("ROLE_LEITOR")
                 .requestMatchers(HttpMethod.POST, "/biblioteca/livro").hasAuthority("ROLE_LEITOR")
                  .requestMatchers(HttpMethod.GET, "/biblioteca/listLivroById").hasAuthority("ROLE_LEITOR")
                  .requestMatchers(HttpMethod.PUT, "/biblioteca/statusAndamento").hasAuthority("ROLE_LEITOR")
                  .requestMatchers(HttpMethod.PUT, "/biblioteca/statusFinalizado").hasAuthority("ROLE_LEITOR")
                 .requestMatchers(HttpMethod.GET, "/livro/viewAvaliacao").permitAll()
                 .requestMatchers(HttpMethod.GET, "/livro/avaliacaoUsuario").permitAll()
                   .requestMatchers(HttpMethod.GET, "/livro/livroSemana").permitAll()
                                  .requestMatchers(HttpMethod.GET, "/livro/scoreSemanal").permitAll()
                 .requestMatchers(HttpMethod.POST, "/usuario/*").permitAll()
                  .requestMatchers( "/error").permitAll()
                  .requestMatchers(HttpMethod.GET, "/aa").permitAll()
                 .requestMatchers(HttpMethod.POST, "/payment/*").permitAll()
                 .requestMatchers(HttpMethod.GET, "/usuario/*").permitAll()
                 .requestMatchers(HttpMethod.GET, "/home/*").permitAll()
                 .requestMatchers(HttpMethod.POST, "/gerencia/*").permitAll()
                    .requestMatchers(HttpMethod.POST, "/files/*").permitAll()
                     .requestMatchers(HttpMethod.GET, "/gerencia/*").permitAll()
                .requestMatchers(HttpMethod.POST, "/usuario")
                .permitAll()
                .anyRequest().authenticated()

               

                
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
            
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
    
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("https://robertomeudominio.000webhostapp.com/"));
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT"));
        configuration.setAllowedHeaders(List.of("Access-Control-Allow-Origin", "*"));
         configuration.setAllowedHeaders(List.of("Access-Control-Allow-Methods", "*"));
         
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		
        return source;
	
       
    }

    
}
