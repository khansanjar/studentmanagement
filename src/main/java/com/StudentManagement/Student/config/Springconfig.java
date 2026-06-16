package com.StudentManagement.Student.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Springconfig {
	
	private static final String[] PUBLIC_PATH = {
			"/login",
			"/images/**",
			"/js/**",
			"/css/**" // CSS path add kiya taake login page par tailwind/css chalay
	};
		
	@Bean
	public SecurityFilterChain chain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth 
				.requestMatchers(PUBLIC_PATH).permitAll()
				.anyRequest().authenticated()
				)
				.formLogin(form -> form
						.loginPage("/login")
						.defaultSuccessUrl("/dashboard", true) // Typo "dashbord" ko sahi kar ke "/dashboard" kiya
						.permitAll())
				.logout(logout -> logout
						.logoutSuccessUrl("/login?logout") // Logout ke baad login page par bhejein
						.permitAll());
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordencode() {
		return new BCryptPasswordEncoder();
	}
}