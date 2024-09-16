package Main;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.util.*;

import Observer.*;
import State.FocusState;
import State.State;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class PomodoroTimer {

    private State state;
    private List<Subscriber> subscribers;
    private JFrame frame = new JFrame();
    private JButton startButton = new JButton("Start");
    private JButton resetButton = new JButton("Reset");
    private JButton settingsButton = new JButton("Settings");

    private boolean started = false;
    private boolean paused = false;

    private boolean soundEnabled = false;
    private boolean popupEnabled = false;

    JLabel timeLabel = new JLabel();

    int seconds = 0;
    int minutes = 0;
    int remainingTime;

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
                timer.stop(); // Para o timer atual
                toggleState(); // Alterna o estado do Pomodoro
                timer.start(); // Inicia o timer novamente com o novo estado
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

        if (soundEnabled) {
            for (Subscriber s : subscribers) {
                try {
                    s.update();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            playSound();
        }

        if (popupEnabled) {
            showPopup();
        }
    }

    private void playSound() {
        try {
            new SoundService().update();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    private void showPopup() {
        JOptionPane.showMessageDialog(frame, "O tempo acabou!");
    }

    // Getters e Setters para as novas configurações
    public boolean isSoundEnabled() {
        return soundEnabled;
    }

    public void setSoundEnabled(boolean soundEnabled) {
        this.soundEnabled = soundEnabled;
    }

    public boolean isPopupEnabled() {
        return popupEnabled;
    }

    public void setPopupEnabled(boolean popupEnabled) {
        this.popupEnabled = popupEnabled;
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
