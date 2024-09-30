package Controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.TodoDao;
import exception.CampusException;
import model.Staff;
import model.Todo;

@WebServlet("/TodoServlet")
public class TodoServlet extends HttpServlet {
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ログインスタッフ情報の取得
		HttpSession session = request.getSession(false);
		Staff staff = (Staff)session.getAttribute("staff");
		int staffId = staff.getStaffId();
		
		try{
			TodoDao todoDao = new TodoDao();
			List<Todo> todoList = todoDao.findAll(staffId);
			
			request.setAttribute("todoList", todoList);
		} catch(CampusException e){
			String message = e.getMessage();
			request.setAttribute("message", message);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/mypage.jsp") ;
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
