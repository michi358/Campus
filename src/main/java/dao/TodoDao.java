package dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import exception.CampusException;
import model.Todo;

public class TodoDao extends BaseDao {
	
	public TodoDao() throws CampusException{
		super();
	}
	
	//ログイン中スタッフのタスク一覧
	public List<Todo> findAll(int staffId) throws CampusException{
		List<Todo> todoList = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM todo WHERE staff_id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, staffId);
			rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String task = rs.getString("task");
				Timestamp createdAt = rs.getTimestamp("createdAt");
		
				Todo todo = new Todo(id,task,createdAt,staffId);
				todoList.add(todo);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			throw new CampusException("タスクの取得に失敗しました");
		}
		return todoList;
	}

}
