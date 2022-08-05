package com.weather.weatherapp;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

/**
 * @author Simon
 */
public class API {

    // Takes in a String as a parameter and returns a String of the completed url
    public static String buildUrl(String location) {
        String locationString = location.replaceAll(" ", "%20");
        return "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/" + locationString + "?unitGroup=us&include=days%2Chours%2Ccurrent&key=SFSB8XW6EANA8HG8E3RA9F46V&contentType=json";
    }

    public static JSONObject getJsonWeather(String location) throws UnirestException {
        HttpResponse<JsonNode> request = Unirest.get(buildUrl(location))
                .asJson();
        if (request.getStatus() == 200) {
            System.out.println("Connection Successful");
        }
        return request.getBody().getObject();
    }
}
