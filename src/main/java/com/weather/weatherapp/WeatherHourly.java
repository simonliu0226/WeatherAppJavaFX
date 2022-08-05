package com.weather.weatherapp;

import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Simon
 */
public class WeatherHourly implements Initializable {
    @FXML
    private Text location_text;

    @FXML
    private Text curr_temp;

    @FXML
    private Text feels_like_text;

    @FXML
    private Text max_text;

    @FXML
    private Text min_text;

    @FXML
    private Text curr_day_text;

    @FXML
    private Text date_text;

    @FXML
    private Text time_text;

    @FXML
    private Text day_text;

    @FXML
    private Text curr_date_text;

    @FXML
    private Button daily_button;

    @FXML
    private ImageView curr_weather_icon;

    @FXML
    private Text hour_text1;
    @FXML
    private Text hour_temp1;
    @FXML
    private ImageView weather_icon1;
    @FXML
    private Text hour_text2;
    @FXML
    private Text hour_temp2;
    @FXML
    private ImageView weather_icon2;
    @FXML
    private Text hour_text3;
    @FXML
    private Text hour_temp3;
    @FXML
    private ImageView weather_icon3;
    @FXML
    private Text hour_text4;
    @FXML
    private Text hour_temp4;
    @FXML
    private ImageView weather_icon4;
    @FXML
    private Text hour_text5;
    @FXML
    private Text hour_temp5;
    @FXML
    private ImageView weather_icon5;
    @FXML
    private Text hour_text6;
    @FXML
    private Text hour_temp6;
    @FXML
    private ImageView weather_icon6;
    @FXML
    private Text hour_text7;
    @FXML
    private Text hour_temp7;
    @FXML
    private ImageView weather_icon7;
    @FXML
    private Text hour_text8;
    @FXML
    private Text hour_temp8;
    @FXML
    private ImageView weather_icon8;
    @FXML
    private Text hour_text9;
    @FXML
    private Text hour_temp9;
    @FXML
    private ImageView weather_icon9;
    @FXML
    private Text hour_text10;
    @FXML
    private Text hour_temp10;
    @FXML
    private ImageView weather_icon10;

    @FXML
    private Button scroll_forward;

    @FXML
    private Button scroll_backward;

    @FXML
    private ImageView background_image;

    @FXML
    private ScrollPane hourly_scroll;

