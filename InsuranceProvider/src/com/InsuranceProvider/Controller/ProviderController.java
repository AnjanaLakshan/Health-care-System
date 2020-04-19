package com.InsuranceProvider.Controller;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.InsuranceProvider.Model.Provider;
import com.InsuranceProvider.Service.ProviderService;


@Path("provider")
public class ProviderController {
	
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String getGreeting(){
		String greeting  = "Hello ";
		return greeting;
	}
	
	@GET
	@Path("providers")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Provider> getAllProviders() throws Exception {
		return ProviderService.getInstance().getAll();
	}

//	@GET
//	@Path("appointment/{appoint_id}")
//	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//	public AppointModel getappointment(@PathParam("doc_id") int doc_id) throws Exception {
//		return AppointController.getInstance().search(doc_id);
//	}
//
//	@POST
//	@Path("appointment")
//	public String saveappointment(AppointModel obj) throws Exception {
//		
//		
//		if (DoctorController.getInstance().chekDoctorIdAvailability(obj.getDoc_id())) {
//			return "doctor id not valid";
//		} else if (PatientController.getInstance().chekPatientIdAvailability(obj.getPatientID())) {
//			return "patient id not valid";
//		} else {
//			AppointController.getInstance().save(obj);
//			return "appointment Saved";
//		}
//
//	}
//
//	@PUT
//	@Path("appointment")
//	public String updateappointment(AppointModel obj) throws Exception {
//		if (DoctorController.getInstance().chekDoctorIdAvailability(obj.getDoc_id())) {
//			return "doctor id not valid";
//		} else if (PatientController.getInstance().chekPatientIdAvailability(obj.getPatientID())) {
//			return "patient id not valid";
//		} else {
//			AppointController.getInstance().update(obj);
//			return "appointment Updated";
//		}
//	}
//
//	@DELETE
//	@Path("appointment/{appoint_id}")
//	public String deleteappointment(@PathParam("appoint_id") int doc_id) throws Exception {
//		AppointModel obj = new AppointModel();
//		obj.setDoc_id(doc_id);
//		AppointController.getInstance().delete(obj);
//		return "appointment Deleted";
//	}
}