package com.marks.edms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.marks.edms")
@MapperScan("com.marks.edms.dao") //添加@Mapper注解
@SpringBootApplication
public class EdmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdmsApplication.class, args);
	}

}
