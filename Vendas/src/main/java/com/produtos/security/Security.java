package com.produtos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.produtos.service.UsuariosUserDetailsService;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UsuariosUserDetailsService usuariosUserDetailsService;
	
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests()
		.antMatchers("/materialize/**","/style/**").permitAll()
		.antMatchers("/bootstrap-5.1.3-dist/**").permitAll()
		.antMatchers("/javaScript/**").permitAll()
		.antMatchers("/", "/login").permitAll()
		.antMatchers(HttpMethod.GET,"/usuario").permitAll()
		.antMatchers(HttpMethod.GET,"/exibir/{imagem}").permitAll()
		.antMatchers(HttpMethod.POST,"/usuario").permitAll()
		.antMatchers(HttpMethod.GET,"/produtos").hasRole("ADMIM")
		
		
		.anyRequest().authenticated().and().formLogin().loginPage("/login").defaultSuccessUrl("/", true).failureUrl("/login-error").and().csrf().disable();
	}
	
	// ** .and().logout().logoutSuccessUrl("/home") **//
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.usuariosUserDetailsService).passwordEncoder(passwordEncoder());
    }
	
	@Autowired
    public void configureInMemoria(AuthenticationManagerBuilder auth) throws Exception {
			
            auth.inMemoryAuthentication().withUser("samuel").password(passwordEncoder().encode("1234")).authorities("ADMIM");
    }

}
