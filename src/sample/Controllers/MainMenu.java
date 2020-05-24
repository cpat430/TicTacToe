package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import sample.Helper;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenu implements Initializable {

    Helper _helper = Main.getHelper();

    @FXML
    private Button playComputerButton;

    @FXML
    private Button playPlayerButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button helpButton;

    @FXML
    void playComputer(ActionEvent event) throws IOException {
        _helper.changeScene(event, "Computer.fxml");
    }

    @FXML
    void playPlayer(ActionEvent event) throws IOException {
        _helper.changeScene(event, "Player.fxml");
    }

    @FXML
    void toHelp(ActionEvent event) throws IOException {
//        _helper.changeScene(event, "Help.fxml");
    }

    @FXML
    void toSettings(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        settingsButton.setDisable(true);
        helpButton.setDisable(true);

    }
}

