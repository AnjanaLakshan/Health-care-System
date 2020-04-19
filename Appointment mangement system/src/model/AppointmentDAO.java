package model;

import DTO.Appointment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

	private Connection connect()  {
		
		Connection con = null; 
	 
	  try   
	  {     
		  Class.forName("com.mysql.jdbc.Driver");              
		  con = DriverManager.getConnection("jdbc:mysql://localhost:3306/appointment", "root", "");
		  
	  }   
	  catch (Exception e)   
	  {e.printStackTrace();} 
	 
	  return con;  
	  } 
	
	public boolean insertAppointmentDetails(String date,String startTime, String endTime, int patient_id,int doctor_id, int hospital_id, int department_id)
	{   
		String output = "";
	 try   
	  {    
		  Connection con = connect(); 
	 
	   if (con == null)   
	   {
		   return false;
	   }
	 
	      String query = " insert into appointment_details (app_date, start_time, end_time, patient_id,doctor_id, hospital_id, department_id) "
	    		  + " values ( ?, ?, ?, ?, ?, ?, ?)";

	      PreparedStatement preparedStmt = con.prepareStatement(query);


	      preparedStmt.setString(1, date);
	      preparedStmt.setString(2, startTime);
		  preparedStmt.setString(3, endTime);
	      preparedStmt.setInt(4, patient_id);
	      preparedStmt.setInt(5, doctor_id);
		  preparedStmt.setInt(6, hospital_id);
		  preparedStmt.setInt(7, department_id);

	      preparedStmt.execute();
	      con.close();

	      output = "Inserted successfully";
		  return true;

	  }catch (Exception e)

	 	{
		   output = "Error while inserting the Details";
		   System.err.println(e.getMessage());
		   return false;
		}

	}
	
	
