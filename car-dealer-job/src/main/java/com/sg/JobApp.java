package com.sg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(basePackages = "com.sg.mapper")
@EnableScheduling
public class JobApp {

	public static void main(String[] args) {
		SpringApplication.run(JobApp.class, args);
	}

}
