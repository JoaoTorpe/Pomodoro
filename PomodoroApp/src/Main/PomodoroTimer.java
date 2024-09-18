package Main;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;

import Observer.*;
import State.FocusState;
import State.State;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class PomodoroTimer {

    public static int cycleCount = 1;

    private State state;
    private List<Subscriber> subscribers;
    private JFrame frame = new JFrame();
    private JButton startButton = new JButton("Start");
    private JButton resetButton = new JButton("Reset");
    private JButton settingsButton = new JButton("Settings");

    private boolean started = false;
    private boolean paused = false;

    private boolean soundEnable;
    private boolean popupEnable; 
    
    public boolean isSoundEnable() {
        return soundEnable;
    }

    public boolean isPopupEnable() {
        return popupEnable;
    }
   

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
        frame.setSize(400, 300);
        frame.setLayout(null);

        // Estilo do frame
        frame.getContentPane().setBackground(new Color(250, 250, 250));

        

        // Estilo do JLabel (tempo)
        timeLabel.setBounds(150, 50, 100, 50);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 36));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setForeground(new Color(50, 50, 50));
        timeLabel.setText(minutes_string + ":" + seconds_string);

        // Estilo dos botões
        startButton.setBounds(100, 120, 100, 50);
        startButton.setFont(new Font("Arial", Font.BOLD, 14));
        startButton.setBackground(new Color(0, 150, 136));
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);
        startButton.setBorder(BorderFactory.createEmptyBorder());

        resetButton.setBounds(200, 120, 100, 50);
        resetButton.setFont(new Font("Arial", Font.BOLD, 14));
        resetButton.setBackground(Color.decode("#7e0de0"));
        resetButton.setForeground(Color.WHITE);
        resetButton.setFocusPainted(false);
        resetButton.setBorder(BorderFactory.createEmptyBorder());

        settingsButton.setBounds(100, 180, 200, 50);
        settingsButton.setFont(new Font("Arial", Font.BOLD, 14));
        settingsButton.setBackground(new Color(33, 150, 243));
        settingsButton.setForeground(Color.WHITE);
        settingsButton.setFocusPainted(false);
        settingsButton.setBorder(BorderFactory.createEmptyBorder());

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
                    } catch (UnsupportedAudioFileException e) {
                        
                        e.printStackTrace();
                    } catch (IOException e) {
                        
                        e.printStackTrace();
                    } catch (LineUnavailableException e) {
                        
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
        cycleCount = 1;
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
        cycleCount ++;
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
       if(!subscribers.contains(s)){
        subscribers.add(s);
       }

    }

    public void removeSubscriber(Subscriber s) {
     
       if(subscribers.contains(s)){
        subscribers.remove(s);
       }
        
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

    public void setSoundEnabled(boolean selected) {
        this.soundEnable = selected;
    }

    public void setPopupEnabled(boolean selected) {
        this.popupEnable = selected;
    }

}
