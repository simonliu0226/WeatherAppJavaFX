module com.weather.weatherapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires unirest.java;
    requires json;

    opens com.weather.weatherapp to javafx.fxml;
    exports com.weather.weatherapp;
}