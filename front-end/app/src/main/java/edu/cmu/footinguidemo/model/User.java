package edu.cmu.footinguidemo.model;

public class User {
	private String userId;
	private String username;
	private String email;
	private String password;
	private String countryList;
	private String numMiles;
	private String journalList;
	private String medalList;
	private int numOfTotalVoices;
	private int numOfTotalPhotos;
	private int numOfTotalCountries;
	private int numOfTotalMiles;
	private String journalListId;
	private String achievementId;

	public User(String email, String username, String password, String countryList, String numMiles, String journalList, String medalList) {
		this.email = email;
		this.username = username;
		this.password = password;
		this.countryList = countryList;
		this.numMiles = numMiles;
		this.journalList = journalList;
		this.medalList = medalList;
	}

	public String getUsername() {
		return username;
	}
	public String getCountryList() {
		return countryList;
	}
	public String getNumMiles() {
		return numMiles;
	}
	public String getJournalList() {
		return journalList;
	}
	public String getMedalList() {
		return medalList;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getNumOfVoices() {
		return numOfTotalVoices;
	}
	public void setNumOfVoices(int numOfVoices) {
		this.numOfTotalVoices = numOfVoices;
	}
	public int getNumOfPhotos() {
		return numOfTotalPhotos;
	}
	public void setNumOfPhotos(int numOfPhotos) {
		this.numOfTotalPhotos = numOfPhotos;
	}
	public int getNumOfCountries() {
		return numOfTotalCountries;
	}
	public void setNumOfCountries(int numOfCountries) {
		this.numOfTotalCountries = numOfCountries;
	}
	public int getNumOfMiles() {
		return numOfTotalMiles;
	}
	public void setNumOfMiles(int numOfMiles) {
		this.numOfTotalMiles = numOfMiles;
	}
	public String getJournalListId() {
		return journalListId;
	}
	public void setJournalListId(String journalListId) {
		this.journalListId = journalListId;
	}
	public String getAchievementId() {
		return achievementId;
	}
	public void setAchievementId(String achievementId) {
		this.achievementId = achievementId;
	}
}
