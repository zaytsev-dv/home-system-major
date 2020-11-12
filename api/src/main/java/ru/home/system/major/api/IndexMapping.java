package ru.home.system.major.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexMapping
{
	@GetMapping("/")
	public String userInterface()
	{
		return "/index.html";
	}
}
