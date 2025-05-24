package com.pmp.spring_elastic;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.pmp.spring_elastic.repository.jpa")
@EnableElasticsearchRepositories(basePackages = "com.pmp.spring_elastic.repository.elastic")
@EnableRabbit
public class SpringElasticApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringElasticApplication.class, args);
	}

}
