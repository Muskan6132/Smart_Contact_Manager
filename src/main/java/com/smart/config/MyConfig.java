package com.smart.config;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import jakarta.annotation.security.PermitAll;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//@Configuration
//@EnableWebSecurity
//public class MyConfig extends WebSecurityConfiguration
//{	
//    @Bean
//    public UserDetailsService getUserDetailsService()
//    {
//    	return new UserDetailsServiceImpl();
//    }
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() 
//    {
//		return new BCryptPasswordEncoder();
//	}
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider()
//    {
//    	DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//    	daoAuthenticationProvider.setUserDetailsService(this.getUserDetailsService());
//    	daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//    	return daoAuthenticationProvider;
//    }
//configure method...
//@Configuration
//public class MyConfig
//{
//	@Bean
//	public PasswordEncoder passwordEncoder() {		
//		return new BCryptPasswordEncoder();		
//	}	
//	@Bean
//	public UserDetailsService userDetailsService() {		
//		UserDetails normalUser=User.withUsername("Muskan")
//				.password(passwordEncoder().encode("password"))
//				.roles("NORMAL")
//				.build();
//		UserDetails adminUser=User.withUsername("Muskan1")
//				.password(passwordEncoder().encode("password"))
//				.roles("ADMIN")
//				.build();		
//		InMemoryUserDetailsManager inMemoryUserDetailsManager=new InMemoryUserDetailsManager(normalUser, adminUser);
//		return inMemoryUserDetailsManager;		
//	}
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {		
//		httpSecurity.csrf().disable() 
//		 .authorizeHttpRequests()
//		 .requestMatchers("/user/public") 
//	     .permitAll()
//		 .anyRequest()
//		 .authenticated()
//		 .and() 
//		 .formLogin();		
//		return httpSecurity.build();
//	}
//}


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MyConfig{
	
	@Bean
	public UserDetailsService getUserDetailsService()
	{
		return new UserDetailsServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(this.getUserDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}	
	//configure method
	@Bean
	protected AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)throws Exception
	{
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http)throws Exception
	{
		http.authorizeHttpRequests((authorizeHttpRequests)->authorizeHttpRequests
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.requestMatchers("/user/**").hasRole("USER")
				.requestMatchers("/**").permitAll());
		http.formLogin(Customizer.withDefaults());
		http.formLogin().loginPage("/signin")
		.loginProcessingUrl("/dologin")
		.defaultSuccessUrl("/user/index");
		return http.build();
	}
}

