package pti.sb_sqashadmin_mvc.dto;

public class UserDTO {

	private int id;
	private boolean admin;
	private String name;

	public UserDTO(int id, boolean admin, String name) {
		super();
		this.id = id;
		this.admin = admin;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
