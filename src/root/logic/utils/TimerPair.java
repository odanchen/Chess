package root.logic.utils;

public class TimerPair {
    private ChessTimer whiteTimer;
    private ChessTimer blackTimer;

    public TimerPair(int minutesGiven) {
        this.whiteTimer = new ChessTimer(minutesGiven);
        this.blackTimer = new ChessTimer(minutesGiven);
    }

    public ChessTimer getWhiteTimer() {
        return whiteTimer;
    }

    public ChessTimer getBlackTimer() {
        return blackTimer;
    }
}
