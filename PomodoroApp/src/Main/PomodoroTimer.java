package Main;


import javax.swing.Timer;

import Strategy.BreakStrategy;
import Strategy.FocusStrategy;
import Strategy.Strategy;

import java.awt.event.*;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.*;
import Observer.*;


public class PomodoroTimer    {

private Strategy strategy;
private List<Subscriber> subscribers;
private JFrame frame = new JFrame();
private JButton startButton = new JButton("START");
private JButton resetButton = new JButton("Reset");
private boolean started = false;
private boolean paused = false;

JLabel timeLabel = new JLabel();

int elapsedTime = 0;
int seconds = 0;
int minutes = 0;
int remainingTime;

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
             toggleStrategies();
        timer.restart();
        }
    }
});

public PomodoroTimer() {
   subscribers = new ArrayList<>();
    strategy = new FocusStrategy();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300, 200);
    frame.setLayout(null);

 
    timeLabel.setBounds(100, 50, 100, 50);
    timeLabel.setText(minutes_string + ":" + seconds_string);
    startButton.setBounds(50, 120, 100, 50);
    resetButton.setBounds(150, 120, 100, 50);

    frame.add(timeLabel);
    frame.add(startButton);
    frame.add(resetButton);

   
    frame.setVisible(true);
}

public void notifySubscribers() {

    for(Subscriber s : subscribers){
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
    strategy.ExecuteStrategy(this);
    timer.start();
}

public void stop(){
    timer.stop();
}

public void reset(){
    if(paused){
        paused = false;
        started = true;
        startButton.setText("PAUSE");
    }
    strategy = new FocusStrategy();
    strategy.ExecuteStrategy(this);
    timer.restart();
}

public void restart(){

    timer.start();
}

public JButton getStartButton(){
    return startButton;
}

public JButton getResetButton(){
    return resetButton;
}

public JFrame getFrame(){
    return this.frame;
}

public void setRemainingTime(int remainingTime){
    this.remainingTime = remainingTime;
}

public void toggleStrategies( ){
        
    notifySubscribers();
   

    if(this.strategy instanceof FocusStrategy){
        strategy = new BreakStrategy();
    }
    else if(this.strategy instanceof BreakStrategy){
        strategy = new FocusStrategy();
    }

    strategy.ExecuteStrategy(this);

}

public void addSubscriber(Subscriber s){
    subscribers.add(s);
}

public void setStarted(boolean b){
this.started = b;
}

public boolean getStarted(){
    return this.started;
}

public void setPaused(boolean b){
    this.paused = b;
    }
    
    public boolean getPaused(){
        return this.paused;
    }


}