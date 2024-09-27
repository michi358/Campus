package model;

public class Staff {
	private int staffId;
	private String staffName;
	private String staffPassword;
	
	public Staff(int staffId, String staffName, String staffPassword) {
		this.staffId = staffId;
		this.staffName = staffName;
		this.staffPassword = staffPassword;
	}
	
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public int getStaffId() {
		return staffId;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getStaffName() {
		return staffName;
	}
	
	public void setStaffPassword(String staffPassword) {
		this.staffPassword = staffPassword;
	}
	public String getStaffPassword() {
		return staffPassword;
	}

}
