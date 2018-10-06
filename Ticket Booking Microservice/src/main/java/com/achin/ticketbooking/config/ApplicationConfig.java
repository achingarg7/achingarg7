package com.achin.ticketbooking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableWebSecurity
public class ApplicationConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getApiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.achin.ticketbooking.controller"))
				.paths(PathSelectors.any()).build();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		// http.authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated();
		http.httpBasic().and().authorizeRequests().antMatchers("/movie/**").hasRole("ADMIN")
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.httpBasic().and().authorizeRequests().antMatchers("/tickets/**").hasRole("USER")
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	private ApiInfo getApiInfo() {
		Contact contact = new Contact("Achin Garg", "github: achingarg7/achingarg7", "achingarg7@gmail.com");
		return new ApiInfoBuilder().title("Ticket Booking").description("Api's for Ticket Booking").version("1.0.0")
				.contact(contact).build();
	}

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).withUser("achin")
				.password("achin").roles("ADMIN");
		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).withUser("dev")
		.password("dev").roles("USER");
	}
}