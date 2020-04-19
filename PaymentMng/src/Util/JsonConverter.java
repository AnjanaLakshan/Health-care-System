package Util;

import DTO.Payment;
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

    public String convertToJson(List<Payment> payments) {

        JsonArray jarray = gson.toJsonTree(payments).getAsJsonArray();


        return jarray.toString();
    }

    public String convertToJson(Payment payment) {

        JsonObject jsonObject = gson.toJsonTree(payment).getAsJsonObject();

        return jsonObject.toString();
    }

    public Payment convertToObject(String jsonString){
       return gson.fromJson(jsonString, Payment.class);
    }
    public String convertToJson(Response response) {

        JsonObject jsonObject = gson.toJsonTree(response).getAsJsonObject();

        return jsonObject.toString();
    }
}