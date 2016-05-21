package model;

import java.io.Serializable;

public class LoginInput implements Serializable {

	private String userId;
	private String userPass;
	private String errorMsg;

	public LoginInput(){
		userId = "";
		userPass = "";
		errorMsg = "";
	}

	public String getuserId(){return userId;}
	public String getuserPass(){return userPass;}
	public String geterrorMsg(){return errorMsg;}

	public void setuserId(String userId){this.userId = userId;}
	public void setuserPass(String userPass){this.userPass = userPass;}
	public void seterrorMsg(String errorMsg){this.errorMsg = errorMsg;}

}
