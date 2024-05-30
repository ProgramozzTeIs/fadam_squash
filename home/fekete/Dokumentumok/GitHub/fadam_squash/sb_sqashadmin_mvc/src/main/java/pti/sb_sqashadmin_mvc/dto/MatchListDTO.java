package pti.sb_sqashadmin_mvc.dto;

import java.util.List;

public class MatchListDTO {

	private List<MatchDTO> matchDtos;
	private UserDTO userDto;
	private List<UserDTO> userList;
	private List<LocationDTO> locationList;

	
	public MatchListDTO(List<MatchDTO> matchDtos, UserDTO userDto, List<UserDTO> userList,
			List<LocationDTO> locationList) {
		super();
		this.matchDtos = matchDtos;
		this.userDto = userDto;
		this.userList = userList;
		this.locationList = locationList;
	}


	public List<MatchDTO> getMatchDtos() {
		return matchDtos;
	}


	public void setMatchDtos(List<MatchDTO> matchDtos) {
		this.matchDtos = matchDtos;
	}


	public UserDTO getUserDto() {
		return userDto;
	}


	public void setUserDto(UserDTO userDto) {
		this.userDto = userDto;
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
