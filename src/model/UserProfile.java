package model;

import java.io.Serializable;

public class UserProfile implements Serializable{
	private String userId;

	public String getuserId(){return userId;}
	public void setuserId(String userId){this.userId = userId;}
}
