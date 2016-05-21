import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class SampmleServlet
 */
@WebServlet("/SampleServlet")
public class SampmleServlet extends HttpServlet {
	private static final long serialVersionUID = 2L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SampmleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<html><head></head><body>");
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//Driver d = (Driver)Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String strConn = "jdbc:sqlserver://ybl49nyf62.database.windows.net:1433;database=BallGame;";
			String strUser = "user=matsuzhi;";
			String strPass = "password=AshnYuk2";
			//String connUrl = strConn + strUser + strPass;
			String connUrl = "jdbc:sqlserver://ybl49nyf62.database.windows.net:1433;database=BallGame;user=matsuzhi@ybl49nyf62;password=AshnYuk2;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
			//Connection con = d.connect(connUrl, new Properties());
			Connection con = DriverManager.getConnection(connUrl);

			Statement stmt = con.createStatement();

			String SQL = "SELECT * FROM dbo.T001_ユーザーマスター";

			ResultSet rs = stmt.executeQuery(SQL);

			while(rs.next()){
				out.println(rs.getString("userId"));
			}
			rs.close();
			stmt.close();

		}
		catch(Exception e){
			//
			e.printStackTrace(out);
		}
		finally{
			out.println("</body></html>");
		}

		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		/*String[] luckArray = {"Very Good", "Good", "Bad"};
		int index = (int)(Math.random() * 3);
		String luck = luckArray[index];

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String today = sdf.format(date);

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>スッキリ占い</title>");
		out.println("</head>");
		out.println("<p>" + today + "の運勢は" + luck + "です。zzz</p>");
		out.println("</body>");
		out.println("</html>");
		*/

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
