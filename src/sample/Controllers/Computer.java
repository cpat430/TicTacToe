package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import sample.GameGridControl;
import sample.Helper;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Computer implements Initializable {

    Helper _helper = Main.getHelper();

    private int _player = 1;

    private int _moves = 0;

    private int[] _playerPlusMoves = {};

    @FXML
    private Button topLeft;

    @FXML
    private Button topMiddle;

    @FXML
    private Button left;

    @FXML
    private Button bottomLeft;

    @FXML
    private Button middle;

    @FXML
    private Button bottomMiddle;

    @FXML
    private Button topRight;

    @FXML
    private Button right;

    @FXML
    private Button bottomRight;

    @FXML
    private Button restartButton;

    @FXML
    private Button mainMenuButton;


    private Button[][] allButtons = new Button[3][3];

    @FXML
    void bottomLeftPress(ActionEvent event) {
        _playerPlusMoves = GameGridControl.buttonChange(bottomLeft, _player, _moves);

        _player = _playerPlusMoves[0];
        _moves = _playerPlusMoves[1];

        if (checkWinner(bottomLeft)) {
            _helper.addAlert(event, "Player " + _player + " wins!");
        }
    }

    @FXML
    void bottomMiddlePress(ActionEvent event) {
        _playerPlusMoves = GameGridControl.buttonChange(bottomMiddle, _player, _moves);

        _player = _playerPlusMoves[0];
        _moves = _playerPlusMoves[1];

        if (checkWinner(bottomMiddle)) {
            _helper.addAlert(event, "Player " + _player + " wins!");
        }
    }

    @FXML
    void bottomRightPress(ActionEvent event) {
        _playerPlusMoves = GameGridControl.buttonChange(bottomRight, _player, _moves);

        _player = _playerPlusMoves[0];
        _moves = _playerPlusMoves[1];

        if (checkWinner(bottomRight)) {
            _helper.addAlert(event, "Player " + _player + " wins!");
        }
    }

    @FXML
    void leftPress(ActionEvent event) {
        _playerPlusMoves = GameGridControl.buttonChange(left, _player, _moves);

        _player = _playerPlusMoves[0];
        _moves = _playerPlusMoves[1];

        if (checkWinner(left)) {
            _helper.addAlert(event, "Player " + _player + " wins!");
        }
    }

    @FXML
    void middlePress(ActionEvent event) {
        _playerPlusMoves = GameGridControl.buttonChange(middle, _player, _moves);

        _player = _playerPlusMoves[0];
        _moves = _playerPlusMoves[1];

        if (checkWinner(middle)) {
            _helper.addAlert(event, "Player " + _player + " wins!");
        }
    }

    @FXML
    void rightPress(ActionEvent event) {
        _playerPlusMoves = GameGridControl.buttonChange(right, _player, _moves);

        _player = _playerPlusMoves[0];
        _moves = _playerPlusMoves[1];

        if (checkWinner(right)) {
            _helper.addAlert(event, "Player " + _player + " wins!");
        }
    }

    @FXML
    void topLeftPress(ActionEvent event) {
        _playerPlusMoves = GameGridControl.buttonChange(topLeft, _player, _moves);

        _player = _playerPlusMoves[0];
        _moves = _playerPlusMoves[1];

        if (checkWinner(topLeft)) {
            _helper.addAlert(event, "Player " + _player + " wins!");
        }
    }

    @FXML
    void topMiddlePress(ActionEvent event) {
        _playerPlusMoves = GameGridControl.buttonChange(topMiddle, _player, _moves);

        _player = _playerPlusMoves[0];
        _moves = _playerPlusMoves[1];

        if (checkWinner(topMiddle)) {
            _helper.addAlert(event, "Player " + _player + " wins!");
        }
    }

    @FXML
    void topRightPress(ActionEvent event) {
        _playerPlusMoves = GameGridControl.buttonChange(topRight, _player, _moves);

        _player = _playerPlusMoves[0];
        _moves = _playerPlusMoves[1];

        if (checkWinner(topRight)) {
            _helper.addAlert(event, "Player " + _player + " wins!");
        }
    }

    @FXML
    void restart(ActionEvent event) throws IOException {

        // reset all the buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                allButtons[i][j].setText("");
                allButtons[i][j].setDisable(false);
            }
        }

        _moves = 0;
        _player = 1;
    }

    @FXML
    void mainMenu(ActionEvent event) throws IOException {
        _helper.changeScene(event, "MainMenu.fxml");
    }

    private boolean checkWinner(Button button) {

        int x = 0,y = 0;

        // find index of input button
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (allButtons[i][j] == button) {
                    x = i;
                    y = j;
                    break;
                }
            }
        }

        // check all those in line of the button inputted.

        if (_moves > 4) {
            // vertical
            if ((allButtons[x][0].getText() == allButtons[x][1].getText()) && (allButtons[x][1].getText() == allButtons[x][2].getText())) {
                return true;
            }

            // horizontal
            if ((allButtons[0][y].getText() == allButtons[1][y].getText()) && (allButtons[1][y].getText() == allButtons[2][y].getText())) {
                return true;
            }

            // diagonal - check both directions
            if ((x + y) % 2 == 0) {
                if ((allButtons[0][0].getText() == allButtons[1][1].getText()) && (allButtons[1][1].getText() == allButtons[2][2].getText())) {
                    return true;
                }

                if ((allButtons[0][2].getText() == allButtons[1][1].getText()) && (allButtons[1][1].getText() == allButtons[2][0].getText())) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        allButtons[0][0] = topLeft;
        allButtons[0][1] = topMiddle;
        allButtons[0][2] = topRight;
        allButtons[1][0] = left;
        allButtons[1][1] = middle;
        allButtons[1][2] = right;
        allButtons[2][0] = bottomLeft;
        allButtons[2][1] = bottomMiddle;
        allButtons[2][2] = bottomRight;

    }


}
