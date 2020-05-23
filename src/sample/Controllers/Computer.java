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

    private int _player = 1;

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

//    @FXML
//    void bottomLeftPress(ActionEvent event) {
//        _playerPlusMoves = GameGridControl.buttonChange(bottomLeft, _player, _moves);
//
//        _player = _playerPlusMoves[0];
//        _moves = _playerPlusMoves[1];
//
//        String player = (_player == 1) ? "X" : "O";
//
//        if (_logic.winning(board, player).equals((player.equals("X")) ? Result.PLAYER_WIN : Result.COMPUTER_WIN)) {
//            _helper.addAlert(event, "Player " + (3 - _player) + " wins!");
//        }
//    }
//
//    @FXML
//    void bottomMiddlePress(ActionEvent event) {
//        _playerPlusMoves = GameGridControl.buttonChange(bottomMiddle, _player, _moves);
//
//        _player = _playerPlusMoves[0];
//        _moves = _playerPlusMoves[1];
//
//        String player = (_player == 1) ? "X" : "O";
//
//        if (_logic.winning(board, player).equals((player.equals("X")) ? Result.PLAYER_WIN : Result.COMPUTER_WIN)) {
//            _helper.addAlert(event, "Player " + (3 - _player) + " wins!");
//        }
//    }


    @FXML
    void buttonPress(ActionEvent event) {

        // get the information from the event
        Button button = (Button) event.getSource();

        _playerPlusMoves = GameGridControl.buttonChange(button, _player, _moves);

        _player = _playerPlusMoves[0];
        _moves = _playerPlusMoves[1];

        String player = (_player == 1) ? "X" : "O";

        if (_logic.winning(board, player).equals((player.equals("X")) ? Result.PLAYER_WIN : Result.COMPUTER_WIN)) {
            _helper.addAlert(event, "Player " + (3 - _player) + " wins!");
        }

        // play the computer's move
        makeComputerMove(board);
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
        _player = 1;
    }

    @FXML
    void mainMenu(ActionEvent event) throws IOException {
        _helper.changeScene(event, "MainMenu.fxml");
    }

    private void makeComputerMove(Button[][] board) {
        // calculate the best move
        Move bestMove = _logic.calculateBestMove(board, "O");

        // get the x and y coordinates
        int x = bestMove.getCoord().getI();
        int y = bestMove.getCoord().getJ();

        board[x][y].setText("O");

        _player = 1;

//        return board;
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

    public Button[][] getButtons() {
        return board;
    }

}
