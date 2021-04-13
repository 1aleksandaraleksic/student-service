package it.engineering.faculty.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.engineering.faculty.logging.LoggingInterceptor;

@Configuration
public class ApplicationConfiguration {

	@Bean
	public LoggingInterceptor loggingInterceptor() {
		return new LoggingInterceptor();
	}
}
