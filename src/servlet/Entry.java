package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EntryData;
import model.EntryLogic;
import model.LoginInput;

/**
 * Servlet implementation class Entry
 */
@WebServlet(description = "サインアップ", urlPatterns = { "/entry" })
public class Entry extends HttpServlet {
	private static final long serialVersionUID = 4L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Entry() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String forwardPath;
		forwardPath = "entry.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPass = request.getParameter("userPass");
		String mailAddr = request.getParameter("mailAddr");
		String errorMsg;

		//入力項目のヌルチェック
		if(userId.equals("") || userPass.equals("") || mailAddr.equals("")){
			errorMsg = "全ての項目を入力してください。";
		}
		else{
			EntryData entryData = new EntryData();
			entryData.setuserId(userId);
			entryData.setuserPass(userPass);
			entryData.setmailAddr(mailAddr);
			//データベースの既存チェックと登録
			errorMsg = EntryLogic.Entry(entryData);
		}


		if(errorMsg.equals("")){
			LoginInput loginInput = new LoginInput();
			String redirectPath = "login.jsp";
			request.setAttribute("Input", loginInput);
			response.sendRedirect(redirectPath);
		}else{
			request.setAttribute("errorMsg", errorMsg);
			String forwardPath = "entry.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
			dispatcher.forward(request, response);
		}

		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
