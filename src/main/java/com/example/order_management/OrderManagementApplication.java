package com.example.order_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//.\mvnw.cmd flyway:repair "-Dflyway.url=jdbc:mysql://localhost:3306/order_management" "-Dflyway.user=root" "-Dflyway.password=YOUR_password"

@SpringBootApplication
public class OrderManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderManagementApplication.class, args);
	}

}
