package sample;

import javafx.scene.control.Button;

/**
 * Class which will facilitate the changing of the buttons when pressed
 */
public class GameGridControl {

    private final static String _empty = " ";
    private final static String _player1 = "X";
    private final static String _player2 = "O";

    public static void computerButtonChange(Button button, String player) {

        if (button.getText().equals(_empty)) {
            button.setText(player);

            // disable the button once the button has been clicked
            button.setDisable(true);
            button.setOpacity(1.0);
        }
    }

    public static void playerButtonChange(Button button, String player) {

        if (button.getText().equals(_empty)) {
            if (player.equals(_player1)) {
                button.setText(_player1);
            } else {
                button.setText(_player2);
            }

            // disable the button once it has been clicked
            button.setDisable(true);
            button.setOpacity(1.0);
        }
    }
}
