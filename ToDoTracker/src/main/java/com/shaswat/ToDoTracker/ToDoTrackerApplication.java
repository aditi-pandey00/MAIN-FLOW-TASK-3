package com.shaswat.ToDoTracker;

import com.shaswat.ToDoTracker.fillter.Filter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ToDoTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToDoTrackerApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean jwtFilter()
	{
		FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new Filter());
		filterRegistrationBean.addUrlPatterns("/api/v1/task/*");

		return filterRegistrationBean;
	}
}
