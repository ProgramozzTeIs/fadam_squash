package pti.sb_sqashadmin_mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pti.sb_sqashadmin_mvc.dto.AdminDTO;
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

	@GetMapping("/matchs/search/user")
	public String searchByUser(Model model,
								@RequestParam("searcheduserid") int searchUserId,
								@RequestParam("userid") int userId) {
		
		MatchListDTO matchDto = null;
		
		matchDto = service.getAllMatchs(userId, searchUserId, null);
		
		model.addAttribute("matchdto", matchDto);
		
		return "index.html";
	}

	@GetMapping("/matchs/search/location")
	public String searchByLocation(Model model,
								@RequestParam("searchedlocationid") int searchLocationId,
								@RequestParam("userid") int userId) {
		
		MatchListDTO matchDto = null;
		
		matchDto = service.getAllMatchs(userId, null, searchLocationId);
		
		model.addAttribute("matchdto", matchDto);
		
		return "index.html";
	}

	@GetMapping("/match/all")
	public String showAllMatchs(Model model,
								@RequestParam("userid") int userId) {
		
		MatchListDTO matchDto = null;
		
		matchDto = service.getAllMatchs(userId, null, null);
		
		model.addAttribute("matchdto", matchDto);
		
		return "index.html";
		
	}
	
	@PostMapping("/logout")
	public String logout(@RequestParam("userid") int userId){
		
		service.logout(userId);
		
		
		
		return "login.html";
	}
	
	@GetMapping("/admin")
	public String admin(Model model,
						@RequestParam("userid") int userId) {
		
		AdminDTO adminDto = null;
		
		adminDto = service.getAdminDto(userId);
		
		model.addAttribute("admindto", adminDto);
		
		return "admin.html";
	}
	
	@PostMapping("/admin/reg/user")
	public String adminAddUser(Model model,
								@RequestParam("name")String newUserName,
								@RequestParam("uid") int userId) {
		
		
		service.addNewUser(newUserName);
		
		AdminDTO adminDto = service.getAdminDto(userId);
		
		model.addAttribute("admindto",adminDto);
		
		return "admin.html";
	}
	
	
}



