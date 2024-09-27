package model;

public class Student {
	private String studentNumber;
	private String studentName;
	
	public Student(String studentNumber, String studentName) {
		this.studentNumber = studentNumber;
		this.studentName = studentName;
	}
	
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	public String getStudentNumber() {
		return studentNumber;
	}
	
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentName() {
		return studentName;
	}

}
