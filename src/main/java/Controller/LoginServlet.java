package Controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.StaffDao;
import exception.CampusException;
import model.Staff;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//ログイン画面を表示させる（URLリクエストをServletで受け取る）
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
		
	}

	//ログイン処理
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int staffId = Integer.parseInt(request.getParameter("staffId"));
		String staffPassword = request.getParameter("staffPassword");
		String nextPage = null;
		
		try {
			StaffDao staffDao = new StaffDao();
			Staff staff = staffDao.doLogin(staffId,staffPassword);
			
			HttpSession session = request.getSession();
			session.setAttribute("staff", staff);
			request.setAttribute("message", "ログインしました");
			
			nextPage = "mypage.jsp";
			
		} catch(CampusException e) {
			String message = e.getMessage();
			request.setAttribute("message", message);
			nextPage = "login.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}

}
