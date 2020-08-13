package com.mike;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
public class ElectronicsServiceApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(ElectronicsServiceApplication.class, args);
	}

}
