package com.weather.weatherapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Simon
 */
public class Welcome implements Initializable {
    @FXML
    private Button enter_button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        enter_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SceneChanger.changeScene(actionEvent, "weatherHome.fxml", "Current Weather Forecast", "New York");
            }
        });
    }
}
