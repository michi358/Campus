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

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
		protected void doPost(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
			
			HttpSession session = request.getSession();
			Staff staff = (Staff)session.getAttribute("staff");
			int staffId = staff.getStaffId();
			String staffName = staff.getStaffName();
			String oldPw = request.getParameter("oldpassword");
			String newPw = request.getParameter("newpassword");
			Staff changePwStaff = new Staff(staffId,staffName,newPw);
			
			String nextPage = null;
			String message = null;
			try {
				StaffDao staffDao = new StaffDao();
				String checkPassword = staffDao.checkPassword(staffId, oldPw);
				
				if(checkPassword == null) {
					request.setAttribute("message", "現在のパスワードが一致しません。");
					nextPage = "changePassword.jsp";
				}else {
					staffDao.updatePassword(changePwStaff);
					session.setAttribute("staff", changePwStaff);
					request.setAttribute("message", "パスワードの変更が完了しました");
					nextPage = "mypage.jsp";
				}				
			} catch(CampusException e) {
				message = e.getMessage();
				request.setAttribute("message", message);
				nextPage = "changePassword.jsp";
			}
			RequestDispatcher rd = request.getRequestDispatcher(nextPage);
			rd.forward(request, response);
			
		}
			
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//PW変更画面を表示させる（URLリクエストをServletで受け取る）
				RequestDispatcher rd = request.getRequestDispatcher("changePassword.jsp");
				rd.forward(request, response);
	
	}

}
