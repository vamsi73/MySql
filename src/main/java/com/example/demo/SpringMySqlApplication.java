package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringMySqlApplication implements CommandLineRunner {
@Autowired 
DataSource datasource;
	public static void main(String[] args) {
		SpringApplication.run(SpringMySqlApplication.class, args);
	}
	@Override
	public void run(String...args) throws Exception{
		System.out.print("...................................................................");

		System.out.print("DataSource is"+datasource);
	}

}
