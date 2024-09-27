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

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		int intId = Integer.parseInt(id);
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		String nextPage = null;
		String message = null;
		try {
			StaffDao staffDao = new StaffDao();
			String checkStaffId = staffDao.checkStaffId(id);
			Staff insertStaff= new Staff(intId,name,password);
			if(checkStaffId == null) {
				staffDao.insertStaff(insertStaff);
			} else {
				request.setAttribute("message", "このIDはすでに使われております。");
				nextPage = "register.jsp";
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("staff", insertStaff);
			request.setAttribute("message", "新規登録が完了しました");
			nextPage = "mypage.jsp";
			
		} catch(CampusException e) {
			message = e.getMessage();
			request.setAttribute("message", message);
			nextPage = "login.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//新規登録画面を表示させる（URLリクエストをServletで受け取る）
		RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
		rd.forward(request, response);
		
	}

}
