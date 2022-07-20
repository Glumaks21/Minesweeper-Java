package graphic;

import logic.FieldController;
import logic.location.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;

public class FieldPanel extends JPanel implements Observer, MouseListener {
    private final Field field;
    private final CellGui[][] grid;
    private Field controller;

    public FieldPanel(GameField field) {
        this.field = field;
        field.addObserver(this);
        controller = new FieldController(field, this);

        setBackground(Color.GRAY);
        GridLayout gridLayout = new GridLayout(field.getHeight(), field.getWidth());
        gridLayout.setVgap(2);
        gridLayout.setHgap(2);
        setLayout(gridLayout);
        grid = new CellGui[field.getHeight()][field.getWidth()];
        for (int y = 0; y < field.getHeight(); y++) {
            for (int x = 0; x < field.getWidth(); x++) {
                Cords cords = new Cords(x, y);
                GameField.FieldCell cell = new GameField.FieldCell();
                cell.setValue(field.valueAt(cords));
                if (field.isOpenedAt(cords)) {
                    cell.open();
                } else if (field.isFlagedAt(cords)) {
                    cell.flag();
                }
                CellGui cellGui = new CellGui(cell);
                cellGui.addMouseListener(this);
                grid[y][x] = cellGui;
                add(cellGui);
            }
        }
    }

    public Field getFieldController() {
        return controller;
    }

    public void setController(Field controller) {
        this.controller = controller;
    }

    @Override
    public void update(Observable o, Object arg) {
        for (int y = 0; y < field.getHeight(); y++) {
            for (int x = 0; x < field.getWidth(); x++) {
                Cords cords = new Cords(x, y);
                if (field.isOpenedAt(cords) != grid[y][x].isOpened()) {
                    grid[y][x].open();
                } else if (field.isFlagedAt(cords) != grid[y][x].isFlaged()) {
                    grid[y][x].flag();
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Cords cords = locationOf((CellGui) e.getSource());
        int button = e.getButton();

        if (button == 1) {
            controller.openAt(cords);
        } else if (button == 3) {
            controller.flagAt(cords);
        }
    }

    private Cords locationOf(CellGui cellGui) {
        for (int y = 0; y < field.getHeight(); y++) {
            for (int x = 0; x < field.getWidth(); x++) {
                if (cellGui == grid[y][x]) {
                    return new Cords(x, y);
                }
            }
        }
        return null;
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    public static class CellGui extends JLabel {
        private final GameField.FieldCell cell;

        public CellGui(GameField.FieldCell cell) {
            this.cell = cell;
            this.setPreferredSize(new Dimension(50, 50));
            update();
        }

        public boolean isOpened() {
            return cell.isOpened();
        }

        public void open() {
            cell.open();
            update();
        }

        public boolean isFlaged() {
            return cell.isFlaged();
        }

        public void flag() {
            cell.flag();
            update();
        }

        public void boom() {
            cell.boom();
            update();
        }

        public void update() {
            String path = new File("").getAbsolutePath() + "\\resources\\cell_pictures";
            if (cell.isBombed()) {
                path += "\\cell_bombed.png";
            } else if (cell.isFlaged()) {
                path += "\\cell_flaged.png";
            } else if (!cell.isOpened()) {
                path += "\\cell_closed.png";
            } else {
                switch (cell.getValue()) {
                    case -1:
                        path += "\\cell_mine.png";
                        break;
                    case 0:
                        path += "\\cell_0.png";
                        break;
                    case 1:
                        path += "\\cell_1.png";
                        break;
                    case 2:
                        path += "\\cell_2.png";
                        break;
                    case 3:
                        path += "\\cell_3.png";
                        break;
                    case 4:
                        path += "\\cell_4.png";
                        break;
                    case 5:
                        path += "\\cell_5.png";
                        break;
                    case 6:
                        path += "\\cell_6.png";
                        break;
                    case 7:
                        path += "\\cell_7.png";
                        break;
                    case 8:
                        path += "\\cell_8.png";
                        break;
                }
            }
            setIcon(new ImageIcon(path));
        }
    }
}