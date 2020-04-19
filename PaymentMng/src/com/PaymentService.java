package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import DTO.Payment;
import DTO.Response;
import model.PaymentDAO;



import java.util.List;

@Path("/Payment")
public class PaymentService {

	PaymentDAO paymentDAO = new PaymentDAO();
	
	@POST //add Payment to the System
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createPayment(@FormParam("date")String date,
								  @FormParam("start_time")String startTime,
								  @FormParam("amount")String amount,
								  @FormParam("patient_id")int patient_id,
								  @FormParam("doctor_id")int doctor_id,
								  @FormParam("department_id")int department_id,
								  @FormParam("hospital_id")int hospital_id,
								  @FormParam("insurance")boolean insurance)
	{
		List<Payment> payments = paymentDAO.filterPayment(new Payment(date, startTime, amount, patient_id, doctor_id, hospital_id, department_id,insurance));
		if(payments.size() > 0){
			return new Response(false, "Requested Payment already created");
		}
		boolean status = paymentDAO.insertPaymentDetails(date, startTime, amount, patient_id, doctor_id, hospital_id, department_id,insurance);
		if(status){
			List<Payment> paymentNew = paymentDAO.filterPayment(new Payment(date, startTime, amount, patient_id, doctor_id, hospital_id, department_id,insurance));
			return new Response(true, "Payment Created with Id: " + paymentNew.get(0).getId());
		}
		return new Response(status);
	}

	@GET// get Payment details by id
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public javax.ws.rs.core.Response getPayment(@PathParam("id") String id)
	{

		Payment payment = paymentDAO.getPayment(id);
		if(payment !=null){
			return javax.ws.rs.core.Response.status(200).type(MediaType.APPLICATION_JSON)
					.entity(payment).build();
		}else{
			return javax.ws.rs.core.Response.status(404).build();
		}

	}
	 
	 @GET//list the all Payment in database
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	public List<Payment> filterPaymentDetails(@QueryParam("date") String date,
											  @QueryParam("start_time") String startTime,
											  @QueryParam("amount") String amount,
											  @QueryParam("patient_id") int patientId,
											  @QueryParam("doctor_id") int doctorId,
											  @QueryParam("hospital_id") int hospitalId,
											  @QueryParam("department_id") int departmentId,
											  @QueryParam("insurance") boolean insurance)
	 {

		 return paymentDAO.filterPayment(new Payment(date,startTime, amount,patientId,doctorId,hospitalId,departmentId,insurance));

	 }
	 
	 
	 @PUT//update the new Payment
	 @Path("/{id}")
	 @Consumes(MediaType.APPLICATION_JSON) 
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response updatePayment(@PathParam("id") String id, Payment payment)
	 {

		 List<Payment> payments = paymentDAO.filterPayment(payment);

		 if(payments.size() > 0){
			 return new Response(false, "Requested payment already created");
		 }

		 return new Response(paymentDAO.updatePaymentDetails(id, payment), "Payment Updated Successfully");
		 
	 } 
	 
	 
	 @DELETE//delete Payment from the database
	 @Path("/{id}")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response deletePaymentDetails(@PathParam("id") String id) {

		 return new Response(paymentDAO.deletePayment(id));
	 }
}
