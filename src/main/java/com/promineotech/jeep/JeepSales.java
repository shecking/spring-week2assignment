package com.promineotech.jeep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// class level annotation
// NOTE: 
@SpringBootApplication // (exclude={DataSourceAutoConfiguration.class})
public class JeepSales {
	
	public static void main(String[] args) {
		// calling SpringApplication
		SpringApplication.run(JeepSales.class, args);
	}

}
