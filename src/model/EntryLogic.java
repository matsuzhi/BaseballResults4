package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EntryLogic {
	private static String userId;
	private static String userPass;
	private static String mailAddr;

	private static  String hashedPass;

	public static String Entry(EntryData entry){
		userId = entry.getuserId();
		userPass = entry.getuserPass();
		hashedPass = SafePassword.getStretchedPassword(userPass, userId);

		String errorMsg;
		errorMsg = SQLEntry(userId, hashedPass, mailAddr);

		return errorMsg;

	}

	private static String SQLEntry(String userId, String hashedPass, String mailAddr){
		String errorMsg = "";
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
			String checkSQL = "SELECT COUNT(*) AS CNT FROM dbo.T001_ユーザーマスター WHERE userId = ?;";
			PreparedStatement pstmt = con.prepareStatement(checkSQL);
			pstmt.setString(1, userId);

			//レコードセットをセットして、件数を取得。１ならログイン成功
			ResultSet rs = pstmt.executeQuery();
			int matchedRecordCount = 0;
			while(rs.next()){
				matchedRecordCount = rs.getInt("CNT");
			}

			if(matchedRecordCount > 0 ){
				errorMsg = "そのユーザーIDはすでに使われています。";
				con.close();
				return errorMsg;
			}


			rs.close();
			pstmt.close();

			String insertSQL = "INSERT INTO dbo.T001_ユーザーマスター (userID, userPass) VALUES(?,?);";
			PreparedStatement pstmt2 = con.prepareStatement(insertSQL);
			pstmt2.setString(1, userId);
			pstmt2.setString(2, hashedPass);
			pstmt2.executeQuery();

			pstmt2.close();

			int id;
			String selectSQL = "SELECT id FROM dbo.T001_ユーザーマスター WHERE userID = ?;";
			PreparedStatement pstmt3 = con.prepareStatement(selectSQL);
			pstmt3.setString(1, userId);
			ResultSet rs3 = pstmt3.executeQuery();
			id = 0;
			while(rs3.next()){
				id = rs3.getInt("id");
			}
			rs3.close();
			pstmt3.close();
			if(id < 1){
				errorMsg = "データベースに登録できませんでした。";
				con.close();
				return errorMsg;
			}

			String insertSQL2 = "INSERT INTO dbo.T002_配信先マスター (userId, mailAddr) VALUES(?,?);";
			PreparedStatement pstmt4 = con.prepareStatement(insertSQL2);
			pstmt4.setString(1, Integer.toString(id));
			pstmt4.setString(2, mailAddr);
			pstmt4.executeQuery();

			pstmt4.close();
			con.close();
		}
		catch(Exception e){
			e.printStackTrace();
			errorMsg = "データベース登録中にエラーが発生しました。";
		}
		return errorMsg;
	}

}
