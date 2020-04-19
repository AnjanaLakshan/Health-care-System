package DTO;


public class Appointment {

	public Appointment(int id, String date, String startTime, String endTime, int patientId, int doctorId, int hospitalId, int departmentId) {
		this.id = id;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.hospitalId = hospitalId;
		this.departmentId = departmentId;
	}

	public Appointment(String date, String startTime, String endTime, int patientId, int doctorId, int hospitalId, int departmentId) {
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.hospitalId = hospitalId;
		this.departmentId = departmentId;
	}

	private int id;
	private String date;
	private String startTime;
	private String endTime;
	private int patientId;
	private int doctorId;
	private int hospitalId;
	private int departmentId;

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
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

	public Appointment() {
	}

}
