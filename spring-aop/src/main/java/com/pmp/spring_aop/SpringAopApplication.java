package com.pmp.spring_aop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pmp.spring_aop.business.BusinessService1;

@SpringBootApplication
public class SpringAopApplication implements CommandLineRunner {

	private BusinessService1 businessService1;

	public SpringAopApplication(BusinessService1 businessService1) {
		super();
		this.businessService1 = businessService1;
	}

	@Override
	public void run(String... args) throws Exception {
		this.businessService1.calculateMax();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringAopApplication.class, args);
	}

}
