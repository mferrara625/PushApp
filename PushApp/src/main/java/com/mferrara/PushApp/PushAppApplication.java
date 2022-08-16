package com.mferrara.PushApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PushAppApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(PushAppApplication.class, args);
	}

}
