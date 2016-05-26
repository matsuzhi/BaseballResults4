package Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginInput;
import model.UserProfile;
/**
 * Servlet Filter implementation class LoginCheck
 */
@WebFilter("/Members/*")
public class LoginCheck implements Filter {

    /**
     * Default constructor.
     */
    public LoginCheck() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpSession session = ((HttpServletRequest)request).getSession();
		UserProfile prof = (UserProfile)session.getAttribute("UserProfile");
		if(prof==null){
			LoginInput inputData = new LoginInput();
			inputData.seterrorMsg("ログアウトしています。再度ログインしてください。");
			request.setAttribute("Input", inputData);
			String redirectPath;
			redirectPath = "../LoginError.jsp";
			((HttpServletResponse)response).sendRedirect(redirectPath);
		}else{

			// pass the request along the filter chain
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
