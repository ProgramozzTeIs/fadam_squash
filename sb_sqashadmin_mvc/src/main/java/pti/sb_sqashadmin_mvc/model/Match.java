package pti.sb_sqashadmin_mvc.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "matchs")
public class Match {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "user1id")
	private int user1Id;
	
	@Column(name = "user2id")
	private int user2Id;
	
	@Column(name = "user1points")
	private int user1Points;
	
	@Column(name = "user2points")
	private int user2Points;
	
	
	@Column(name = "placeid")
	private int placeId;
	
	@Column(name = "matchdate")
	private LocalDate matchDate;


	public LocalDate getMatchDate() {
		return matchDate;
	}


	public void setMatchDate(LocalDate matchDate) {
		this.matchDate = matchDate;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getUser1Id() {
		return user1Id;
	}


	public void setUser1Id(int user1Id) {
		this.user1Id = user1Id;
	}


	public int getUser2Id() {
		return user2Id;
	}


	public void setUser2Id(int user2Id) {
		this.user2Id = user2Id;
	}


	public int getUser1Points() {
		return user1Points;
	}


	public void setUser1Points(int user1Points) {
		this.user1Points = user1Points;
	}


	public int getUser2Points() {
		return user2Points;
	}


	public void setUser2Points(int user2Points) {
		this.user2Points = user2Points;
	}


	public int getPlaceId() {
		return placeId;
	}


	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}
	
	
}
