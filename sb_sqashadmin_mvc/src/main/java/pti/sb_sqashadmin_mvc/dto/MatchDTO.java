package pti.sb_sqashadmin_mvc.dto;

import java.time.LocalDate;

public class MatchDTO {

	private String user1Name;
	private int user1Points;
	private String user2Name;
	private int user2Points;
	private String placeName;
	private int placePrice;
	private LocalDate matchDate;

	public MatchDTO(String user1Name, int user1Points, String user2Name, int user2Points, String placeName,
			int placePrice, LocalDate matchDate) {
		super();
		this.user1Name = user1Name;
		this.user1Points = user1Points;
		this.user2Name = user2Name;
		this.user2Points = user2Points;
		this.placeName = placeName;
		this.placePrice = placePrice;
		this.matchDate = matchDate;
	}

	public String getUser1Name() {
		return user1Name;
	}

	public void setUser1Name(String user1Name) {
		this.user1Name = user1Name;
	}

	public int getUser1Points() {
		return user1Points;
	}

	public void setUser1Points(int user1Points) {
		this.user1Points = user1Points;
	}

	public String getUser2Name() {
		return user2Name;
	}

	public void setUser2Name(String user2Name) {
		this.user2Name = user2Name;
	}

	public int getUser2Points() {
		return user2Points;
	}

	public void setUser2Points(int user2Points) {
		this.user2Points = user2Points;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public int getPlacePrice() {
		return placePrice;
	}

	public void setPlacePrice(int placePrice) {
		this.placePrice = placePrice;
	}

	public LocalDate getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(LocalDate matchDate) {
		this.matchDate = matchDate;
	}
	
	
	
}
