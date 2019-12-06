package application;

public class Employee {
	private int employee_no;
	private String first_name;
	private String last_name;
	private String username;
	private String type;
	
	public Employee(int employee_no, String first_name, String last_name, String username, String type){
		this.employee_no = employee_no;
		this.first_name = first_name;
		this.last_name = last_name;
		this.username = username;
		this.type = type;
		
	}

	public Employee(String first_name, String last_name, String username, String type) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.username = username;
		this.type = type;
	}

	public int getEmployee_no() {
		return employee_no;
	}

	public void setEmployee_no(int employee_no) {
		this.employee_no = employee_no;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	

}
