package pti.sb_sqashadmin_mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pti.sb_sqashadmin_mvc.dto.MatchListDTO;
import pti.sb_sqashadmin_mvc.dto.UserDTO;
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
	
	@PostMapping("/login")
	public String login(Model model,
						@RequestParam(name = "username") String userName,
						@RequestParam(name = "password") String password) {
		
		UserDTO userDto = service.login(userName, password);
		
		String returnString = "";
		
		if(userDto == null) {
			
			returnString = "login.html";
			
		}
		
		else {
			
			MatchListDTO matchDto = service.getAllMatchs(userDto.getId(), null, null);
			
			if(matchDto!= null) {
				
				model.addAttribute("matchdto", matchDto);
				returnString = "index.html";
			}
			else {
				
				returnString = "firstlogin.html";
				model.addAttribute("userDto", userDto);
				
			}
		}
		
		return returnString;
	}
	@PostMapping("/login/changepwd")
	public String changePwd(Model model,
							@RequestParam("pwd1") String pwd1,
							@RequestParam("pwd2") String pwd2,
							@RequestParam("uid") int uid) {
		
		

		String returnString = "index.html";

		MatchListDTO matchDto = service.setFirstLogin(uid, pwd1, pwd2);
		
		model.addAttribute("matchdto", matchDto);
		
		if(matchDto == null) {

			
			UserDTO userDto = service.getUserDTOFromUserId(uid);
			
			returnString = "firstlogin.html";
			model.addAttribute("userDto",userDto);
			
		}
		
		return returnString;
	}




}



