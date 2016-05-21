package model;

public class LoginLogic {

	private static String userId;
	private static String userPass;
	private static String errorMsg;


	public static void LoginCheck(LoginInput input){
		userId = input.getuserId();
		userPass = input.getuserPass();

		//文字列の値が同じか見る場合はequalsメソッドを使う
		//"=="は文字列の場合、同じオブジェクトかどうかを判別する
		if(userId.equals("matsuzhi") &&  userPass.equals("test")){
			errorMsg = "";
		}
		else{
			errorMsg = "ログインできませんでした。";
		}
		//ログイン成功の場合、エラーメッセージは空白にする
		input.seterrorMsg(errorMsg);
	}
}
