package Util;

import DTO.Appointment;
import DTO.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;


public class JsonConverter {

    private final Gson gson;

    public JsonConverter() {

        gson = new GsonBuilder().create();
    }

    public String convertToJson(List<Appointment> appointments) {

        JsonArray jarray = gson.toJsonTree(appointments).getAsJsonArray();


        return jarray.toString();
    }

    public String convertToJson(Appointment appointment) {

        JsonObject jsonObject = gson.toJsonTree(appointment).getAsJsonObject();

        return jsonObject.toString();
    }

    public Appointment convertToObject(String jsonString){
       return gson.fromJson(jsonString, Appointment.class);
    }
    public String convertToJson(Response response) {

        JsonObject jsonObject = gson.toJsonTree(response).getAsJsonObject();

        return jsonObject.toString();
    }
}