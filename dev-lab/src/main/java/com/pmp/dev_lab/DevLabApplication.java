package com.pmp.dev_lab;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.pmp.dev_lab.rest.rest_template.RestTemplateMain;

@SpringBootApplication
public class DevLabApplication {

	public static void main(String[] args) {
		// SpringApplication.run(DevLabApplication.class, args);
		executeBean(args);
	}

	public static void executeBean(String[] args) {
		try (var content = new AnnotationConfigApplicationContext(DevLabApplication.class)) {
			var bean = content.getBean(RestTemplateMain.class);
			bean.getTodos();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
