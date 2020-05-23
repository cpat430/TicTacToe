package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Helper {

    Button[][] allButtons;

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

        // customise the alert to have a restart button
        ButtonType restart = new ButtonType("Restart");
        ButtonType returnToMenu = new ButtonType("Main Menu", ButtonBar.ButtonData.CANCEL_CLOSE);

        a.getButtonTypes().setAll(restart, returnToMenu);

        Optional<ButtonType> result = a.showAndWait();
        result.ifPresent(res -> {
            if (res.equals(returnToMenu)) {
                try {
                    changeScene(event, "MainMenu.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void disableButtons(Button[][] allButtons, boolean disable) {

        if (disable) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    allButtons[i][j].setDisable(true);
                    allButtons[i][j].setOpacity(1.0);
                }
            }
        } else {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    allButtons[i][j].setDisable(false);
                    allButtons[i][j].setOpacity(1.0);
                }
            }
        }
    }


}
