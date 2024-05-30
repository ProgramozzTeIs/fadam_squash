package pti.sb_sqashadmin_mvc.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pti.sb_sqashadmin_mvc.db.Database;
import pti.sb_sqashadmin_mvc.dto.MatchListDTO;
import pti.sb_sqashadmin_mvc.dto.UserDTO;
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

	public MatchListDTO getAllMatchs(int id) {

		User user = db.getUserById(id);

		MatchListDTO matchListDto = null;

		if (user != null && user.isLoggedin() == true) {

			matchListDto = new MatchListDTO();

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
			returnDto = this.getAllMatchs(uid);
		}
		return returnDto;
	}
	public UserDTO getUserDTOFromUser (int userId) {
		
		User user = db.getUserById(userId);
		
		
		UserDTO userDto = new UserDTO(user.getId(), user.isAdmin(), user.getName());
		
		return userDto;
	}

}
