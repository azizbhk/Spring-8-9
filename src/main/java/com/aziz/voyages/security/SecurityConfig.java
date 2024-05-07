package com.aziz.voyages.security;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests

				.requestMatchers("/showCreate", "/savevoyage").hasAnyAuthority("ADMIN", "AGENT")
				.requestMatchers("/modifiervoyage", "/supprimervoyage").hasAuthority("ADMIN")
				.requestMatchers("/Listevoyges").hasAnyAuthority("ADMIN", "AGENT", "USER").anyRequest().authenticated())

				.formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults())
				.exceptionHandling((exception) -> exception.accessDeniedPage("/accessDenied"));
		return http.build();
		
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}


	 @Bean
	    public InMemoryUserDetailsManager userDetailsService() {
	      PasswordEncoder passwordEncoder = passwordEncoder();
	    	
	    	UserDetails admin = User
	    			.withUsername("admin")
	                .password(passwordEncoder.encode("123"))
	                .authorities("ADMIN")
	                .build();
	        UserDetails userAziz = User
	                .withUsername("aziz")
	                .password(passwordEncoder.encode("123"))
	                .authorities("AGENT","USER")
	                .build();
	        UserDetails user1 = User
	                .withUsername("user1")
	                .password(passwordEncoder.encode("123"))
	                .authorities("USER")
	                .build(); 
	         
	        return new InMemoryUserDetailsManager(admin,userAziz,user1);
	        
	}
}