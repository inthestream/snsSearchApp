package com.yun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.yun"})
public class SnsSearchAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnsSearchAppApplication.class, args);
	}
}
