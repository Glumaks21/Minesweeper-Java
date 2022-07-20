package logic;

import graphic.FlagsCounterPanel;

public class FlagsCounterController {
    private final FlagsCounterPanel flagCounterPanel;
    private final GameSession gameSession;

    public FlagsCounterController(FlagsCounterPanel flagCounterPanel, GameSession gameSession) {
        this.flagCounterPanel = flagCounterPanel;
        this.gameSession = gameSession;
    }

    public void increase() {
        gameSession.increaseFlags();
    }

    public void decrease() {
        gameSession.decreaseFlags();
    }
}