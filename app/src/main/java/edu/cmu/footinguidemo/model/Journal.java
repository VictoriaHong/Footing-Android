package edu.cmu.footinguidemo.model;

public class Journal {
	private String journalId;
	private String journalName;
	private String photoPath;
	private String voicePath;
	public Journal(String journalName, String photoPath, String voicePath){

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

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getVoicePath() {
		return voicePath;
	}

	public void setVoicePath(String voicePath) {
		this.voicePath = voicePath;
	}
}
