package com.fullstack.gateway.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.fullstack.gateway.filter.PreRequestFilter;
import com.fullstack.gateway.gen.SnowflakeIdGenerator;
import com.fullstack.gateway.service.GatewayAccessLogsService;

@Configuration
//@EnableOAuth2Sso
//@EnableResourceServer
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SnowflakeIdGenerator snowflakeIdGenerator;

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private GatewayAccessLogsService gatewayAccessLogsService;

	@Override
	public void configure(WebSecurity web) throws Exception {
		final String[] SWAGGER_UI = { "/swagger-resources/**", "/swagger-ui.html", "/v2/api-docs", "/webjars/**",
				"/actuator/**"};

		web.ignoring().antMatchers(SWAGGER_UI);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {

		// 网关日志前置过滤器。把cors, csrf, x-frame-options的危险都预先屏蔽掉。
		http.addFilterBefore(new PreRequestFilter(snowflakeIdGenerator, redisTemplate, gatewayAccessLogsService),
				AbstractPreAuthenticatedProcessingFilter.class).cors().and().csrf().disable().headers().frameOptions()
				.disable();

		 http
		 //HTTP Basic authentication
		 .httpBasic()
		 .and()
		 .authorizeRequests()
		 .antMatchers("/user-portal/**", "/mentor-portal/**", "/admin-portal/**", "/training-portal/**")
		 .hasAnyRole("user","admin")
		 .and()
		 .formLogin().disable().cors().configurationSource(corsConfigurationSource());
	}

	 @Override
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception
	 {
	 auth
	 .inMemoryAuthentication()
	 .withUser("rexdedao").password(passwordEncoder().encode("Passw0rd!")).roles("admin").and()
	 .withUser("user").password(passwordEncoder().encode("Passw0rd!")).roles("user");
	 }

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE"));
		 configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.setAllowCredentials(false);
		configuration.setMaxAge(3600L);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
