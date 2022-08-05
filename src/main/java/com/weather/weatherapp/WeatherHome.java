package com.weather.weatherapp;

import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
public class WeatherHome implements Initializable {
    @FXML
    private ImageView background_image;

    @FXML
    private ImageView curr_weather_icon;

    @FXML
    private TextField search_text_field;

    @FXML
    private Button search_button;

    @FXML
    private Text day_text;

    @FXML
    private Text date_text;

    @FXML
    private Text time_text;

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
    private Text wind_speed;

    @FXML
    private Text wind_direction;

    @FXML
    private Text precipitation_type;

    @FXML
    private Text precipitation_probability;

    @FXML
    private Text humidity_percentage;

    @FXML
    private Text pressure;

    @FXML
    private Text uv_index;

    @FXML
    private Text visibility;

    @FXML
    private Text sunrise;

    @FXML
    private Text sunset;

    @FXML
    private Text curr_day_text;

    @FXML
    private Text curr_date_text;

    @FXML
    private Text day_text1;
    @FXML
    private Text date_text1;
    @FXML
    private ImageView weather_icon1;
    @FXML
    private Text max_temp1;
    @FXML
    private Text min_temp1;
    @FXML
    private Text sunrise_time1;
    @FXML
    private Text sunset_time1;
    @FXML
    private Text day_text2;
    @FXML
    private Text date_text2;
    @FXML
    private ImageView weather_icon2;
    @FXML
    private Text max_temp2;
    @FXML
    private Text min_temp2;
    @FXML
    private Text sunrise_time2;
    @FXML
    private Text sunset_time2;
    @FXML
    private Text day_text3;
    @FXML
    private Text date_text3;
    @FXML
    private ImageView weather_icon3;
    @FXML
    private Text max_temp3;
    @FXML
    private Text min_temp3;
    @FXML
    private Text sunrise_time3;
    @FXML
    private Text sunset_time3;
    @FXML
    private Text day_text4;
    @FXML
    private Text date_text4;
    @FXML
    private ImageView weather_icon4;
    @FXML
    private Text max_temp4;
    @FXML
    private Text min_temp4;
    @FXML
    private Text sunrise_time4;
    @FXML
    private Text sunset_time4;
    @FXML
    private Text day_text5;
    @FXML
    private Text date_text5;
    @FXML
    private ImageView weather_icon5;
    @FXML
    private Text max_temp5;
    @FXML
    private Text min_temp5;
    @FXML
    private Text sunrise_time5;
    @FXML
    private Text sunset_time5;
    @FXML
    private Text day_text6;
    @FXML
    private Text date_text6;
    @FXML
    private ImageView weather_icon6;
    @FXML
    private Text max_temp6;
    @FXML
    private Text min_temp6;
    @FXML
    private Text sunrise_time6;
    @FXML
    private Text sunset_time6;
    @FXML
    private Text day_text7;
    @FXML
    private Text date_text7;
    @FXML
    private ImageView weather_icon7;
    @FXML
    private Text max_temp7;
    @FXML
    private Text min_temp7;
    @FXML
    private Text sunrise_time7;
    @FXML
    private Text sunset_time7;
    @FXML
    private Text day_text8;
    @FXML
    private Text date_text8;
    @FXML
    private ImageView weather_icon8;
    @FXML
    private Text max_temp8;
    @FXML
    private Text min_temp8;
    @FXML
    private Text sunrise_time8;
    @FXML
    private Text sunset_time8;
    @FXML
    private Text day_text9;
    @FXML
    private Text date_text9;
    @FXML
    private ImageView weather_icon9;
    @FXML
    private Text max_temp9;
    @FXML
    private Text min_temp9;
    @FXML
    private Text sunrise_time9;
    @FXML
    private Text sunset_time9;
    @FXML
    private Text day_text10;
    @FXML
    private Text date_text10;
    @FXML
    private ImageView weather_icon10;
    @FXML
    private Text max_temp10;
    @FXML
    private Text min_temp10;
    @FXML
    private Text sunrise_time10;
    @FXML
    private Text sunset_time10;
    @FXML
    private Button scroll_forward;
    @FXML
    private Button scroll_backward;

    @FXML
    private ScrollPane daily_scroll;

    @FXML
    private Button hourly_button;

