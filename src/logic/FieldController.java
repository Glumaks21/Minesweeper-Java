package logic;

import graphic.FieldPanel;
import logic.location.*;

public class FieldController implements Field {
    private final FieldPanel fieldPanel;
    private final Field field;

    public FieldController(GameField field, FieldPanel fieldPanel) {
        this.field = field;
        this.fieldPanel = fieldPanel;
    }

    @Override
    public int getWidth() {
        return field.getWidth();
    }

    @Override
    public int getHeight() {
        return field.getHeight();
    }

    @Override
    public int valueAt(Cords cords) {
        return field.valueAt(cords);
    }

    @Override
    public void setValueAt(Cords cords, int value) {
        field.setValueAt(cords, value);
    }

    @Override
    public boolean isOpenedAt(Cords cords) {
        return field.isOpenedAt(cords);
    }

    @Override
    public void openAt(Cords cords) {
        field.openAt(cords);
    }

    @Override
    public boolean isFlagedAt(Cords cords) {
        return field.isFlagedAt(cords);
    }

    @Override
    public void flagAt(Cords cords) {
        field.flagAt(cords);
    }

    @Override
    public boolean isBombedAt(Cords cords) {
        return field.isBombedAt(cords);
    }

    @Override
    public void boomAt(Cords cords) {
        field.boomAt(cords);
    }
}