package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginInput;
import model.LoginLogic;
import model.UserProfile;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		//Getで来た時はログイン画面に飛ばす。
		//ユーザーID、パスワード、エラーメッセージは空白
		String forwardPath;
		forwardPath = "/login.jsp";
		LoginInput inputData = new LoginInput();
		request.setAttribute("Input",  inputData);
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//フォームから受け取った値をセット
		String userId = request.getParameter("userId");
		String userPass = request.getParameter("userPass");
		String errorMsg;

		//フォームから受け取ったIDとパスワードを渡して、一致するか確認する
		LoginInput inputData = new LoginInput();
		inputData.setuserId(userId);
		inputData.setuserPass(userPass);
		LoginLogic.LoginCheck(inputData);

		//エラーメッセージが空白かどうかで、ログイン可否を判定
		errorMsg = inputData.geterrorMsg();

		//エラーメッセージとユーザー情報を次のページに渡す
		request.setAttribute("Input",  inputData);

		//ログイン成功時、セッションを設定
		//（失敗時はすぐに破棄）
		HttpSession session = request.getSession();
		String forwardPath;
		if(errorMsg == ""){

			//ログイン成功時は、登録されたユーザー情報を渡す
			//今はユーザーIDだけ
			UserProfile userProf = new UserProfile();
			userProf.setuserId(userId);
			session.setAttribute("UserProfile", userProf);

			//ログイン成功時はダッシュボードにフォワード（リダイレクトの方がいいな）
			forwardPath = "/Dashboard.jsp";

		}else{

			//ログイン失敗時はセッション破棄
			session.invalidate();

			//エラーメッセージを表示させるために渡す
			request.setAttribute("errorMsg", errorMsg);

			//ログイン失敗時はログイン画面にフォワード
			forwardPath = "/login.jsp";
		}

		//次のページに飛ばす（ログイン成功時はリダイレクトのほうがいい）
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

}
