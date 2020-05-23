package sample;

import javafx.scene.control.Button;

/**
 * Class which will facilitate the changing of the buttons when pressed
 */
public class GameGridControl {

    private static int _player;

    public static void computerButtonChange(Button button, String player) {
        final String _empty = " ";

        if (button.getText().equals(_empty)) {
            button.setText(player);

            button.setDisable(true);
            button.setOpacity(1.0);
        }
    }

    public static void playerButtonChange(Button button, String player) {

        final String _player1 = "X";
        final String _player2 = "O";
        final String _empty = " ";

        if (button.getText().equals(_empty)) {
            if (player.equals(_player1)) {
                button.setText(_player1);
            } else {
                button.setText(_player2);
            }

            button.setDisable(true);
            button.setOpacity(1.0);
        }

    }
}
