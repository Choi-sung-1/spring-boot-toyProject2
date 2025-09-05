package com.project.toyProject2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.project.toyProject2")

public class ToyProject2Application {

	public static void main(String[] args) {
		SpringApplication.run(ToyProject2Application.class, args);
	}

}
