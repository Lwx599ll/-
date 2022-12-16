package cn.edu.sdau.demo2020214464;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {
		SecurityAutoConfiguration.class
})
public class Demo2020214464Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo2020214464Application.class, args);
	}

}
