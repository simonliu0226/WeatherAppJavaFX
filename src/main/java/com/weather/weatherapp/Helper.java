package com.weather.weatherapp;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.util.Duration;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Simon
 */
public class Helper {

    // Returns day of the week
    public static String getDay(int offset, JSONObject weatherJson) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(weatherJson.getString("timezone")));
        int dayInt = (calendar.get(Calendar.DAY_OF_WEEK) + offset) % 7;
        switch (dayInt) {
            case 1 -> {
                return "Sunday";
            }
            case 2 -> {
                return "Monday";
            }
            case 3 -> {
                return "Tuesday";
            }
            case 4 -> {
                return "Wednesday";
            }
            case 5 -> {
                return "Thursday";
            }
            case 6 -> {
                return "Friday";
            }
            default -> {
                return "Saturday";
            }
        }

    }

    // Returns an Image object
    public static Image getBackground(String icon) {
        return switch (icon) {
            case "snow" -> new Image(Objects.requireNonNull(Helper.class.getResourceAsStream("snowy.jpg")));
            case "rain" -> new Image(Objects.requireNonNull(Helper.class.getResourceAsStream("rainy.jpg")));
            case "fog", "wind" -> new Image(Objects.requireNonNull(Helper.class.getResourceAsStream("windy.jpg")));
            case "cloudy" -> new Image(Objects.requireNonNull(Helper.class.getResourceAsStream("cloudy.jpg")));
            case "partly-cloudy-day" -> new Image(Objects.requireNonNull(Helper.class.getResourceAsStream("clear.jpg")));
            case "partly-cloudy-night" -> new Image(Objects.requireNonNull(Helper.class.getResourceAsStream("cloudy_night.jpg")));
            case "clear-night" -> new Image(Objects.requireNonNull(Helper.class.getResourceAsStream("night.jpg")));
            default -> new Image(Objects.requireNonNull(Helper.class.getResourceAsStream("clearImage.jpg")));
        };
    }


    // Animates the scrolling effect to the right
    public static void slowScrollToRight(ScrollPane scrollPane) {
        Animation animation = new Timeline(
                new KeyFrame(Duration.seconds(0.75),
                        new KeyValue(scrollPane.hvalueProperty(), 1)));
        animation.play();
    }

    // Animates the scrolling effect to the left
    public static void slowScrollToLeft(ScrollPane scrollPane) {
        Animation animation = new Timeline(
                new KeyFrame(Duration.seconds(1.35),
                        new KeyValue(scrollPane.hvalueProperty(), -1)));
        animation.play();
    }

    // Returns the icon Image object
    public static Image getIcon(String icon) {
        return switch (icon) {
            case "snow" -> new Image(Objects.requireNonNull(WeatherHourly.class.getResourceAsStream("snowy.gif")));
            case "rain" -> new Image(Objects.requireNonNull(WeatherHourly.class.getResourceAsStream("rain.gif")));
            case "fog", "wind" -> new Image(Objects.requireNonNull(WeatherHourly.class.getResourceAsStream("wind.gif")));
            case "cloudy" -> new Image(Objects.requireNonNull(WeatherHourly.class.getResourceAsStream("cloudy.gif")));
            case "partly-cloudy-day" -> new Image(Objects.requireNonNull(WeatherHourly.class.getResourceAsStream("partly-cloudy-day.gif")));
            case "partly-cloudy-night" -> new Image(Objects.requireNonNull(WeatherHourly.class.getResourceAsStream("night.gif")));
            case "clear-night" -> new Image(Objects.requireNonNull(WeatherHourly.class.getResourceAsStream("clear-night.gif")));
            default -> new Image(Objects.requireNonNull(WeatherHourly.class.getResourceAsStream("sunny.gif")));
        };
    }

    // Returns the date string
    public static String getDate(int daysAhead) {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, daysAhead);
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(calendar.getTime());
    }

    // Returns the wind direction
    public static String getWindDirection(Double degrees) {
        if (degrees >= 337.5 || degrees <= 22.5) {
            return "North";
        } else if (degrees > 22.5 && degrees <= 67.5) {
            return "North East";
        } else if (degrees > 67.5 && degrees <= 112.5) {
            return "East";
        } else if (degrees > 112.5 && degrees <= 157.5) {
            return "South East";
        } else if (degrees > 157.5 && degrees <= 202.5) {
            return "South";
        } else if (degrees > 202.5 && degrees <= 247.5) {
            return "South West";
        } else if (degrees > 247.5 && degrees <= 292.5) {
            return "West";
        } else {
            return "North West";
        }
    }
}
