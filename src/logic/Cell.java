package logic;

public class Cell {
    private final int value;
    private boolean opened;
    private boolean flaged;

    public Cell(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isOpened() {
        return opened;
    }

    public void open() {
        if (!flaged) {
            opened = true;
        }
    }

    public boolean isFlaged() {
        return flaged;
    }

    public void flag() {
        if (!opened) {
            flaged = !flaged;
        }
    }
}