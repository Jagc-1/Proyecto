package com.jpa.proyecto.security.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jpa.proyecto.domain.repositories.CuentaRepository;



@Configuration // clase de configuracion
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {



    @Autowired
    private JwtAutorizacionFiltro jwtAuthorizationFilter;
    

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.
        authorizeHttpRequests(authorize  -> authorize
        .requestMatchers("/api/**").permitAll()
        .anyRequest().authenticated())
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session  -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authenticationProvider(authenticationProvider())
        .addFilterBefore(jwtAuthorizationFilter ,UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    
    @Autowired
	CuentaRepository repository;

	@Bean
	public DaoAuthenticationProvider authenticationProvider(){
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration  auth) throws Exception{
        return auth.getAuthenticationManager();
    }

   
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); //algoritmo de encriptaciónn
    }

	@Bean
	public UserDetailsService userDetailsService(){
		return username -> repository.findByName(username)
		.orElseThrow(() -> new RuntimeException("User not found"));
	}
    


}
