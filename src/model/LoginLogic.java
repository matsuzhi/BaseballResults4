package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginLogic {

	private static String userId;
	private static String userPass;
	private static String errorMsg;

	//ハッシュ化したパスワード
	private static String hashPass;

	public static void LoginCheck(LoginInput input){
		userId = input.getuserId();
		userPass = input.getuserPass();

		hashPass = SafePassword.getStretchedPassword(userPass, userId);


		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			//接続文字列を作成（Azureポータルから持ってきただけ）
			String connUrl = "jdbc:sqlserver://ybl49nyf62.database.windows.net:1433;";
			connUrl = connUrl + "database=BallGame;user=matsuzhi@ybl49nyf62;password=AshnYuk2;";
			connUrl = connUrl + "encrypt=true;trustServerCertificate=false;";
			connUrl = connUrl + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
			//データベース接続
			Connection con = DriverManager.getConnection(connUrl);

			//エスケープしたSQL発行
			String SQL = "SELECT COUNT(*) AS CNT FROM dbo.T001_ユーザーマスター WHERE userId = ? AND userPass = ?;";
			PreparedStatement pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, userId);
			pstmt.setString(2, hashPass);

			//レコードセットをセットして、件数を取得。１ならログイン成功
			ResultSet rs = pstmt.executeQuery();
			int matchedRecordCount = 0;
			while(rs.next()){
				matchedRecordCount = rs.getInt("CNT");
			}

			rs.close();
			pstmt.close();
			con.close();


			//ユーザーIDとパスワードが一致する組が存在したらログイン成功
			if(matchedRecordCount == 1){
				errorMsg = "";								//ログイン成功の場合、エラーメッセージは空白
			}
			else{
				errorMsg = "ログインできませんでした。";	//ログイン失敗の場合はエラーメッセージを出さない
			}

			input.seterrorMsg(errorMsg);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
