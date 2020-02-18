package com.valarcfcc.xyz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.valarcfcc.xyz.api.mapper")
public class XyzApplication {

	public static void main(String[] args) {
		SpringApplication.run(XyzApplication.class, args);
	}

}
