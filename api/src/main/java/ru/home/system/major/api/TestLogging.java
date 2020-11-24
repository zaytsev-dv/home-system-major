package ru.home.system.major.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//TODO: only for test
@RestController
@RequestMapping("/logging")
@Slf4j
public class TestLogging
{
	@RequestMapping("/{id}")
	public String getUserById(@PathVariable(required = true) final Long id) {
		log.info("Request with the id =[" + id + "]");
		return "Test: " + id;
	}
}
