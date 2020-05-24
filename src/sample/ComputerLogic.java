package sample;


import javafx.scene.control.Button;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**s
 * Calculates the best move for the computer to make. It does this by running through
 * all possible moves to see which is the best one. If it cannot find the best move,
 * it will default to playing in either middle, or the corners.
 * This has a recursive method which will calculate the best move out of all moves.
 */
public class ComputerLogic {

    private Helper _helper = Main.getHelper();

    private Map<String,Move> dp = new HashMap<String,Move>();

    private Button[][] _allButtons;

    final private String _computer = "O";
    final private String _player = "X";
    final private String _empty = " ";

    final private int _boardSize = 3;

    public ComputerLogic(Button[][] allButtons) {

        this._allButtons = allButtons;
    }

    /**
     * Method to get all the empty spots in the tic-tac-toe board
     * @param board
     * @return
     */
    public Vector<Coordinate> getEmptyCoordinates(Button[][] board) {

        Vector<Coordinate> availableSpots = new Vector<Coordinate>();

        for (int i = 0; i < _boardSize; i++) {
            for (int j = 0; j < _boardSize; j++) {
                if (board[i][j].getText().equals(_empty)) {

                    availableSpots.add(new Coordinate(i,j));
                }
            }
        }

        return availableSpots;
    }

    public Button[][] duplicateBoard(Button[][] board) {

        Button[][] newBoard = new Button[3][3];

        for (int i = 0; i < _boardSize; i++) {
            for (int j = 0; j < _boardSize; j++) {
                newBoard[i][j] = new Button(board[i][j].getText());
            }
        }

        return newBoard;
    }

    /**
     * Method which will calculate if the computer will win and play to win those.
     * Evaluate a score for each button, if the button is losing for the computer
     * then it will mark it as -10, and if it is winning it will mark it as 10.
     * This will then let the computer choose a button which doesn't have a score of -10
     * @param board
     * @param player
     * @return
     */
    public Move calculateBestMove(Button[][] board, String player) {

        // get all the empty spots and store them in a coordinate array
        Vector<Coordinate> availableSpots = getEmptyCoordinates(board);
        Vector<Move> availableMoves = new Vector<Move>();

        String dpBoard = toString(board);

        if (dp.containsKey(dpBoard)) {
            return dp.get(dpBoard);
        }

        // loop to go through all the available moves
        for (Coordinate coord : availableSpots) {

            Button[][] testBoard = duplicateBoard(board);

            // get coord values
            int i = coord.getI();
            int j = coord.getJ();

            // set the coord spot to the player
            testBoard[i][j].setText(player);

            // check if it is a winning move
            Move winningMove;
            Result result = winning(testBoard, player);

            winningMove = new Move(result, coord);

            // if the game is over (DRAW, PLAYER_WIN, COMPUTER_WIN) then add move
            // otherwise use the MiniMax to find the next move.
            if (result == Result.DRAW) {
                availableMoves.add(winningMove);
            } else if (result == Result.PLAYER_WIN) {
                availableMoves.add(winningMove);
            } else if (result == Result.COMPUTER_WIN) {
                availableMoves.add(winningMove);
            } else {
                // change the player
                String nextPlayer = (player.equals(_player)) ? _computer : _player;

                // using MiniMax to determine what the best move is for the computer.
                winningMove = calculateBestMove(testBoard,nextPlayer);

                // set the new move based off the result and the current coordinate
                Move newMove = new Move(winningMove.getResult(), coord);

                // add the move to the vector containing all available moves
                availableMoves.add(newMove);

            }
        }

        // sort the available moves by the result so that computer winning is on the left
        // and the player winning is on the right
        Collections.sort(availableMoves, new SortScores());

        // if the player is the player, return the best move for the player
        if (player.equals(_player)) {
            dp.put(dpBoard, availableMoves.get(availableMoves.size()-1));
            return dp.get(dpBoard);
        }

        // if computer, return the best move for the computer.
        dp.put(dpBoard, availableMoves.get(0));
        return dp.get(dpBoard);
    }

    /**
     * Method to check all winning possibilities for the given player
     * @param board
     * @param player
     * @return
     */
    public Result winning(Button[][] board, String player) {

        // get the number of spaces
        int moves = 9 - getEmptyCoordinates(board).size();

        if (moves < 5) {
            return Result.INCOMPLETE;
        }

        if (    (board[0][0].getText().equals(player) && board[0][1].getText().equals(player) && board[0][2].getText().equals(player)) ||
                (board[1][0].getText().equals(player) && board[1][1].getText().equals(player) && board[1][2].getText().equals(player)) ||
                (board[2][0].getText().equals(player) && board[2][1].getText().equals(player) && board[2][2].getText().equals(player)) ||
                (board[0][0].getText().equals(player) && board[1][0].getText().equals(player) && board[2][0].getText().equals(player)) ||
                (board[0][1].getText().equals(player) && board[1][1].getText().equals(player) && board[2][1].getText().equals(player)) ||
                (board[0][2].getText().equals(player) && board[1][2].getText().equals(player) && board[2][2].getText().equals(player)) ||
                (board[0][0].getText().equals(player) && board[1][1].getText().equals(player) && board[2][2].getText().equals(player)) ||
                (board[0][2].getText().equals(player) && board[1][1].getText().equals(player) && board[2][0].getText().equals(player))) {
            return (player.equals(_player)) ? Result.PLAYER_WIN : Result.COMPUTER_WIN;
        } else if (moves == 9) {
            return Result.DRAW;
        }

        return Result.INCOMPLETE;
    }

    public String toString(Button[][] board) {

        String out = "";

        for (int i = 0; i < _boardSize; i++) {
            for (int j = 0; j < _boardSize; j++) {
                out += board[i][j].getText();
            }
        }

        return out;
    }
}
