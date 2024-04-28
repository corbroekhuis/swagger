package com.rabo.yepp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class YeppApplication  implements CommandLineRunner {

	@Value("${server.port}")
	private String port;

	public static void main(String[] args){

		ApplicationContext ctx = SpringApplication.run(YeppApplication.class, args);
		Arrays.stream(ctx.getBeanDefinitionNames())
				.filter( beanName-> ctx.getBean(beanName).getClass().getPackageName().startsWith("com.rabo.yepp"))
				.toList().forEach(System.out::println);

	}

	@Override
	public void run(String... args) throws Exception {
		if( port == null) port = "8080";
		System.out.println("http://localhost:" + port + "/swagger-ui.html");
	}
}
