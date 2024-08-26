// src/main/java/com/flight/config/WebConfig.java
package com.flight.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Indicates that this is a configuration class
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// Configures CORS settings for the application
		registry.addMapping("/**") // Allows CORS for all paths
				.allowedOrigins("http://localhost:3000") // Permits requests from the specified React app URL
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Specifies allowed HTTP methods
				.allowedHeaders("*"); // Allows all headers in requests
	}
}
