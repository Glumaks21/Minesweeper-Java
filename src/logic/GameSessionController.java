package logic;

import graphic.MainGui;
import logic.location.*;

public class GameSessionController implements Field {
    private final GameSession gameSession;
    private final MainGui mainGui;
    private Field fieldController;

    public GameSessionController(GameSession gameSession, MainGui mainGui) {
        this.gameSession = gameSession;
        this.mainGui = mainGui;
        fieldController = mainGui.getFieldController();
    }

    @Override
    public int getWidth() {
        return fieldController.getWidth();
    }

    @Override
    public int getHeight() {
        return fieldController.getHeight();
    }

    @Override
    public int valueAt(Cords cords) {
        return fieldController.valueAt(cords);
    }

    @Override
    public void setValueAt(Cords cords, int value) {
        fieldController.setValueAt(cords, value);
    }

    @Override
    public boolean isOpenedAt(Cords cords) {
        return fieldController.isOpenedAt(cords);
    }

    @Override
    public void openAt(Cords cords) {
        if (gameSession.getState() == GameSession.State.RUNNING) {
            fieldController.openAt(cords);

            if (fieldController.isBombedAt(cords)) {
                gameSession.setState(GameSession.State.LOSE);
            } else {
                checkWon();
            }
        }
    }

    private void checkWon() {
        for (int y = 0; y < fieldController.getHeight(); y++) {
            for (int x = 0; x < fieldController.getWidth(); x++) {
                Cords cords = new Cords(x, y);
                if (!fieldController.isOpenedAt(cords) && !fieldController.isFlagedAt(cords)) {
                    return;
                }
            }
        }
        gameSession.setState(GameSession.State.WIN);

    }

    @Override
    public boolean isFlagedAt(Cords cords) {
        return fieldController.isFlagedAt(cords);
    }

    @Override
    public void flagAt(Cords cords) {
        if (gameSession.getState() == GameSession.State.RUNNING) {
            fieldController.flagAt(cords);

            if (fieldController.isFlagedAt(cords)) {
                gameSession.decreaseFlags();
                checkWon();
            } else if (!fieldController.isOpenedAt(cords)) {
                gameSession.increaseFlags();
            }
        }
    }

    @Override
    public boolean isBombedAt(Cords cords) {
        return fieldController.isBombedAt(cords);
    }

    @Override
    public void boomAt(Cords cords) {
        fieldController.boomAt(cords);
    }
}