package pti.sb_sqashadmin_mvc.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "users")
public class User {
	
	

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id")
		private int id;
	
		@Column(name = "name")
		private String name;
		
		@Column(name = "password")
		private String password;
		
		@Column(name = "admin")
		private boolean admin;
		
		@Column(name = "loggedin")
		private boolean loggedin;
		
		
		@Column(name = "lastlogindate")
		private LocalDateTime lastLoginDate;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public boolean isAdmin() {
			return admin;
		}

		public void setAdmin(boolean admin) {
			this.admin = admin;
		}

		public boolean isLoggedin() {
			return loggedin;
		}

		public void setLoggedin(boolean loggedin) {
			this.loggedin = loggedin;
		}


		public LocalDateTime getLastLoginDate() {
			return lastLoginDate;
		}

		public void setLastLoginDate(LocalDateTime lastLoginDate) {
			this.lastLoginDate = lastLoginDate;
		}
		
}
