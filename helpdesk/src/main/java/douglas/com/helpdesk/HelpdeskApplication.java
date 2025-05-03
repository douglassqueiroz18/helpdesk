package douglas.com.helpdesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan(basePackages = "douglas.com.helpdesk")
// @EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
// @EnableJpaRepositories(basePackages = "douglas.com.helpdesk.repositories")

@SpringBootApplication
public class HelpdeskApplication {
		public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}



}
