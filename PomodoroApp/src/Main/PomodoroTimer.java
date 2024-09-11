package Main;

import javax.swing.Timer;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.*;
import Observer.*;
import State.FocusState;
import State.State;

public class PomodoroTimer {

    private State state;
    private List<Subscriber> subscribers;
    private JFrame frame = new JFrame();
    private JButton startButton = new JButton("Start");
    private JButton resetButton = new JButton("Reset");
    private JButton settingsButton = new JButton("Settings");

    private boolean started = false;
    private boolean paused = false;

    JLabel timeLabel = new JLabel();

    int seconds = 0;
    int minutes = 0;
    int remainingTime;

//    int focusTimeInSeconds = 25 * 60; // Tempo padrão de foco (25 minutos)
//    int breakTimeInSeconds = 5 * 60;  // Tempo padrão de pausa (5 minutos)

    int focusTimeInSeconds = 10;
    int breakTimeInSeconds = 5;

    String seconds_string = String.format("%02d", seconds);
    String minutes_string = String.format("%02d", minutes);

    Timer timer = new Timer(1000, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (remainingTime >= 0) {
                minutes = remainingTime / 60;
                seconds = remainingTime % 60;
                seconds_string = String.format("%02d", seconds);
                minutes_string = String.format("%02d", minutes);
                timeLabel.setText(minutes_string + ":" + seconds_string);
                remainingTime--;
            } else {
                toggleState();
                timer.restart();
            }
        }
    });

    public PomodoroTimer() {
        subscribers = new ArrayList<>();
        state = new FocusState(); // Estado inicial

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(null);

        timeLabel.setBounds(100, 50, 100, 50);
        timeLabel.setText(minutes_string + ":" + seconds_string);
        startButton.setBounds(50, 120, 100, 50);
        resetButton.setBounds(150, 120, 100, 50);
        settingsButton.setBounds(50, 180, 200, 50);

        frame.add(timeLabel);
        frame.add(startButton);
        frame.add(resetButton);
        frame.add(settingsButton);
        frame.setVisible(true);
    }

    public void notifySubscribers() {
        for (Subscriber s : subscribers) {
            try {
                s.update();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        state.executeState(this);
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void reset() {
        if (paused) {
            paused = false;
            started = true;
            startButton.setText("PAUSE");
        }
        state = new FocusState();
        state.executeState(this);
        timer.restart();
    }

    public void restart() {
        timer.start();
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public void toggleState() {
        notifySubscribers();
        state = state.nextState();
        state.executeState(this);
    }

    public int getFocusTime() {
        return focusTimeInSeconds;
    }

    public void setFocusTime(int focusTimeInSeconds) {
        this.focusTimeInSeconds = focusTimeInSeconds;
    }

    public int getBreakTime() {
        return breakTimeInSeconds;
    }

    public void setBreakTime(int breakTimeInSeconds) {
        this.breakTimeInSeconds = breakTimeInSeconds;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public JButton getSettingsButton() {
        return settingsButton;
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public void addSubscriber(Subscriber s) {
        subscribers.add(s);
    }

    public void setStarted(boolean b) {
        this.started = b;
    }

    public boolean getStarted() {
        return this.started;
    }

    public void setPaused(boolean b) {
        this.paused = b;
    }

    public boolean getPaused() {
        return this.paused;
    }
}
