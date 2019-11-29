package sample;

import javafx.scene.control.Button;

/**
 * Class which will facilitate the changing of the buttons when pressed
 */
public class GameGridControl {

    private static int _player;

    public static int[] buttonChange(Button button, int player, int currentMove) {

        int[] output = {player, currentMove};

        if (button.getText().isEmpty()) {
            if (player == 1) {
                button.setText("X");
            } else {
                button.setText("O");
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
