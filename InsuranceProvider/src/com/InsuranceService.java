package com;

import model.Provider;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class InsuranceService {

	Provider provideObj = new Provider();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return provideObj.readItems();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("nic") String nic, @FormParam("name") String name,
			@FormParam("income") String income, @FormParam("level") String level) {
		String output = provideObj.insertData(nic, name, income, level);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateData(String itemData) {

		// Convert the input string to a JSON object
		JsonObject provideObject = new JsonParser().parse(itemData).getAsJsonObject();

		// Read the values from the JSON object
		String clientID = provideObject.get("pID").getAsString();
		String itemCode = provideObject.get("nic").getAsString();
		String itemName = provideObject.get("name").getAsString();
		String itemPrice = provideObject.get("income").getAsString();

		String output = provideObj.updateData(pID, nic, name, income);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteData(String itemData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

		// Read the value from the element <itemID>
		String clientID = doc.select("pID").text();
		String output = provideObj.deleteData(pID);
		return output;
	}

}