    JSONObject weatherJson;
    JSONArray daysJson;
    JSONObject currConditionsJson;
    JSONArray hoursJson;
    JSONArray secondHoursJson;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // button will change the scene to weatherHome.fxml
        daily_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SceneChanger.changeScene(actionEvent, "weatherHome.fxml", "Current Weather Forecast", weatherJson.getString("address"));
            }
        });

        // Button will scroll the scrollpane forward, revealing more hours
        scroll_forward.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Helper.slowScrollToRight(hourly_scroll);
            }
        });

        // Button will scroll the scrollpane backward, back to its original position
        scroll_backward.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Helper.slowScrollToLeft(hourly_scroll);
            }
        });

    }

    public void setInfo(String location) {
        // places hourly elements into lists.
        List<Text> hours_text = new ArrayList<>(Arrays.asList(hour_text1, hour_text2, hour_text3, hour_text4, hour_text5, hour_text6, hour_text7, hour_text8, hour_text9, hour_text10));
        List<ImageView> weather_icons = new ArrayList<>(Arrays.asList(weather_icon1, weather_icon2, weather_icon3, weather_icon4, weather_icon5, weather_icon6, weather_icon7, weather_icon8, weather_icon9, weather_icon10));
        List<Text> hour_temps = new ArrayList<>(Arrays.asList(hour_temp1, hour_temp2, hour_temp3, hour_temp4, hour_temp5, hour_temp6, hour_temp7, hour_temp8, hour_temp9, hour_temp10));

        // gets API JSON file
        try {
            weatherJson = API.getJsonWeather(location);
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        // Stores JSONObjects and JSONArrays from Json data into objects
        daysJson = weatherJson.getJSONArray("days");
        currConditionsJson = weatherJson.getJSONObject("currentConditions");
        hoursJson = daysJson.getJSONObject(0).getJSONArray("hours");
        secondHoursJson = daysJson.getJSONObject(1).getJSONArray("hours");

        // sets the date_text and curr_date_text on scene
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date locationDate = new Date();
        dateFormat.setTimeZone(TimeZone.getTimeZone(weatherJson.getString("timezone")));
        date_text.setText(dateFormat.format(locationDate));
        curr_date_text.setText(dateFormat.format(locationDate));

        // sets the day_text and curr_day_text on scene
        day_text.setText(Helper.getDay(0, weatherJson));
        curr_day_text.setText(Helper.getDay(0, weatherJson));

        // Sets the current temperature text
        int currentTemp = (int) currConditionsJson.getDouble("temp");
        curr_temp.setText(currentTemp + "\u00B0");

        // Sets the feels like temperature;
        int feelsLike = (int) currConditionsJson.getDouble("feelslike");
        feels_like_text.setText(feelsLike + "\u00B0");

        // Sets the max temperature
        int maxTemp = (int) daysJson.getJSONObject(0).getDouble("tempmax");
        max_text.setText(maxTemp + "\u00B0");

        // Sets the min temperature
        int minTemp = (int) daysJson.getJSONObject(0).getDouble("tempmin");
        min_text.setText(minTemp + "\u00B0");

        // Sets location text
        location_text.setText(weatherJson.getString("resolvedAddress"));

        // Sets Icon
        curr_weather_icon.setImage(Helper.getIcon(daysJson.getJSONObject(0).getString("icon")));

        // Sets background image
        background_image.setImage(Helper.getBackground(daysJson.getJSONObject(0).getString("icon")));

        // Sets the Hourly icons
        // Gets time for each hour
        DateFormat hourFormatter = new SimpleDateFormat("HH");
        Date hourDate = new Date();
        hourFormatter.setTimeZone(TimeZone.getTimeZone(weatherJson.getString("timezone")));
        String currentHour = hourFormatter.format(hourDate);
        int hourInt = Integer.parseInt(currentHour);
        int hourCount = 0;
        int secondDayHour = 0;
        // Sets the information for each hour element on current day
        while (hourCount < 10) {
            JSONObject hourJson = hoursJson.getJSONObject(hourCount+1+hourInt);
            weather_icons.get(hourCount).setImage(Helper.getIcon(hourJson.getString("icon")));
            hours_text.get(hourCount).setText(hourJson.getString("datetime").substring(0,5));
            hour_temps.get(hourCount).setText(hourJson.getInt("temp") + "\u00B0");
            hourCount++;
            if (hourCount+1+hourInt == 24) {
                break;
            }
        }
        // Sets the information for each hour element on next day
        while(hourCount < 10) {
            JSONObject hourJson = secondHoursJson.getJSONObject(secondDayHour);
            weather_icons.get(hourCount).setImage(Helper.getIcon(hourJson.getString("icon")));
            hours_text.get(hourCount).setText(hourJson.getString("datetime").substring(0, 5));
            hour_temps.get(hourCount).setText(hourJson.getInt("temp") + "\u00B0");
            hourCount++;
            secondDayHour++;
        }

        // Continuously update time on the scene
        DateFormat timeFormatterInit = new SimpleDateFormat("HH:mm");
        timeFormatterInit.setTimeZone(TimeZone.getTimeZone(weatherJson.getString("timezone")));
        time_text.setText(timeFormatterInit.format(locationDate));
        Timeline clockTimeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            DateFormat timeFormatter = new SimpleDateFormat("HH:mm");
            Date dateTime = new Date();
            timeFormatter.setTimeZone(TimeZone.getTimeZone(weatherJson.getString("timezone")));
            time_text.setText(timeFormatter.format(dateTime));
        }));
        clockTimeline.setCycleCount(Timeline.INDEFINITE);
        clockTimeline.play();

        // Refreshes the scene every 20 minutes
        Timeline refreshScene = new Timeline(new KeyFrame(Duration.minutes(20), e -> {
            setInfo(weatherJson.getString("address"));
        }));
        refreshScene.setCycleCount(Timeline.INDEFINITE);
        refreshScene.play();
    }









}
