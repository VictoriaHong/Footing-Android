package edu.cmu.footinguidemo.model;

public class Journal {
	private String journalId;
	private String journalName;
	private int photoPath;
	private int voicePath;
	public Journal(String journalName, int photoPath, int voicePath){

		this.journalName = journalName;
		this.photoPath = photoPath;
		this.voicePath = voicePath;
	}
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

	public int getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(int photoPath) {
		this.photoPath = photoPath;
	}

	public int getVoicePath() {
		return voicePath;
	}

	public void setVoicePath(int voicePath) {
		this.voicePath = voicePath;
	}
}
