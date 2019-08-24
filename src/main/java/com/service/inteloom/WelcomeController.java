package com.service.inteloom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
	
	@Autowired
	private WelcomeService service;
	
	@RequestMapping("/welcome")
	public String welcome() {
		String message = service.message();
		return message;
	}

}

@Component
 class WelcomeService{
	
	public String message() {
		return "Mounika!";
	}
}
