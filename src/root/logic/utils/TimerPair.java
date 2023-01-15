package root.logic.utils;

public class TimerPair {
    private final ChessTimerPair whiteTimer;
    private final ChessTimerPair blackTimer;

    public TimerPair(int minutesGiven) {
        this.whiteTimer = new ChessTimerPair(minutesGiven);
        this.blackTimer = new ChessTimerPair(minutesGiven);
    }

    public ChessTimerPair getWhiteTimer() {
        return whiteTimer;
    }

    public ChessTimerPair getBlackTimer() {
        return blackTimer;
    }
}
