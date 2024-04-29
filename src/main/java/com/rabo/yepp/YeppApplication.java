package com.rabo.yepp;

import com.github.javafaker.Faker;
import com.rabo.yepp.model.Transaction;
import com.rabo.yepp.repository.Repository;
import com.rabo.yepp.repository.RepositoryImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class YeppApplication{

	public static void main(String[] args){

		SpringApplication.run(YeppApplication.class, args);
	}
}
