package model;

import java.io.Serializable;

public class EntryData implements Serializable {
	private String userId;
	private String userPass;
	private String mailAddr;

	public void setuserId(String userId){this.userId = userId;}
	public void setuserPass(String userPass){this.userPass = userPass;}
	public void setmailAddr(String mailAddr){this.mailAddr = mailAddr;}

	public String getuserId(){return userId;}
	public String getuserPass(){return userPass;}
	public String getmailAddr(){return mailAddr;}
}
