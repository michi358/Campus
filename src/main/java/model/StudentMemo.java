package model;

public class StudentMemo {
	private String memoId;
	private String memo;
	private String studentNumber;
	private String studentName;
	private String staffId;
	private String staffName;
	
	//①すべてセットするコンストラクタ
	public StudentMemo(
			String studentNumber, String studentName,
			String staffId, String staffName,
			String memoId, String memo) {
		this.studentNumber = studentNumber;
		this.studentName = studentName;
		this.staffId = staffId;
		this.staffName = staffName;
		this.memoId = memoId;
		this.memo = memo;
	}
	
	//②学生情報は登録済み、メモのみ新規の場合
	public StudentMemo(
			String studentNumber, 
			String studentName,
			String staffId, 
			String staffName) {
		this.studentNumber = studentNumber;
		this.studentName = studentName;
		this.staffId = staffId;
		this.staffName = staffName;
		this.memoId = "";
		this.memo = "";
	}
	//③学生情報、メモともに新規の場合
	public StudentMemo(String staffId, String staffName) {
		this.studentNumber = "";
		this.studentName = "";
		this.staffId = staffId;
		this.staffName = staffName;
		this.memoId = "";
		this.memo = "";
	}
	
	public String getMemoId() {
		return memoId;
	}
	public String getMemo() {
		return memo;
	}
	public String getStudentNumber() {
		return studentNumber;
	}
	public String getStudentName() {
		return studentName;
	}
	public String getStaffId() {
		return staffId;
	}
	public String getStaffName() {
		return staffName;
	}
	
	public void setMemoId(String memoId) {
		this.memoId = memoId;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	public void setStaffId(String staffId)
	{
		this.staffId = staffId;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
}
