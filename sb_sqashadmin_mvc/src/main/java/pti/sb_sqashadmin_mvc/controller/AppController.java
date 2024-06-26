package pti.sb_sqashadmin_mvc.controller;




import java.io.IOException;
import java.util.Date;
import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import pti.sb_sqashadmin_mvc.dto.AdminDTO;
import pti.sb_sqashadmin_mvc.dto.MatchListDTO;
import pti.sb_sqashadmin_mvc.dto.UserDTO;
import pti.sb_sqashadmin_mvc.service.AppService;

@Controller
public class AppController {

	private AppService service;

	@Autowired
	public AppController(AppService service) {
		super();
		this.service = service;
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
		
		
		
		
		AdminDTO adminDto = service.addNewUser(newUserName, userId);
		
		model.addAttribute("admindto",adminDto);
		
		return "admin.html";
	}
	
	@PostMapping("/admin/reg/location")
	public String adminAddPlace(Model model,
								@RequestParam("locationname")String newLocationName,
								@RequestParam("uid")int userId,
								@RequestParam("address")String address,
								@RequestParam("price")int price) {
		
		AdminDTO adminDto = service.addNewLocation(newLocationName, address, price, userId);
		
		
		model.addAttribute("admindto",adminDto);
		
		return "admin.html";
	}
	@PostMapping("/admin/reg/match")
	public String adminAddMatch(Model model,
								@RequestParam("uid") int userId,
								@RequestParam("locationid")int locationId,
								@RequestParam("user1id") int user1Id,
								@RequestParam("user1point")int user1Point,
								@RequestParam("user2id") int user2Id,
								@RequestParam("user2point")int user2Point,
								@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
		
		
		AdminDTO adminDto = service.addNewMatch(userId, locationId, user1Id, user1Point, user2Id, user2Point, date);
		
		model.addAttribute("admindto",adminDto);
		
		
		return "admin.html";
	}
								
	@PostMapping("/match/export")
	public String exportToXML(Model model,
								@RequestParam("uid") int userId ,
								@RequestParam("path") String filePath) throws IOException{
		
		MatchListDTO dto = null;
		
		dto = service.getAndExportAllMatchs(userId, filePath);
		
		model.addAttribute("matchdto", dto);
		
		return "index.html";
	}
	
	@PostMapping("/match/import")
	public String importFromXml(Model model,
			@RequestParam("uid") int userId ,
			@RequestParam("xmlfile") MultipartFile file) throws JDOMException, IOException {
		
		MatchListDTO dto = null;
		
		dto = service.importFromXml(userId, file);
		
		model.addAttribute("matchdto", dto);
		return "index.html";
	}
	
}
