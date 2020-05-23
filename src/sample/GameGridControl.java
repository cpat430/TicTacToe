package sample;

import javafx.scene.control.Button;

/**
 * Class which will facilitate the changing of the buttons when pressed
 */
public class GameGridControl {

    private static int _player;

//    public static void buttonChange(Button button, String player) {
//        final String _player1 = "X";
//        final String _player2 = "O";
//        final String _empty = " ";
//
//
//    }
//
    public static int[] buttonChange(Button button, int player, int currentMove) {

        final String _player1 = "X";
        final String _player2 = "O";
        final String _empty = " ";

        int[] output = {player, currentMove};

        if (button.getText().equals(_empty)) {
            if (player == 1) {
                button.setText(_player1);
            } else {
                button.setText(_player2);
            }

            int moves = currentMove;
            moves++;

            button.setDisable(true);
            button.setOpacity(1.0);

            output[0] = 3 - player;
            output[1] = moves;

            return output;
        }

        return output;
    }
}
