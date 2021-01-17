package com.example.psqljwt;

import com.example.psqljwt.filters.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PsqljwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(PsqljwtApplication.class, args);
	}

	//For protecting urls : all urls starting with /api/categories need token in its header
	@Bean
	public FilterRegistrationBean<AuthFilter> filterRegistrationBean() {
		FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
		AuthFilter authFilter = new AuthFilter();
		registrationBean.setFilter(authFilter);
		registrationBean.addUrlPatterns("/api/categories/*","/api/resourceserver");
		return registrationBean;
	}
}     //To make basic auth tokens in the header and only the post method works condition. Go to filter file and create
//another method which splits with "basis" not "bearer"

