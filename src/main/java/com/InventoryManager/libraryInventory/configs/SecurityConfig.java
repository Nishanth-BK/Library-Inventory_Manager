package com.InventoryManager.libraryInventory.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers("/images/**","/login","/css/**","/js/**")
				.permitAll()
				.anyRequest().authenticated()
				)
		.formLogin(form -> form.loginPage("/login")
				.defaultSuccessUrl("/",true)
				.permitAll()
				)
		.logout(logout-> logout
				.logoutSuccessUrl("/login?logout")
				.permitAll()
				);
		return http.build();
	}
	@Bean
	public UserDetailsService userDetailsService() {
		return new InMemoryUserDetailsManager(
				User.withUsername("admin")
				.password(passwordEncoder().encode("admin@22"))
				.roles("USER")
				.build()
				);
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
