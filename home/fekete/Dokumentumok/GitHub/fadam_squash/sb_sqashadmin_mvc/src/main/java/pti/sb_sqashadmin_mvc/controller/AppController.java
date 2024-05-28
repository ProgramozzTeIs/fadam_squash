package pti.sb_sqashadmin_mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pti.sb_sqashadmin_mvc.service.AppService;

@Controller
public class AppController {

	private AppService service;
	@Autowired
	public AppController() {
		
		this.service = new AppService();
	}
	
	@GetMapping("/")
	public String firstPage() {
		
		
		return "login.html";
	}
	
	@GetMapping("/login")
	public String login(Model model,
						@RequestParam(name = "username") String userName,
						@RequestParam(name = "password") String password) {
		
		UserDto dto = service.login(userName, password);
		
		
		return"";
	}
}
