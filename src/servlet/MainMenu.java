package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MainMenu
 */
@WebServlet("/MainMenu")
public class MainMenu extends HttpServlet {
	private static final long serialVersionUID = 4L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainMenu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

		String redirectPath;
		String action =  request.getParameter("action");
		switch (action){
		case "logout":
			redirectPath = "login";
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect(redirectPath);
			break;
		default:
			break;
		}
	}
}
