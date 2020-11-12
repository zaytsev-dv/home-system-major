package ru.home.system.major.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"ru.home.system.major.*"})
public class HomeSystemMajorApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(HomeSystemMajorApplication.class, args);
	}

}
