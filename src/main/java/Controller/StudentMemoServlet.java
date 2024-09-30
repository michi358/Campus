package Controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.StudentDao;
import exception.CampusException;
import model.Staff;
import model.Student;
import model.StudentMemo;

@WebServlet("/StudentMemoServlet")
public class StudentMemoServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//学生番号の取得
		String findStudentNumber = request.getParameter("student_number");
		
		//スタッフ情報の取得
		HttpSession session = request.getSession(false);
		Staff staff = (Staff)session.getAttribute("staff");
		
		StudentMemo studentMemo = null;
		
		try {
			if(findStudentNumber != null) { //学生番号がある＝学生情報が登録済み
				StudentDao studentDao = new StudentDao();
				//学生番号からメモの有無を取得
				//存在する場合はメモに情報セット済みのものがstudentMemoに返される
				studentMemo = studentDao.findStudentMemo(findStudentNumber);
				
				//学生情報はあるが、メモがない場合
				if(studentMemo == null) {
					//①findStudent で学生情報がセットされstudentに返される
					Student student = studentDao.findStudent(findStudentNumber);
					String studentNumber = student.getStudentNumber();
					String studentName = student.getStudentName();
					//②スタッフ情報の取得から、id、nameを取得する
					String staffId = Integer.toString(staff.getStaffId());
					String staffName = staff.getStaffName();
					//①②を使用して、StudentMemoの②のコンストラクタでセットする
					studentMemo = new StudentMemo(studentNumber,studentName,staffId,staffName);
				}
			} else {
				//URLから学生番号が取得できない場合は新規登録
				String staffId = Integer.toString(staff.getStaffId());
				String staffName = staff.getStaffName();
				studentMemo = new StudentMemo(staffId, staffName);
			}
			request.setAttribute("studentMemo", studentMemo);
		} catch(CampusException e) {
			e.printStackTrace();
			String message = e.getMessage();
			request.setAttribute("message", message);
		}
		RequestDispatcher rd = request.getRequestDispatcher("showMemo.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		Staff staff = (Staff)session.getAttribute("staff");
		String staffId = Integer.toString(staff.getStaffId());;
		String staffName = staff.getStaffName();
		
		String studentNumber = request.getParameter("studentNumber");
		String studentName = request.getParameter("studentName");
		String memo = request.getParameter("memo");
System.out.println("取得" + studentNumber);
		
		String message = null;
		
		try {
			StudentDao studentDao = new StudentDao();
			String memoId = studentDao.findMemoId(studentNumber);
			
			StudentMemo studentMemo = 
					new StudentMemo(studentNumber, studentName, staffId, staffName, memoId, memo);
			if(memoId == null) {
				studentDao.insertStudent(studentMemo);
				message = "学生情報を登録しました";
			} else {
				studentDao.updateMemo(studentMemo, memoId);
				message = "メモを更新しました";
			}
			request.setAttribute("studentMemo", studentMemo);
		} catch(CampusException e) {
			message = e.getMessage();
			request.setAttribute("message", message);
			request.getRequestDispatcher("showMemo.jsp").forward(request, response);
		}
		

	}

}
