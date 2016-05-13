package edu.cmu.footinguidemo.model;

public class Journal {
	private String journalId;
	private String journalName;
	private String journalContent;
	private String photoPath;
	private String voicePath;

	public Journal(String journalId, String journalName, String journalContent, String photoPath, String voicePath) {
		this.journalId = journalId;
		this.journalName = journalName;
		this.journalContent = journalContent;
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
	public String getJournalContent() {
		return journalContent;
	}
	public void setJournalName(String journalName) {
		this.journalName = journalName;
	}
	public void setJournalContent(String journalContent) {
		this.journalContent = journalContent;
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
