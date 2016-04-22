package edu.cmu.footinguidemo.model;

public class Journal {
	private String journalId;
	private String journalName;
	private int numOfVoices;
	private int numOfPhotos;
	public String getJournalId() {
		return journalId;
	}
	public void setJournalId(String journalId) {
		this.journalId = journalId;
	}
	public String getJournalName() {
		return journalName;
	}
	public void setJournalName(String journalName) {
		this.journalName = journalName;
	}
	public int getNumOfVoices() {
		return numOfVoices;
	}
	public void setNumOfVoices(int numOfVoices) {
		this.numOfVoices = numOfVoices;
	}
	public int getNumOfPhotos() {
		return numOfPhotos;
	}
	public void setNumOfPhotos(int numOfPhotos) {
		this.numOfPhotos = numOfPhotos;
	}
}
