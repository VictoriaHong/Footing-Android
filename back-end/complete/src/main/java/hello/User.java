package hello;

public class User {
	String userName;
	String email;
	String countryList;
	String journalList;
	String medalList;
	String miles;
	

	public User(String email, String userName, String countryList, String miles, String journalList,
			String medalList) {
		this.userName = userName;
		this.email = email;
		this.countryList = countryList;
		this.journalList = journalList;
		this.medalList = medalList;
		this.miles = miles;
	}
	public String getMiles() {
		return miles;
	}
	public void setMiles(String miles) {
		this.miles = miles;
	}

	public String getJournalList() {
		return journalList;
	}
	public void setJournalList(String journalList) {
		this.journalList = journalList;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCountryList() {
		return countryList;
	}
	public void setCountryList(String countryList) {
		this.countryList = countryList;
	}
	public String getMedalList() {
		return medalList;
	}
	public void setMedalList(String medalList) {
		this.medalList = medalList;
	}
	

	public void userPrint(){
		System.out.println(email+" "+userName + " " + countryList +" " + miles +" "+ journalList +"" + medalList);
	}
}
