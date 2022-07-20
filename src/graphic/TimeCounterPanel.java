package graphic;

import logic.GameSession;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class TimeCounterPanel extends JPanel implements Observer {
    private final GameSession gameSession;
    private final JLabel counter;

    public TimeCounterPanel(GameSession gameSession) {
        this.gameSession = gameSession;
        counter = new JLabel();
        counter.setFont(new Font(Font.DIALOG, Font.BOLD, 60));
        counter.setForeground(Color.RED);
        this.setBackground(Color.BLACK);
        update(null, null);
        this.add(counter);
    }

    @Override
    public void update(Observable o, Object arg) {
        counter.setText(String.format("%03d", gameSession.getTime()));
    }
}
