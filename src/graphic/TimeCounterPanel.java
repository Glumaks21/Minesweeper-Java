package graphic;

import logic.GameSession;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.*;

public class TimeCounterPanel extends JPanel implements Observer {
    private final GameSession gameSession;
    private final MainGui mainGui;
    private final JLabel counter;

    public TimeCounterPanel(GameSession gameSession, MainGui mainGui) {
        this.gameSession = gameSession;
        this.mainGui = mainGui;
        gameSession.addObserver(this);

        counter = new JLabel();
        counter.setFont(new Font(Font.DIALOG, Font.BOLD, 70));
        counter.setForeground(Color.RED);
        setBackground(Color.BLACK);
        setBorder(new CompoundBorder(new LineBorder(Color.GRAY, 5), new LineBorder(Color.DARK_GRAY, 4)));
        add(counter);

        update(null, "time");
    }

    @Override
    public void update(Observable o, Object arg) {
        String command = (String) arg;
        if (command.equals("time")) {
            counter.setText(String.format("%03d", gameSession.getTime()));
        }
    }
}
