package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import DTO.Appointment;
import DTO.Response;
import model.AppointmentDAO;



import java.util.List;

@Path("/Appointment")
public class AppointmentService {

	AppointmentDAO appointmentDAO = new AppointmentDAO();
	
	@POST //add Appointment to the System
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createAppointment(@FormParam("date")String date,
									  @FormParam("start_time")String startTime,
										 @FormParam("end_time")String endTime,
										 @FormParam("patient_id")int patient_id,
										 @FormParam("doctor_id")int doctor_id,
									     @FormParam("department_id")int department_id,
									     @FormParam("hospital_id")int hospital_id)
	{
		List<Appointment> appointments = appointmentDAO.filterAppointments(new Appointment(date, startTime, endTime, patient_id, doctor_id, hospital_id, department_id));
		if(appointments.size() > 0){
			return new Response(false, "Requested appointment already created");
		}
		boolean status = appointmentDAO.insertAppointmentDetails(date, startTime, endTime, patient_id, doctor_id, hospital_id, department_id);
		if(status){
			List<Appointment> appointmentsNew = appointmentDAO.filterAppointments(new Appointment(date, startTime, endTime, patient_id, doctor_id, hospital_id, department_id));
			return new Response(true, "Appointment Created with Id: " + appointmentsNew.get(0).getId());
		}
		return new Response(status);
	}

	@GET// get Appointment details by id
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getAppointment(@PathParam("id") String id)
	{

		Appointment appointment = appointmentDAO.getAppointment(id);
		if(appointment !=null){
			return javax.ws.rs.core.Response.status(200).type(MediaType.APPLICATION_JSON)
					.entity(appointment).build();
		}else{
			return javax.ws.rs.core.Response.status(404).build();
		}

	}
	 
	 @GET//list the all Appointment in database
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	public List<Appointment> filterAppointmentsDetails(@QueryParam("date") String date,
											@QueryParam("start_time") String startTime,
											@QueryParam("start_time") String endTime,
											@QueryParam("patient_id") int patientId,
											@QueryParam("doctor_id") int doctorId,
											@QueryParam("hospital_id") int hospitalId,
											@QueryParam("department_id") int departmentId)
	 {

		 return appointmentDAO.filterAppointments(new Appointment(date,startTime,endTime,patientId,doctorId,hospitalId,departmentId));

	 }
	 
	 
	 @PUT//update the new Appointment
	 @Path("/{id}")
	 @Consumes(MediaType.APPLICATION_JSON) 
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response updateAppointment(@PathParam("id") String id, Appointment appointment)
	 {

		 List<Appointment> appointments = appointmentDAO.filterAppointments(appointment);

		 if(appointments.size() > 0){
			 return new Response(false, "Requested appointment already created");
		 }

		 return new Response(appointmentDAO.updateAppointmentDetails(id, appointment), "Appointment Updated Succesfully");
		 
	 } 
	 
	 
	 @DELETE//delete Appointment from the database
	 @Path("/{id}")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response deleteAppointmentDetails(@PathParam("id") String id) {

		 return new Response(appointmentDAO.deleteAppointment(id));
	 }
}
