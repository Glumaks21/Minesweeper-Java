package logic.location;

import java.util.*;

public final class GameField extends Observable implements Field {
    private final int width;
    private final int height;
    private final FieldCell[][] grid;

    public GameField(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new FieldCell[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = new FieldCell();
            }
        }
    }

    @Override
    public int getWidth() {
        return width;
    }
    @Override
    public int getHeight() {
        return height;
    }

    public void setUpMines(int mines) {
        int set = 0;
        while(set != mines) {
            int x = (int) (Math.random() * width);
            int y = (int) (Math.random() * height);

            Cords cords = new Cords(x, y);
            if (valueAt(cords) == -1) {
                continue;
            }
            setValueAt(cords, -1);
            set++;
        }
    }

    public void setUpMineRange() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Cords cords = new Cords(x, y);
                if (valueAt(cords) != -1) {
                    setValueAt(cords, countMinesAround(cords));
                }
            }
        }
    }

    private int countMinesAround(Cords cords) {
        int centerX = cords.getX();
        int centerY = cords.getY();

        int count = 0;
        for (int y = centerY - 1; y <= centerY + 1; y++) {
            for (int x = centerX - 1; x <= centerX + 1; x++) {
                Cords currCords = new Cords(x, y);
                if (currCords.equals(cords)) {
                    continue;
                }

                if (isCordsBelong(currCords) && valueAt(currCords) == -1) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isCordsBelong(Cords cords) {
        return cords.getX() >= 0 && cords.getX() < width &&
                cords.getY() >= 0 && cords.getY() < height;
    }

    @Override
    public int valueAt(Cords cords) {
        return grid[cords.getY()][cords.getX()].getValue();
    }

    @Override
    public void setValueAt(Cords cords, int value) {
        grid[cords.getY()][cords.getX()].setValue(value);
        setChanged();
        notifyObservers();
    }

    @Override
    public boolean isOpenedAt(Cords cords) {
        return grid[cords.getY()][cords.getX()].isOpened();
    }

    @Override
    public void openAt(Cords cords) {
        if (!isFlagedAt(cords)) {
            if (valueAt(cords) == -1) {
                boomAt(cords);
                openAll();
            } else if (valueAt(cords) == 0) {
                deepOpenAt(cords);
            } else {
                grid[cords.getY()][cords.getX()].open();
            }

            setChanged();
            notifyObservers();
        }
    }

    private void deepOpenAt(Cords cords) {
        int x = cords.getX();
        int y = cords.getY();

        if (!isCordsBelong(cords) || isOpenedAt(cords) || valueAt(cords) != 0) {
            return;
        }

        grid[y][x].open();
        deepOpenAt(new Cords(x + 1, y));
        deepOpenAt(new Cords(x - 1, y));
        deepOpenAt(new Cords(x, y + 1));
        deepOpenAt(new Cords(x, y - 1));
    }

    private void openAll() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x].open();
            }
        }
    }

    @Override
    public boolean isFlagedAt(Cords cords) {
        return grid[cords.getY()][cords.getX()].isFlaged();
    }

    @Override
    public void flagAt(Cords cords) {
        if (!isOpenedAt(cords)) {
            grid[cords.getY()][cords.getX()].flag();
            setChanged();
            notifyObservers();
        }
    }

    @Override
    public boolean isBombedAt(Cords cords) {
        return grid[cords.getY()][cords.getX()].isBombed();
    }

    @Override
    public void boomAt(Cords cords) {
        grid[cords.getY()][cords.getX()].boom();
        setChanged();
        notifyObservers();
    }

    public static class FieldCell {
        private int value;
        private boolean opened;
        private boolean flaged;
        private boolean bombed;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
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

        public boolean isBombed() {
            return bombed;
        }

        public void boom() {
            if (value == -1) {
                bombed = true;
            }
        }
    }
}