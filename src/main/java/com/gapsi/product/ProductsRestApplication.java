package com.gapsi.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.gapsi.product.controllers")
public class ProductsRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsRestApplication.class, args);
	}
}
