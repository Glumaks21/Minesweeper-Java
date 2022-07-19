package logic;

public class Field {
    private final int width;
    private final int height;
    private final Cell[][] grid;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new Cell[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = new Cell(0);
            }
        }
    }

    public void setUpMines(int mines) {
        if (mines > height * width) {
            throw new IllegalArgumentException("Too much mines");
        }
        if (mines <= 0) {
            throw new IllegalArgumentException("Not a positive number of mines");
        }

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

    public int valueAt(Cords cords) {
        return grid[cords.getY()][cords.getX()].getValue();
    }

    public void setValueAt(Cords cords, int value) {
        grid[cords.getY()][cords.getX()] = new Cell(value);
    }

    public boolean isOpenedAt(Cords cords) {
        return grid[cords.getY()][cords.getX()].isOpened();
    }

    public boolean isFlagedAt(Cords cords) {
        return grid[cords.getY()][cords.getX()].isFlaged();
    }

    public void print() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.printf("%4d", grid[y][x].getValue());
            }
            System.out.println();
        }
    }
}

