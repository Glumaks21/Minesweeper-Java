package graphic;

import logic.GameSession;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

public class SmilePanel extends JPanel implements Observer, ActionListener {
    private final GameSession gameSession;
    private final MainGui mainGui;
    private final JButton button;

    public SmilePanel(GameSession gameSession, MainGui mainGui) {
        this.gameSession = gameSession;
        this.mainGui = mainGui;
        gameSession.addObserver(this);
        button = new JButton();
        button.addActionListener(this);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        //button.set

        add(button);
        setBorder(new CompoundBorder(new LineBorder(Color.GRAY, 4), new LineBorder(Color.DARK_GRAY, 5)));
        update(null, "state");
    }


    @Override
    public void update(Observable o, Object arg) {
        String command = (String) arg;
        if (command.equals("state")) {
            String path = new File("").getAbsolutePath() + "\\resources\\smile_pictures";
            switch (gameSession.getState()) {
                case RUNNING:
                    setBackground(Color.LIGHT_GRAY);
                    path += "\\trololo.png";
                    break;
                case WIN:
                    setBackground(Color.BLACK);
                    path += "\\madface.jpg";
                    break;
                case LOSE:
                    setBackground(Color.BLACK);
                    path += "\\sadface.png";
                    break;
            }
            button.setIcon(new ImageIcon(path));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainGui.restart();
    }
}