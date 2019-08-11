package com.bioprac.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import static org.springframework.http.HttpMethod.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	public void configureGlobal1(final AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("jeremy").password("{noop}password").roles("USER", "ADMIN");
		
	}
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		
		http.httpBasic()
			.disable()
			.authorizeRequests()
			.antMatchers(GET, "/questions").permitAll()
			.antMatchers(GET, "/questions/*").permitAll()
			.antMatchers(POST, "/questions").hasRole("ADMIN")
			.antMatchers(DELETE, "/questions").hasRole("ADMIN")
			.antMatchers(PUT, "/questions").hasRole("ADMIN")
			.anyRequest()
			.authenticated()
			.and()
			.csrf()
			.disable();
		
	}

}