public List<Appointment> filterAppointments(Appointment appointment)
	{   
		String output = ""; 

		try   
		{   
			Connection con = connect(); 
		
			if (con == null)    
			{
				return null;
			} 

			List<Appointment> appointments = new ArrayList<>();
	     	boolean filterRequested = false;
	     	String filterStr = "";
	     	if(appointment.getDate() != null) {
				if(!appointment.getDate().equals("")) {
					filterRequested =true;
					filterStr = filterStr + "app_date=" + "'" +appointment.getDate() + "'";
				}
			}
			if(appointment.getStartTime() != null) {
				if(!appointment.getStartTime().equals("")) {
					if(filterRequested){
						filterStr = filterStr + " AND start_time=" + appointment.getStartTime();
					}else{
						filterStr = filterStr + "start_time=" + appointment.getStartTime();
						filterRequested = true;
					}

				}
			}
			if(appointment.getEndTime() != null) {
				if(!appointment.getEndTime().equals("")) {
					if(filterRequested){
						filterStr = filterStr + " AND end_time=" + appointment.getEndTime();
					}else{
						filterStr = filterStr + "end_time=" + appointment.getEndTime();
						filterRequested = true;
					}

				}
			}
			if(appointment.getPatientId() != 0) {
				if(filterRequested){
					filterStr = filterStr + " AND patient_id=" + appointment.getPatientId();
				}else{
					filterStr = filterStr + "patient_id=" + appointment.getPatientId();
					filterRequested = true;
				}
			}
			if(appointment.getDoctorId() != 0) {
				if(filterRequested){
					filterStr = filterStr + " AND doctor_id=" + appointment.getDoctorId();
				}else{
					filterStr = filterStr + "doctor_id=" + appointment.getDoctorId();
					filterRequested = true;
				}
			}
			if(appointment.getHospitalId() != 0) {
				if(filterRequested){
					filterStr = filterStr + " AND hospital_id=" + appointment.getHospitalId();
				}else{
					filterStr = filterStr + "hospital_id=" + appointment.getHospitalId();
					filterRequested = true;
				}
			}
			if(appointment.getDepartmentId() != 0) {
				if(filterRequested){
					filterStr = filterStr + " AND department_id=" + appointment.getDepartmentId();
				}else{
					filterStr = filterStr + "department_id=" + appointment.getDepartmentId();
					filterRequested = true;
				}
			}
			String query = "SELECT * FROM appointment_details";
			if(filterRequested){
				query = query + " WHERE " + filterStr;
			}
			Statement stmt = con.createStatement();
	     	ResultSet rs = stmt.executeQuery(query);


	 
	     while (rs.next())    
	     {     
	    	 int id = rs.getInt("id");
			 java.sql.Date dbSqlDate = rs.getDate("app_date");
	    	 String startTime = rs.getString("start_time");
	    	 String endTime = rs.getString("end_time");
	    	 int patientId = rs.getInt("patient_id");
			 int doctorId = rs.getInt("doctor_id");
			 int hospitalId = rs.getInt("hospital_id");
			 int departmentId = rs.getInt("department_id");

			 Appointment appointmentObj = new Appointment(id, dbSqlDate.toString(), startTime, endTime, patientId, doctorId, hospitalId, departmentId);
			 appointments.add(appointmentObj);
	         } 
	
	     con.close(); 
	 	return appointments;

	     }

		catch (Exception e)  
		{    
		return null;
		}



	  } 
	
	public boolean updateAppointmentDetails(String ID, Appointment appointment)
	{   
		String output = ""; 
	 
		try   
		{    
				Connection con = connect(); 
	 
				if (con == null)   
				{
					return false;
				} 
	 
	   String query = "UPDATE appointment_details SET app_date=?,start_time=?,end_time=?,patient_id=?,doctor_id=?,hospital_id=?,department_id=? WHERE id=?";
	 
	   PreparedStatement preparedStmt = con.prepareStatement(query);

	   preparedStmt.setString(1, appointment.getDate());
	   preparedStmt.setString(2, appointment.getStartTime());
	   preparedStmt.setString(3, appointment.getEndTime());
	   preparedStmt.setInt(4, appointment.getPatientId());
	   preparedStmt.setInt(5, appointment.getDoctorId());
	   preparedStmt.setInt(6, appointment.getHospitalId());
	   preparedStmt.setInt(7, appointment.getDepartmentId());
	   preparedStmt.setInt(8, Integer.parseInt(ID));

	 
	   preparedStmt.execute();   
	   con.close(); 
	 
	   output = "Updated successfully";
	   return true;
	}   
		catch (Exception e)   
	{    
			output = "Error while updating the Details.";    
			System.err.println(e.getMessage());   
			return false;
	}
	  
	}
	
	public boolean deleteAppointment(String id)
	{   
		String output = ""; 
	 
	  try   
	  {    
		  Connection con = connect(); 
	 
	   if (con == null)    
	   {
		   return false;
	   }
	 
	   String query = "delete from appointment_details where id=?";
	 
	   PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   preparedStmt.setInt(1, Integer.parseInt(id));
	 
	   preparedStmt.execute();    
	   con.close(); 
	 
	   output = "Deleted successfully"; 
	   return true;
	  }   
	  catch (Exception e)   
	  {    
		  output = "Error while deleting the Details.";    
		  System.err.println(e.getMessage());
		  return false;
	  }
	}


	public Appointment getAppointment(String id) //get details by id function
	{
		try
		{
			Connection con = connect();

			if (con == null)
			{
				return null;
			}

			Appointment appointment = null;
			String query = "select * from appointment_details where id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, Integer.parseInt(id));
			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next())
			{
				java.sql.Date dbSqlDate = rs.getDate("app_date");
				String startTime = rs.getString("start_time");
				String endTime = rs.getString("end_time");
				int patientId = rs.getInt("patient_id");
				int doctorId = rs.getInt("doctor_id");
				int hospitalId = rs.getInt("hospital_id");
				int departmentId = rs.getInt("department_id");

				appointment = new Appointment(Integer.parseInt(id), dbSqlDate.toString(), startTime, endTime, patientId, doctorId, hospitalId, departmentId);
			}
			con.close();
			return appointment;
		}

		catch (Exception e)
		{
			return null;
		}

	}

}
