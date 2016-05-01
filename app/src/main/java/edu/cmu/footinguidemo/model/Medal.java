package edu.cmu.footinguidemo.model;

import java.util.UUID;

public class Medal {
	private UUID mId;
	private String medalName;
	//private String path;
	private boolean mSolved;
	public Medal(){
		this(UUID.randomUUID());
	}
	public Medal(UUID id){
		this.mId = id;
	}
	public Medal(String medalName, boolean mSolved){

		this.medalName = medalName;
		//this.path = path;
		this.mSolved = mSolved;

	}

	public UUID getId() {
		return mId;
	}



	public String getMedalName() {
		return medalName;
	}
	public void setMedalName(String medalName) {
		this.medalName = medalName;
	}
//	public String getPath() {
//		return path;
//	}
//	public void setPath(String path) {
//		this.path = path;
//	}

	public boolean isSolved() {
		return mSolved;
	}

	public void setSolved(boolean solved) {
		mSolved = solved;
	}
}
