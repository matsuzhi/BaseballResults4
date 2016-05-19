

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

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
	private static final long serialVersionUID = 1L;

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
		try{
			Driver d = (Driver)Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

			String strConn = "jdbc:sqlserver://ybl49nyf62.database.windows.net:1433;database=BallGame;";
			String strUser = "user=matsuzhi@ybl49nyf62;";
			String strPass = "password=AshnYuk2";
			String connUrl = strConn + strUser + strPass;
			Connection con = d.connect(connUrl, new Properties());

			Statement stmt = con.createStatement();

			String SQL = "SELECT userID FROM T001_ユーザーマスター";

			ResultSet rs = stmt.executeQuery(SQL);

			while(rs.next()){
				System.out.println(rs.getString("userId"));
			}
			rs.close();
			stmt.close();
		}
		catch(Exception e){
			e.printStackTrace();
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
