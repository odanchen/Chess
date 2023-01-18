package root.logic.utils;

public class TimerPair {
    private final ChessTimer whiteTimer;
    private final ChessTimer blackTimer;

    public TimerPair(int minutesGiven) {
        this.whiteTimer = new ChessTimer(minutesGiven);
        this.blackTimer = new ChessTimer(minutesGiven);
    }

    public void whiteMoved() {
        whiteTimer.stop();
        blackTimer.start();
    }

    public void blackMoved() {
        blackTimer.stop();
        whiteTimer.start();
    }

    public String getWhiteTimeLeft() {
        return whiteTimer.getTimeLeft();
    }

    public String getBlackTimeLeft() {
        return blackTimer.getTimeLeft();
    }
}