    JSONObject weatherJson;
    JSONObject currConditionsJson;
    JSONArray daysJson;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Button will use the info from the textfield to load a specific location
        search_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (search_text_field.getText().isBlank()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please enter a location into the text box.");
                    alert.show();
                } else {
                    loadLocation(search_text_field.getText());
                    search_text_field.clear();
                }
            }
        });

        // scrolls the scrollpane forwards
        scroll_forward.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Helper.slowScrollToRight(daily_scroll);
            }
        });

        // scrolls the scrollpane backwards
        scroll_backward.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Helper.slowScrollToLeft(daily_scroll);
            }
        });

        // changes the scene to weatherHourly.fxml
        hourly_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SceneChanger.changeScene(actionEvent, "weatherHourly.fxml", "Hourly Weather", weatherJson.getString("address"));
            }
        });


    }

    public void loadLocation(String location) {
        // places daily information into lists;
        List<Text> days_text = new ArrayList<>(Arrays.asList(day_text1, day_text2, day_text3, day_text4, day_text5, day_text6, day_text7, day_text8, day_text9, day_text10));
        List<Text> dates_text = new ArrayList<>(Arrays.asList(date_text1, date_text2, date_text3, date_text4, date_text5, date_text6, date_text7, date_text8, date_text9, date_text10));
        List<ImageView> daily_icons = new ArrayList<>(Arrays.asList(weather_icon1, weather_icon2, weather_icon3, weather_icon4, weather_icon5, weather_icon6, weather_icon7, weather_icon8, weather_icon9, weather_icon10));
        List<Text> daily_max_temps = new ArrayList<>(Arrays.asList(max_temp1, max_temp2, max_temp3, max_temp4, max_temp5, max_temp6, max_temp7, max_temp8, max_temp9, max_temp10));
        List<Text> daily_min_temps = new ArrayList<>(Arrays.asList(min_temp1, min_temp2, min_temp3, min_temp4, min_temp5, min_temp6, min_temp7, min_temp8, min_temp9, min_temp10));
        List<Text> daily_sunrise = new ArrayList<>(Arrays.asList(sunrise_time1, sunrise_time2, sunrise_time3, sunrise_time4, sunrise_time5, sunrise_time6, sunrise_time7, sunrise_time8, sunrise_time9, sunrise_time10));
        List<Text> daily_sunset = new ArrayList<>(Arrays.asList(sunset_time1, sunset_time2, sunset_time3, sunset_time4, sunset_time5, sunset_time6, sunset_time7, sunset_time8, sunset_time9, sunset_time10));

        try {
            weatherJson = API.getJsonWeather(location);
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        // Stores JSON elements into variables
        currConditionsJson = weatherJson.getJSONObject("currentConditions");
        daysJson = weatherJson.getJSONArray("days");

        // Sets icon for current weather
        curr_weather_icon.setImage(Helper.getIcon(daysJson.getJSONObject(0).getString("icon")));

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

        // Sets information for future forecast
        for (int i = 0; i < 10; i++) {
            days_text.get(i).setText(Helper.getDay(i+1, weatherJson));
            dates_text.get(i).setText(Helper.getDate(i+1));
            daily_max_temps.get(i).setText((int) daysJson.getJSONObject(i + 1).getDouble("tempmax") + "\u00B0");
            daily_min_temps.get(i).setText((int) daysJson.getJSONObject(i + 1).getDouble("tempmin") + "\u00B0");
            daily_sunrise.get(i).setText(daysJson.getJSONObject(i+1).getString("sunrise").substring(0, 5));
            daily_sunset.get(i).setText(daysJson.getJSONObject(i+1).getString("sunset").substring(0, 5));
            daily_icons.get(i).setImage(Helper.getIcon(daysJson.getJSONObject(i+1).getString("icon")));
        }

        // Sets Wind information
        wind_speed.setText((int) currConditionsJson.getDouble("windspeed") + "mph");
        wind_direction.setText(Helper.getWindDirection(currConditionsJson.getDouble("winddir")));

        // Sets the Precipitation information;
        if (currConditionsJson.isNull("preciptype")) {
            precipitation_type.setText("None");
            precipitation_probability.setText("0%");
        } else {
            String precipitation = currConditionsJson.getJSONArray("preciptype").getString(0);
            precipitation = precipitation.substring(0,1).toUpperCase() + precipitation.substring(1);
            precipitation_type.setText(precipitation);
            precipitation_probability.setText(currConditionsJson.getDouble("precipprob") + "%");
        }

        // Sets humidity
        humidity_percentage.setText(currConditionsJson.getInt("humidity") + "%");

        // Sets pressure
        pressure.setText(Math.round(currConditionsJson.getDouble("pressure") * 0.02953 * 100.0) / 100.0 + " inHg");

        // Sets uv index
        uv_index.setText(Integer.toString(currConditionsJson.getInt("uvindex")));

        // Sets visibility
        visibility.setText(currConditionsJson.getDouble("visibility") + " Miles");

        // Sets Sunrise
        sunrise.setText(currConditionsJson.getString("sunrise").substring(0, 5));

        // Sets Sunset
        sunset.setText(currConditionsJson.getString("sunset").substring(0, 5));

        // Sets background image
        background_image.setImage(Helper.getBackground(daysJson.getJSONObject(0).getString("icon")));

        // Continuously update time on the scene
        DateFormat timeFormatterInit = new SimpleDateFormat("HH:mm");
        Date dateTimeInit = new Date();
        timeFormatterInit.setTimeZone(TimeZone.getTimeZone(weatherJson.getString("timezone")));
        time_text.setText(timeFormatterInit.format(dateTimeInit));
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
            loadLocation(weatherJson.getString("address"));
        }));
        refreshScene.setCycleCount(Timeline.INDEFINITE);
        refreshScene.play();

    }




}
