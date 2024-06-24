package pti.sb_sqashadmin_mvc.dto;

import java.util.List;

public class AdminDTO {

	private UserDTO user;
	private List<UserDTO> userList;
	private List<LocationDTO> locationList;

	public AdminDTO(UserDTO user, List<UserDTO> userList, List<LocationDTO> locationList) {
		super();
		this.user = user;
		this.userList = userList;
		this.locationList = locationList;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public List<UserDTO> getUserList() {
		return userList;
	}

	public void setUserList(List<UserDTO> userList) {
		this.userList = userList;
	}

	public List<LocationDTO> getLocationList() {
		return locationList;
	}

	public void setLocationList(List<LocationDTO> locationList) {
		this.locationList = locationList;
	}
	
	
	
}
