package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.CampusException;
import model.Student;
import model.StudentMemo;

public class StudentDao extends BaseDao{
	
	public StudentDao() throws CampusException{
		super();
	}
	
	//学生一覧
	public List<Student> findAllStudent() 
			throws CampusException{
		List<Student> studentList = new ArrayList<>();
		try {
			//SQL文
			String sql = "SELECT * FROM student";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				String number = rs.getString("student_number");
				String name = rs.getString("student_name");
				Student student = new Student(number,name);
				studentList.add(student);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			throw new CampusException("学生情報の取得に失敗しました");
		}
		return studentList;
	}
	
	//urlから取得した学生番号で学生メモの存在確認、取得。
	//返り値は存在する場合のすべてセットされたstudentMemo,メモが存在しない場合は、findStudentの実施
	public StudentMemo findStudentMemo(String findStudentNumber) 
			throws CampusException{
		StudentMemo studentMemo = null;
		
		try {
			String sql ="SELECT "
					+ "student.student_number, student.student_name"
					+ "staff.staff_id, staff.staff_number "
					+ "memo.memo_id, memo.memo"
					+ "FROM memo JOIN student ON memo.student_number = student.student_number"
					+ "JOIN staff On memo.update_staff_id = staff.staff_id"
					+ "WHERE memo.student_number =  ?" ;
			ps = con.prepareStatement(sql);
			ps.setString(1, findStudentNumber);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String studentNumber = rs.getString("student_number");
				String studentName = rs.getString("student_name");
				String staffId = rs.getString("staff_id");
				String staffName = rs.getString("staff_name");
				String memoId = rs.getString("memo_id");
				String memo = rs.getString("memo");
				studentMemo = new StudentMemo(studentNumber,studentName,staffId,staffName,memoId,memo);
			}
			if(studentMemo == null) {
				findStudent(findStudentNumber);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			throw new CampusException("指定された学生メモの取得に失敗しました");
		}
		return studentMemo;
	}
	
	//学生番号はあるが、メモが存在しない場合に使用する学生情報の取得
	public Student findStudent(String findStudentNumber) 
			throws CampusException{
		
		Student student = null;
		try {
			String sql = "SELECT * FROM sudent WHERE student_number = ?";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String number = rs.getString("student_number");
				String name = rs.getString("student_name");
				student = new Student(number,name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CampusException("指定された学生番号の職に失敗しました");
		}
		return student;
	}
}
