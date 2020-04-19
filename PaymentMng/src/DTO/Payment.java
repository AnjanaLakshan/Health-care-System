package DTO;


public class Payment {

	public Payment(int id, String date, String startTime, String amount, int patientId, int doctorId, int hospitalId, int departmentId, boolean insurance) {
		this.id = id;
		this.date = date;
		this.startTime = startTime;
		this.amount = amount;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.hospitalId = hospitalId;
		this.departmentId = departmentId;
		this.insurance = insurance;
	}

	public Payment(String date, String startTime, String amount, int patientId, int doctorId, int hospitalId, int departmentId, boolean insurance) {
		this.date = date;
		this.startTime = startTime;
		this.amount = amount;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.hospitalId = hospitalId;
		this.departmentId = departmentId;
		this.insurance = insurance;
	}

	private int id;
	private String date;
	private String startTime;
	private String amount;
	private int patientId;
	private int doctorId;
	private int hospitalId;
	private int departmentId;
	private boolean insurance;

	public Payment(int id, String startTime, String startTime1, String amount, int patientId, int doctorId, int hospitalId, int departmentId) {
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public int getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(int hospitalId) {
		this.hospitalId = hospitalId;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public boolean getInsurance(){return insurance;}

	public void setInsurance(boolean insurance){this.insurance = insurance;}

	public Payment() {
	}

}
