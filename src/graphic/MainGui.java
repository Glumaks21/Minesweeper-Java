package graphic;

import logic.*;
import logic.location.Field;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class MainGui {
    private final JFrame frame;
    private FlagsCounterPanel flagsPanel;
    private TimeCounterPanel timePanel;
    private SmilePanel smilePanel;
    private FieldPanel fieldPanel;
    private GameSession gameSession;

    public MainGui() {
        frame = new JFrame("Minesweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setForeground(Color.BLACK);
    }

    public void start() {
        setUpGameSession();
        setUpGui();
        gameSession.start();

        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void setUpGameSession() {
        gameSession = new GameSession(GameSession.Difficulty.EASY);
        flagsPanel = new FlagsCounterPanel(gameSession, this);
        smilePanel = new SmilePanel(gameSession, this);
        gameSession.addObserver(smilePanel);
        timePanel = new TimeCounterPanel(gameSession, this);
        fieldPanel = new FieldPanel(gameSession.getField());
        fieldPanel.setController(new GameSessionController(gameSession, this));
    }

    private void setUpGui() {
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(BorderLayout.WEST, flagsPanel);
        northPanel.add(BorderLayout.CENTER, smilePanel);
        northPanel.add(BorderLayout.EAST, timePanel);

        JPanel southPanel = new JPanel();
        southPanel.add(fieldPanel);
        southPanel.setBorder(new CompoundBorder(new LineBorder(Color.GRAY, 5), new LineBorder(Color.DARK_GRAY, 3)));

        frame.getContentPane().add(BorderLayout.NORTH, northPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, southPanel);
    }

    public void restart() {
        frame.getContentPane().removeAll();
        start();
    }

    public Field getFieldController() {
        return fieldPanel.getFieldController();
    }
}