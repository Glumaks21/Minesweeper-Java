package logic;

import logic.location.GameField;
import java.util.*;
import java.util.concurrent.*;

public class GameSession extends Observable {
    public enum Difficulty {
        EASY(9, 9, 10), NORMAL(16, 16, 40), HARD(30, 16, 99);

        private final int width;
        private final int height;
        private final int mines;

        Difficulty(int width, int height, int mines) {
            this.width = width;
            this.height = height;
            this.mines = mines;
        }
    }

    public enum State {
        RUNNING, LOSE, WIN;
    }

    private final GameField field;
    private State state;
    private int flags;
    private int time;

    public GameSession(Difficulty difficulty) {
        field = new GameField(difficulty.width, difficulty.height);
        field.setUpMines(difficulty.mines);
        field.setUpMineRange();
        state = State.RUNNING;
        flags = difficulty.mines;
    }

    public void start() {
        state = State.RUNNING;
        startTimer();
        setChanged();
        notifyObservers("state");
    }

    public int getFlags() {
        return flags;
    }

    public void increaseFlags() {
        flags++;
        setChanged();
        notifyObservers("flags");
    }

    public void decreaseFlags() {
        flags--;
        setChanged();
        notifyObservers("flags");
    }

    public int getTime() {
        return time;
    }

    public void startTimer() {
        ScheduledExecutorService timer = Executors.newScheduledThreadPool(1);
        timer.scheduleWithFixedDelay(() -> {
            if (state != State.RUNNING) {
                timer.shutdown();
            }
            time++;
            setChanged();
            notifyObservers("time");
        }, 1, 1, TimeUnit.SECONDS);
    }

    public GameField getField() {
        return field;
    }

    public void setState(State state) {
        this.state = state;
        setChanged();
        notifyObservers("state");
    }

    public State getState() {
        return state;
    }
}