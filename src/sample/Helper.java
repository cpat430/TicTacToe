package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.util.Optional;

public class Helper {

    public void changeScene(ActionEvent event, String scene) throws IOException {
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("Scenes/" + scene));
        Scene newScene = new Scene(newSceneParent);
        Stage window = (Stage) (((Node)event.getSource()).getScene().getWindow());
        window.setScene(newScene);
        window.show();
    }

    public void addAlert(ActionEvent event, String message) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText(message);

        Optional<ButtonType> result = a.showAndWait();
        result.ifPresent(res -> {
            if (res.equals(ButtonType.OK)) {
                try {
                    changeScene(event, "MainMenu.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } 
        });
    }
}
