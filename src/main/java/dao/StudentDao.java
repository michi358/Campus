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
			String sql =
					"SELECT "	// 検索内容
					+ "student.student_number, "
					+ "student.student_name, "
					+ "staff.staff_id, "
					+ "staff.staff_name, "
					+ "memo.memo_id, "
					+ "memo.memo "
					+ "FROM "
					+ "memo JOIN "
					+ "student ON memo.student_number = student.student_number JOIN "
					+ "staff ON memo.updated_staff_id = staff.staff_id "
					+ "WHERE "
					+ "memo.student_number = ?";

			ps = con.prepareStatement(sql);
			ps.setString(1, findStudentNumber);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String studentNumber = rs.getString("student.student_number");
				String studentName = rs.getString("student.student_name");
				String staffId = rs.getString("staff.staff_id");
				String staffName = rs.getString("staff.staff_name");
				String memoId = rs.getString("memo.memo_id");
				String memo = rs.getString("memo.memo");
				studentMemo = new StudentMemo(studentNumber,studentName,staffId,staffName,memoId,memo);
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
			String sql = "SELECT * FROM student WHERE student_number = ?";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String number = rs.getString("student_number");
				String name = rs.getString("student_name");
				student = new Student(number,name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CampusException("指定された学生情報の取得に失敗しました");
		}
		return student;
	}
	
	//メモIDの検索（メモ更新、新規を分岐するのにに使用）
	public String findMemoId(String studentNumber) 
			throws CampusException{
		String memoId = null;
		try {
			String sql = "SELECT memo_id FROM memo WHERE student_number = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, studentNumber);
			rs = ps.executeQuery();
			while(rs.next()) {
				memoId = rs.getString("memo_id");
			}
		} catch(SQLException e) {
			e.printStackTrace();
			throw new CampusException("メモIDの取得に失敗しました");
		}
		return memoId;
	}
	
	//新規登録
	public void insertStudent(StudentMemo studentMemo) 
			throws CampusException{
		String studentNumber = studentMemo.getStudentNumber();
		String studentName = studentMemo.getStudentName();
		String staffId = studentMemo.getStaffId();
		String memo = studentMemo.getMemo();
		
		try {
			String sql = "INSERT INTO student "
					+ "SET(student_number,student_name)"
					+ "VALUES(?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, studentNumber);
			ps.setString(2, studentName);
			ps.executeUpdate();
			
			sql = "INSERT INTO memo"
					+ "SET(student_number, update_staff_id, memo)"
					+ "VALUES(?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, studentNumber);
			ps.setString(2, staffId);
			ps.setString(3, memo);
			ps.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
			throw new CampusException("学生情報の登録に失敗しました");
		}
	}
	
	//memoの更新
	public void updateMemo(StudentMemo studentMemo, String memoId) 
			throws CampusException{
		String memo = studentMemo.getMemo();
		String staffId = studentMemo.getStaffId();
		try {
			String sql = "UPDATE memo SET memo = ?, updated_staff_id = ? WHERE memo_id = ?";
			ps.setString(1, memo);
			ps.setString(2, staffId);
			ps.setString(3, memoId);
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			throw new CampusException("メモの更新に失敗しました");
		}
	}
}
