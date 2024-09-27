package dao;

import java.sql.SQLException;

import exception.CampusException;
import model.Staff;

public class StaffDao extends BaseDao{
	
	public StaffDao() throws CampusException{
		super();
	}
	
	public Staff doLogin(int staffId, String staffPassword) 
			throws CampusException {
		Staff loginStaff = null;
		try {
			//sql文
			String sql = "select * from staff "
					+ "where staff_id = ? AND login_password = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1,staffId);
			ps.setString(2,staffPassword);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("staff_id");
				String name = rs.getString("staff_name");
				String pw = rs.getString("login_password");
				loginStaff = new Staff(id,name,pw);
			}
			if(loginStaff == null) {
				throw new CampusException("ログインできませんでした");
			}
		} catch(SQLException e) {
			e.printStackTrace();
			throw new CampusException("SQL実行中に例外が発生しました");
		}
		return loginStaff;
	}

	//新規登録
	public void insertStaff(Staff staff) 
			throws CampusException {
		int id = staff.getStaffId();
		String name = staff.getStaffName();
		String pw = staff.getStaffPassword();
		try {
			//sql文
			String sql = "INSERT INTO staff(staff_id,staff_name,login_password VALUE(?,?,?) ";
			ps = con.prepareStatement(sql);
			ps.setInt(1,id);
			ps.setString(2, name);
			ps.setString(3,pw);
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			throw new CampusException("新規登録に失敗しました");
		}
	}
	
	//IDの重複確認
	public String checkStaffId(String Id) throws CampusException{
		String staffId = null;
		try {
			String sql = "SELECT staff_id FROM staff WHERE staff_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, staffId);
			rs = ps.executeQuery();
			while(rs.next()) {
				staffId = rs.getString("staff_id");
			}
		} catch(SQLException e) {
			e.printStackTrace();
			throw new CampusException("スタッフIDの取得に失敗しました");
		}
		return staffId;
	}
}
