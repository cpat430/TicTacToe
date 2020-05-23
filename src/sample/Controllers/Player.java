package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import sample.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Player implements Initializable {

    private Helper _helper = Main.getHelper();

    private ComputerLogic _logic;

    final private String _player1 = "X";
    final private String _player2 = "O";
    final private String _empty = " ";

    private int _currentPlayer = 1;
    private int _moves = 0;
    private int[] _playerPlusMoves = {};

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

    void makeMove(ActionEvent event, Button button, int curPlayer, int curMoves) {
        _playerPlusMoves = GameGridControl.buttonChange(button, curPlayer, curMoves);

        String player = (curPlayer == 1) ? "X" : "O";

        Result result = _logic.winning(board,player);

        if (result == Result.PLAYER_WIN) {
            _helper.addAlert(event, "Player " + curPlayer + " wins!");
        } else if (result == Result.COMPUTER_WIN) {
            _helper.addAlert(event, "Player " + curPlayer + " wins!");
        } else if (result == Result.DRAW) {
            _helper.addAlert(event, "Game ended in a draw");
        }

        _currentPlayer = _playerPlusMoves[0];
        _moves = _playerPlusMoves[1];
    }

    @FXML
    void bottomLeftPress(ActionEvent event) {
        makeMove(event, bottomLeft, _currentPlayer, _moves);
    }

    @FXML
    void bottomMiddlePress(ActionEvent event) {
        makeMove(event, bottomMiddle, _currentPlayer, _moves);
    }

    @FXML
    void bottomRightPress(ActionEvent event) {
        makeMove(event, bottomRight, _currentPlayer, _moves);
    }

    @FXML
    void leftPress(ActionEvent event) {
        makeMove(event, left, _currentPlayer, _moves);
    }

    @FXML
    void middlePress(ActionEvent event) {
        makeMove(event, middle, _currentPlayer, _moves);
    }

    @FXML
    void rightPress(ActionEvent event) {
        makeMove(event, right, _currentPlayer, _moves);
    }

    @FXML
    void topLeftPress(ActionEvent event) {
        makeMove(event, topLeft, _currentPlayer, _moves);
    }

    @FXML
    void topMiddlePress(ActionEvent event) {
        makeMove(event, topMiddle, _currentPlayer, _moves);
    }

    @FXML
    void topRightPress(ActionEvent event) {
        makeMove(event, topRight, _currentPlayer, _moves);
    }

    @FXML
    void restart(ActionEvent event) throws IOException {

        // reset all the buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].setText(" ");
                board[i][j].setDisable(false);
            }
        }

        _moves = 0;
        _currentPlayer = 1;
    }

    @FXML
    void mainMenu(ActionEvent event) throws IOException {
        _helper.changeScene(event, "MainMenu.fxml");
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

        this._logic = new ComputerLogic(board);

    }

    public Button[][] getButtons() {
        return board;
    }

}
