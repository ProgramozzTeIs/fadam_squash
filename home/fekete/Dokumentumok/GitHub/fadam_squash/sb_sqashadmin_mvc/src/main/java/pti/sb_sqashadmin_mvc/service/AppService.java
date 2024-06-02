package pti.sb_sqashadmin_mvc.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pti.sb_sqashadmin_mvc.db.Database;
import pti.sb_sqashadmin_mvc.dto.AdminDTO;
import pti.sb_sqashadmin_mvc.dto.LocationDTO;
import pti.sb_sqashadmin_mvc.dto.MatchDTO;
import pti.sb_sqashadmin_mvc.dto.MatchListDTO;
import pti.sb_sqashadmin_mvc.dto.UserDTO;
import pti.sb_sqashadmin_mvc.model.Location;
import pti.sb_sqashadmin_mvc.model.Match;
import pti.sb_sqashadmin_mvc.model.User;

@Service
public class AppService {

	private Database db;

	@Autowired
	public AppService() {

		this.db = new Database();
	}

	public UserDTO login(String userName, String password) {

		User user = db.getUserByNameAndPassword(userName, password);

		UserDTO userDto = null;

		if (user != null) {

			userDto = new UserDTO(user.getId(), user.isAdmin(), user.getName());

			if (user.getLastLoginDate() != null) {

				user.setLoggedin(true);
				user.setLastLoginDate(LocalDateTime.now());
				db.updateUser(user);
			}

		}

		return userDto;
	}

	public MatchListDTO getAllMatchs(int userId, Integer playerId, Integer locationId) {

		User user = db.getUserById(userId);

		MatchListDTO matchListDto = null;
		
		if (user != null && user.isLoggedin() == true) {

			List<Match> matchList = db.getAllMatchs(playerId, locationId);
			
			List<MatchDTO> matchDtoList = new ArrayList<>();
			
			List<User> users = db.getAllUser();
			
			List<Location> locations = db.getAllLocation();
			
			for(int index = 0; index < matchList.size(); index++) {
				
				Match currentMatch = matchList.get(index);
				
				String user1Name = "";
				String user2Name = "";
				
				for (int usersIndex = 0; usersIndex < users.size(); usersIndex++) {
					
					int currentUserId = users.get(usersIndex).getId();
					
					if(currentUserId == currentMatch.getUser1Id()) {
						
						user1Name = users.get(usersIndex).getName();
						
					}
					else if (currentUserId == currentMatch.getUser2Id()) {
						
						
						user2Name = users.get(usersIndex).getName(); 
						
					}
					
					
				}
				
				String locationName = "";
				int locationPrice = 0;
				
				for (int locationIndex = 0; locationIndex < locations.size(); locationIndex++) {
					
					Location currentLocation = locations.get(locationIndex);
					
					if (currentLocation.getId() == currentMatch.getPlaceId()) {
						
						locationName = currentLocation.getName();
						locationPrice = currentLocation.getPrice();
						break;
					}
					
				}
				
				MatchDTO currentMAtchDto = new MatchDTO(user1Name, currentMatch.getUser1Points(), user2Name, currentMatch.getUser2Points(), locationName, locationPrice, currentMatch.getMatchDate());
				matchDtoList.add(currentMAtchDto);
				
			}
			

			
			UserDTO userDto = getUserDTOFromUserId(userId);

			
			matchListDto = new MatchListDTO(matchDtoList, userDto, getAllUserDTO(), getAllLocalDTO());

		}
		
		return matchListDto;
	}

	public MatchListDTO setFirstLogin(int uid, String pwd1, String pwd2) {

		MatchListDTO returnDto = null;
		
		if (pwd1.equals(pwd2) && pwd1.length()>2) {

			User user = db.getUserById(uid);

			user.setLastLoginDate(LocalDateTime.now());
			user.setLoggedin(true);
			user.setPassword(pwd1);
			db.updateUser(user);
			returnDto = this.getAllMatchs(uid, null, null);
		}
		return returnDto;
	}
	public UserDTO getUserDTOFromUserId (int userId) {
		
		User user = db.getUserById(userId);
		
		
		UserDTO userDto = getUserDTOFromUser(user);
		
		return userDto;
	}
	public UserDTO getUserDTOFromUser (User user) {
	
		
		UserDTO userDto = new UserDTO(user.getId(), user.isAdmin(), user.getName());
		
		return userDto;
	}

	public void logout(int userId) {
		
		User user = db.getUserById(userId);
		
		user.setLoggedin(false);
		
		db.updateUser(user);
		
	}

	public AdminDTO getAdminDto(int userId) {
		
		AdminDTO adminDto = null;
		User admin = db.getUserById(userId);
		if (admin.isAdmin()==true) {
			
			
			UserDTO userDto = getUserDTOFromUser(admin);
			
			List<UserDTO> userDtoList = getAllUserDTO();
			
			List<LocationDTO> locationDtoList = getAllLocalDTO();
			
			
			adminDto = new AdminDTO(userDto, userDtoList, locationDtoList);
			
			
			
		}
		return adminDto;
	}
	
	private List<LocationDTO> getAllLocalDTO(){

		List<Location> locations = db.getAllLocation();
		
		List<LocationDTO> locationList = new ArrayList<>();
		
		for (int locationIndex = 0; locationIndex < locations.size(); locationIndex++) {
			
			Location CurrentLocation = locations.get(locationIndex);
			
			LocationDTO currentLocationDto = new LocationDTO(CurrentLocation.getId(), CurrentLocation.getName());
			locationList.add(currentLocationDto);
			
		}
		return locationList;
	} 


	public void addNewUser(String newUserName) {
		
		User user = new User();
		user.setId(0);
		user.setLoggedin(false);
		user.setName(newUserName);
		user.setPassword(generatePassword());
		
		db.inserNewUser(user);
		
		
	}
	
	private String generatePassword() {
		
		Random rand = new Random();
		
		String password = "";
		
		for(int index =0; index < 3; index++) {
			int number = rand.nextInt(10);
			
			String numberString = number + "";
			
			password = password + numberString;
					
		}
		return password;
	}
	
	
	
	
	private List<UserDTO> getAllUserDTO(){
		
		List<User> users = db.getAllUser();
		
		List<UserDTO> userList = new ArrayList<>();
		
		for(int userIndex = 0; userIndex < users.size(); userIndex++) { //az admint kihagyjuk a listából
			
			User currentUser = users.get(userIndex);
			if (currentUser.isAdmin()==false) {
				UserDTO currentUserDto = getUserDTOFromUser(currentUser);
				userList.add(currentUserDto);
			}
			
		}
		return userList;
	}
}
