package mk.game_of_life.view;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
@Setter
public class MainMenu extends JPanel {

    private JSpinner msDelay;
    private JButton startGameButton;
    private JButton stopGameButton;
    public MainMenu() {
        setBackground(Color.DARK_GRAY);
        FlowLayout flowLayout = new FlowLayout();
        setLayout(flowLayout);

        msDelay = new JSpinner(new SpinnerNumberModel(10, 0, 10000, 1));
        startGameButton = new JButton("Start");
        stopGameButton = new JButton("Stop");

        add(msDelay);
        add(startGameButton);
        add(stopGameButton);

        // ms delay on change event
        msDelay.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                MainFrame.getInstance().getGamePanel().setMillisecondsDelay((Integer) msDelay.getValue());
            }
        });

        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingWorker<Void, Void> swingWorker = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() {
                        MainFrame.getInstance().getGamePanel().startGame();
                        return null;
                    }
                };

                swingWorker.execute();
            }
        });

        stopGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.getInstance().getGamePanel().setGameRunning(false);
                System.out.println("stopped");
            }
        });

    }
}
