package logic;

import java.util.Observable;

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

    private final Field field;
    private int flags;
    private int time;

    public GameSession(Difficulty difficulty) {
        field = new Field(difficulty.width, difficulty.height);
        field.setUpMines(difficulty.mines);
        field.setUpMineRange();
        flags = difficulty.mines;
    }

    public int getFlags() {
        return flags;
    }

    public void increaseFlags() {
        flags++;
    }

    public void decreaseFlags() {
        flags--;
    }

    public int getTime() {
        return time;
    }

    public Field getField() {
        return field;
    }
}