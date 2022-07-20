package logic.location;

public interface Field {
    int getWidth();
    int getHeight();
    int valueAt(Cords cords);
    void setValueAt(Cords cords, int value);
    boolean isOpenedAt(Cords cords);
    void openAt(Cords cords);
    boolean isFlagedAt(Cords cords);
    void flagAt(Cords cords);
    boolean isBombedAt(Cords cords);
    void boomAt(Cords cords);
}
