package sample;

import sample.Controllers.Computer;

import java.util.Comparator;

public class SortScores implements Comparator<Move> {

    // Used for sorting in ascending order of score
    public int compare(Move a, Move b)
    {

        if (a.getResult() == b.getResult()) {
            return 0;
        } else if (a.getResult() == Result.COMPUTER_WIN || b.getResult() == Result.PLAYER_WIN) {
            return -1;
        } else if (a.getResult() == Result.PLAYER_WIN || b.getResult() == Result.COMPUTER_WIN) {
            return 1;
        }

        return 0;
    }
}
