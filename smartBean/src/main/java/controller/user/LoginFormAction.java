	package controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.ha.backend.Sender;

import model.user.UserDao;
import model.user.UserVo;

/**
 * Servlet implementation class LoginFormAction
 */
public class LoginFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginFormAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		UserDao userDao = UserDao.getInstance();
		UserVo user = userDao.getUserByEmail(email);
		
		HttpSession session = request.getSession();
		if(user != null && user.getPassword().equals(password)) {
			session.setAttribute("log", email);
			session.setAttribute("name", user.getName());
			
			response.sendRedirect("/");
		} else {
			request.setAttribute("message", "이메일 또는 비밀번호가 올바르지 않습니다.");
			request.getRequestDispatcher("alert").forward(request, response);
		}
		
	}

}
