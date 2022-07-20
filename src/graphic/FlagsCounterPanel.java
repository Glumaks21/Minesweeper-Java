package graphic;

import logic.GameSession;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.*;

public class FlagsCounterPanel extends JPanel implements Observer {
    private final GameSession gameSession;
    private final MainGui mainGui;
    private final JLabel counter;

    public FlagsCounterPanel(GameSession gameSession, MainGui mainGui) {
        this.gameSession = gameSession;
        this.mainGui = mainGui;
        gameSession.addObserver(this);

        counter = new JLabel();
        counter.setFont(new Font(Font.DIALOG, Font.BOLD, 70));
        counter.setForeground(Color.RED);
        setBackground(Color.BLACK);
        setBorder(new CompoundBorder(new LineBorder(Color.GRAY, 5), new LineBorder(Color.DARK_GRAY, 4)));
        add(counter);

        update(null, "flags");
    }

    @Override
    public void update(Observable o, Object arg) {
        String command = (String) arg;
        if (command.equals("flags")) {
            counter.setText(String.format("%03d", gameSession.getFlags()));
        }
    }
}