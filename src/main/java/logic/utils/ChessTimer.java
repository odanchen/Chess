package logic.utils;

public class ChessTimer {
    private float secondsLeft;
    private long timeSinceStart;
    private boolean isPaused = true;

    public ChessTimer(int minutesLeft) {
        this.secondsLeft = minutesLeft * 60;
    }

    public String getTimeLeft() {
        if (!isPaused) {
            secondsLeft -= (System.currentTimeMillis() - timeSinceStart) / 1000f;
            timeSinceStart = System.currentTimeMillis();
        }
        return String.format("%02d:%02d", (int) (secondsLeft / 60), (int) (secondsLeft % 60));
    }

    public void start() {
        timeSinceStart = System.currentTimeMillis();
        isPaused = false;
    }

    public void stop() {
        isPaused = true;
    }
}
