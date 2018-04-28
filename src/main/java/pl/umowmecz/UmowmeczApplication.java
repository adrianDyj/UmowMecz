package pl.umowmecz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({"classpath:application.properties"})
public class UmowmeczApplication {

	public static void main(String[] args) {
		SpringApplication.run(UmowmeczApplication.class, args);
	}
}
