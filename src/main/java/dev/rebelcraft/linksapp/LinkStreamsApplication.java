package dev.rebelcraft.linksapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.BeanNameViewResolver;

@SpringBootApplication
@EnableAsync
public class LinkStreamsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinkStreamsApplication.class, args);
	}

	@Bean
	public ViewResolver beanViewResolver() {
		return new BeanNameViewResolver();
	}

}
