package graphic;

import logic.GameSession;
import javax.swing.*;
import java.awt.*;

public class MainGui {
    private JFrame frame;
    private FlagsCounterPanel flagsPanel;
    private TimeCounterPanel timePanel;
    private FieldPanel fieldPanel;
    private GameSession gameSession;

    public void start() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gameSession = new GameSession(GameSession.Difficulty.EASY);
        flagsPanel = new FlagsCounterPanel(gameSession);
        timePanel = new TimeCounterPanel(gameSession);
        fieldPanel = new FieldPanel(gameSession.getField());

        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(BorderLayout.WEST, flagsPanel);
        northPanel.add(BorderLayout.EAST, timePanel);

        frame.getContentPane().add(BorderLayout.NORTH, northPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, fieldPanel);

        frame.pack();
        frame.setVisible(true);
    }
}