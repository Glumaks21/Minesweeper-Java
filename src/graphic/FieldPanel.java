package graphic;

import logic.*;
import javax.swing.*;
import java.awt.*;

public class FieldPanel extends JPanel {
    private final Field field;
    private final CellGui[][] grid;

    public FieldPanel(Field field) {
        this.field = field;

        setLayout(new GridLayout(field.getHeight(), field.getWidth()));
        grid = new CellGui[field.getHeight()][field.getWidth()];
        for (int y = 0; y < field.getHeight(); y++) {
            for (int x = 0; x < field.getWidth(); x++) {
                Cords cords = new Cords(x, y);
                Cell cell = new Cell(field.valueAt(cords));
                if (field.isOpenedAt(cords)) {
                    cell.open();
                } else if (field.isFlagedAt(cords)) {
                    cell.flag();
                }
                CellGui cellGui = new CellGui(cell);


                add(cellGui);
                grid[y][x] = cellGui;
                setBackground(Color.GRAY);
            }
        }
    }
}