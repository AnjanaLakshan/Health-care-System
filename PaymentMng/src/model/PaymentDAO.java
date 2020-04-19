package model;

import DTO.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

	private Connection connect()  {
		
		Connection con = null; 
	 
	  try   
	  {     
		  Class.forName("com.mysql.jdbc.Driver");              
		  con = DriverManager.getConnection("jdbc:mysql://localhost:3306/payment", "root", "");
		  
	  }   
	  catch (Exception e)   
	  {e.printStackTrace();} 
	 
	  return con;  
	  } 
	
	public boolean insertPaymentDetails(String date, String startTime, String amount, int patient_id, int doctor_id, int hospital_id, int department_id, boolean insurance)
	{   
		String output = "";
	 try   
	  {    
		  Connection con = connect(); 
	 
	   if (con == null)   
	   {
		   return false;
	   }
	 
	      String query = " insert into payment_details (app_date, start_time, amount, patient_id,doctor_id, hospital_id, department_id,insurance) "
	    		  + " values ( ?, ?, ?, ?, ?, ?, ?, ?)";

	      PreparedStatement preparedStmt = con.prepareStatement(query);


	      preparedStmt.setString(1, date);
	      preparedStmt.setString(2, startTime);
		  preparedStmt.setString(3, amount);
	      preparedStmt.setInt(4, patient_id);
	      preparedStmt.setInt(5, doctor_id);
		  preparedStmt.setInt(6, hospital_id);
		  preparedStmt.setInt(7, department_id);
		  preparedStmt.setBoolean(8,insurance);

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
	
	
public List<Payment> filterPayment(Payment payment)
	{   
		String output = ""; 

		try   
		{   
			Connection con = connect(); 
		
			if (con == null)    
			{
				return null;
			} 

			List<Payment> payments = new ArrayList<>();
	     	boolean filterRequested = false;
	     	String filterStr = "";
	     	if(payment.getDate() != null) {
				if(!payment.getDate().equals("")) {
					filterRequested =true;
					filterStr = filterStr + "app_date=" + "'" + payment.getDate() + "'";
				}
			}
			if(payment.getStartTime() != null) {
				if(!payment.getStartTime().equals("")) {
					if(filterRequested){
						filterStr = filterStr + " AND start_time=" + payment.getStartTime();
					}else{
						filterStr = filterStr + "start_time=" + payment.getStartTime();
						filterRequested = true;
					}

				}
			}
			if(payment.getAmount() != null) {
				if(!payment.getAmount().equals("")) {
					if(filterRequested){
						filterStr = filterStr + " AND amount=" + payment.getAmount();
					}else{
						filterStr = filterStr + "amount=" + payment.getAmount();
						filterRequested = true;
					}

				}
			}
			if(payment.getPatientId() != 0) {
				if(filterRequested){
					filterStr = filterStr + " AND patient_id=" + payment.getPatientId();
				}else{
					filterStr = filterStr + "patient_id=" + payment.getPatientId();
					filterRequested = true;
				}
			}
			if(payment.getDoctorId() != 0) {
				if(filterRequested){
					filterStr = filterStr + " AND doctor_id=" + payment.getDoctorId();
				}else{
					filterStr = filterStr + "doctor_id=" + payment.getDoctorId();
					filterRequested = true;
				}
			}
			if(payment.getHospitalId() != 0) {
				if(filterRequested){
					filterStr = filterStr + " AND hospital_id=" + payment.getHospitalId();
				}else{
					filterStr = filterStr + "hospital_id=" + payment.getHospitalId();
					filterRequested = true;
				}
			}
			if(payment.getDepartmentId() != 0) {
				if(filterRequested){
					filterStr = filterStr + " AND department_id=" + payment.getDepartmentId();
				}else{
					filterStr = filterStr + "department_id=" + payment.getDepartmentId();
					filterRequested = true;
				}
			}
			if(payment.getInsurance() != false) {
				if(filterRequested){
					filterStr = filterStr + " AND insurance=" + payment.getInsurance();
				}else{
					filterStr = filterStr + "department_id=" + payment.getInsurance();
					filterRequested = true;
				}
			}
			String query = "SELECT * FROM payment_details";
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
	    	 String amount = rs.getString("amount");
	    	 int patientId = rs.getInt("patient_id");
			 int doctorId = rs.getInt("doctor_id");
			 int hospitalId = rs.getInt("hospital_id");
			 int departmentId = rs.getInt("department_id");
			 boolean insurance = rs.getBoolean("insurance");

			 Payment paymentObj = new Payment(id, dbSqlDate.toString(), startTime, amount, patientId, doctorId, hospitalId, departmentId, insurance);
			 payments.add(paymentObj);
	         } 
	
	     con.close(); 
	 	return payments;

	     }

		catch (Exception e)  
		{    
		return null;
		}



	  } 
	
	public boolean updatePaymentDetails(String ID, Payment payment)
	{   
		String output = ""; 
	 
		try   
		{    
				Connection con = connect(); 
	 
				if (con == null)   
				{
					return false;
				} 
	 
	   String query = "UPDATE payment_details SET app_date=?,start_time=?,amount=?,patient_id=?,doctor_id=?,hospital_id=?,department_id=?,insurance=? WHERE id=?";
	 
	   PreparedStatement preparedStmt = con.prepareStatement(query);

	   preparedStmt.setString(1, payment.getDate());
	   preparedStmt.setString(2, payment.getStartTime());
	   preparedStmt.setString(3, payment.getAmount());
	   preparedStmt.setInt(4, payment.getPatientId());
	   preparedStmt.setInt(5, payment.getDoctorId());
	   preparedStmt.setInt(6, payment.getHospitalId());
	   preparedStmt.setInt(7, payment.getDepartmentId());
	   preparedStmt.setBoolean(8, payment.getInsurance());
	   preparedStmt.setInt(9, Integer.parseInt(ID));

	 
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
	
	public boolean deletePayment(String id)
	{   
		String output = ""; 
	 
	  try   
	  {    
		  Connection con = connect(); 
	 
	   if (con == null)    
	   {
		   return false;
	   }
	 
	   String query = "delete from payment_details where id=?";
	 
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


	public Payment getPayment(String id) //get details by id function
	{
		try
		{
			Connection con = connect();

			if (con == null)
			{
				return null;
			}

			Payment payment = null;
			String query = "select * from payment_details where id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, Integer.parseInt(id));
			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next())
			{
				java.sql.Date dbSqlDate = rs.getDate("app_date");
				String startTime = rs.getString("start_time");
				String amount = rs.getString("amount");
				int patientId = rs.getInt("patient_id");
				int doctorId = rs.getInt("doctor_id");
				int hospitalId = rs.getInt("hospital_id");
				int departmentId = rs.getInt("department_id");
				boolean insurance = rs.getBoolean("insurance");

				payment = new Payment(Integer.parseInt(id), dbSqlDate.toString(), startTime, amount, patientId, doctorId, hospitalId, departmentId,insurance);
			}
			con.close();
			return payment;
		}

		catch (Exception e)
		{
			return null;
		}

	}

}
