package com.example.medical_helps;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@MapperScan(basePackages = {"com.example.medical_helps.model.sys.dao","com.example.medical_helps.model.app.dao"})
public class LandManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(LandManageApplication.class, args);
	}
}
