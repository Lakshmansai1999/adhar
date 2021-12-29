package com.example.demoadhar.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demoadhar.service.UserDetailsServiceImpl;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder userPasswordEncoder;
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(userPasswordEncoder);
    }
    
    @Bean
    public PasswordEncoder oauthClientPasswordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Bean
    public PasswordEncoder userPasswordEncoder() {
        return new BCryptPasswordEncoder(8);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
     
    	http.csrf().disable().authorizeRequests().antMatchers("/oauth/token").permitAll()
    	.antMatchers("/api").hasAnyAuthority("ROLE_SUPER_ADMIN")
    	.antMatchers("/adharusers/**").hasAuthority("ROLE_ADMIN").anyRequest()
    	.authenticated().and().formLogin().disable();
    	
    	/*
    	 * http.csrf().disable().authorizeRequests().antMatchers("/oauth/token").permitAll()
  	.antMatchers("/api").hasAnyAuthority("ROLE_MOBILE_SUPER_ADMIN")
    	.antMatchers("/mobilecompany/**","/mobilemodel/**","/Distributiondetails/**").hasAuthority("ROLE_MOBILECOMPANY").anyRequest()
    	//.antMatchers("/Distributiondetails/**").hasAuthority("ROLE_MOBILECOMPANY")
    	
    	.authenticated().and().formLogin().disable();
    	 */
    	
    	/*
    	 *  protected void configure(HttpSecurity http) throws Exception {
     
    	http.csrf().disable().authorizeRequests().antMatchers("/api/get").permitAll()
    	.antMatchers("/user").hasAnyAuthority("SUPER_ADMIN").anyRequest()
    	.authenticated().and().formLogin().disable();
    	 */
}
}