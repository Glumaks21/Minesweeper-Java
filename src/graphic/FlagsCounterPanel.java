package graphic;

import logic.FlagsCounterController;
import logic.GameSession;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class FlagsCounterPanel extends JPanel implements Observer {
    private final GameSession gameSession;
    private final FlagsCounterController controller;
    private final JLabel counter;

    public FlagsCounterPanel(GameSession gameSession) {
        this.gameSession = gameSession;
        controller = new FlagsCounterController(this, gameSession);
        counter = new JLabel();
        counter.setFont(new Font(Font.DIALOG, Font.BOLD, 60));
        counter.setForeground(Color.RED);
        this.setBackground(Color.BLACK);
        update(null, null);
        this.add(counter);
    }

    @Override
    public void update(Observable o, Object arg) {
        counter.setText(String.format("%03d", gameSession.getFlags()));
    }
}