package news.inboxed.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.BeanNameViewResolver;

import dev.feedhub.app.FeedsApplication;
import dev.webshares.app.WebSharesApplication;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@ComponentScan(basePackageClasses = { InboxedApplication.class, FeedsApplication.class, WebSharesApplication.class })
@EnableJdbcRepositories(basePackageClasses = { InboxedApplication.class, FeedsApplication.class, WebSharesApplication.class })
public class InboxedApplication {

	public static void main(String[] args) {
		SpringApplication.run(InboxedApplication.class, args);
	}

	@Bean
	public ViewResolver beanViewResolver() {
		return new BeanNameViewResolver();
	}

}
