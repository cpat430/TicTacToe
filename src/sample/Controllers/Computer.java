package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import sample.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Computer implements Initializable {

    private Helper _helper = Main.getHelper();

    private ComputerLogic _logic;

    private String _player = "X";
    final private String _computer = "O";

    @FXML private Button topLeft;
    @FXML private Button topMiddle;
    @FXML private Button left;
    @FXML private Button bottomLeft;
    @FXML private Button middle;
    @FXML private Button bottomMiddle;
    @FXML private Button topRight;
    @FXML private Button right;
    @FXML private Button bottomRight;

    @FXML private Button restartButton;
    @FXML private Button mainMenuButton;

    private Button[][] board = new Button[3][3];

    @FXML
    void buttonPress(ActionEvent event) {

        // get the information from the event
        Button button = (Button) event.getSource();

        // make the user's turn
        GameGridControl.computerButtonChange(button, _player);

        // check if the user wins
        boolean win = checkWin(_player, event);

        if (win) return;

        // play the computer's move
        makeComputerMove(board);

        // check if the computer wins
        checkWin(_computer, event);

    }

    // resets the board
    @FXML
    void restart(ActionEvent event) throws IOException {

        // reset all the buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].setText(" ");
                board[i][j].setDisable(false);
            }
        }
    }

    // method to return to the main menu
    @FXML
    void mainMenu(ActionEvent event) throws IOException {
        _helper.changeScene(event, "MainMenu.fxml");
    }

    private void makeComputerMove(Button[][] board) {
        // calculate the best move
        Move bestMove = _logic.calculateBestMove(board, _computer);

        // get the i and j coordinates
        int i = bestMove.getCoord().getI();
        int j = bestMove.getCoord().getJ();

        board[i][j].setText(_computer);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        board[0][0] = topLeft;
        board[0][1] = topMiddle;
        board[0][2] = topRight;
        board[1][0] = left;
        board[1][1] = middle;
        board[1][2] = right;
        board[2][0] = bottomLeft;
        board[2][1] = bottomMiddle;
        board[2][2] = bottomRight;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j].setText(" ");
            }
        }

        this._logic = new ComputerLogic(board);
    }

    public boolean checkWin(String player, ActionEvent event) {
        Result result = _logic.winning(board,player);

        if (result == Result.PLAYER_WIN) {
            _helper.addAlert(event, "You win!", "Computer.fxml");
        } else if (result == Result.COMPUTER_WIN) {
            _helper.addAlert(event, "Computer wins! Try again next time", "Computer.fxml");
        } else if (result == Result.DRAW) {
            _helper.addAlert(event, "Game ended in a draw", "Computer.fxml");
        } else {
            return false;
        }

        return true;
    }

    public Button[][] getButtons() {
        return board;
    }

}
