package com.marks.edms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.marks.edms")
@SpringBootApplication
public class EdmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdmsApplication.class, args);
	}

}
