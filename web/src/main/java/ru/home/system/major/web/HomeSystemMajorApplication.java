package ru.home.system.major.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"ru.home.system.major.*"})
@EntityScan(basePackages = {"ru.home.system.major.core.domain"})
@EnableJpaRepositories(basePackages = {"ru.home.system.major.core.repository"})
public class HomeSystemMajorApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(HomeSystemMajorApplication.class, args);
	}

}
