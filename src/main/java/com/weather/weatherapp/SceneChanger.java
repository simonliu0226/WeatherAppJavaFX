package com.weather.weatherapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Simon
 */
public class SceneChanger {
    // Method to change to basic fxml scene
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String location) {
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(SceneChanger.class.getResource(fxmlFile));
            root = loader.load();
            if (fxmlFile.equals("weatherHourly.fxml")) {
                WeatherHourly weatherHourly = loader.getController();
                weatherHourly.setInfo(location);
            } else {
                WeatherHome weatherHome = loader.getController();
                weatherHome.loadLocation(location);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        assert root != null;
        stage.setScene(new Scene(root, 1920, 1080));
        stage.setResizable(false);
        stage.show();
    }
}
