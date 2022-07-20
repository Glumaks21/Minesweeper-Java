package graphic;

import logic.Cell;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class CellGui extends JLabel {
    private final Cell cell;

    public CellGui(Cell cell) {
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

    public void update() {
        String path = new File("").getAbsolutePath() + "\\resources";
        System.out.println(path);
        ImageIcon img = null;
        if (!cell.isOpened()) {
            path += "\\cell_closed.png";
        } else if (cell.isFlaged()) {
            path += "\\cell_flaged.png";
        } else {
            switch (cell.getValue()) {
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
        img = new ImageIcon(path);
        setIcon(img);
    }
}