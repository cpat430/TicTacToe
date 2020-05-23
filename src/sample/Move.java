package sample;

public class Move {
    private Result _result;
    private Coordinate _coord;

    public Move(Result result, Coordinate coord) {
        _result = result;
        _coord = coord;
    }

    public Result getResult() {
        return _result;
    }

    public Coordinate getCoord() {
        return _coord;
    }
}
