package us.filin.routerra.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class PlaceholderTestApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(PlaceholderTestApplication.class, args);
	}

}
