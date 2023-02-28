package com.woxsen.leagueapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

//	@Value("cors.allowedOrings")
//	private String allowedOrigins;

	public void addCorsMappings(CorsRegistry registry) {
		final long MAX_AGE_SECS = 3600;

		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("GET", "POST", "PUT", "DELETE")
				.allowedHeaders("*")
				.maxAge(MAX_AGE_SECS);
	}
}
