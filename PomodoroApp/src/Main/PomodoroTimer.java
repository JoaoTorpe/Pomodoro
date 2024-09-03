package Main;


import javax.swing.Timer;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class PomodoroTimer    {

private JFrame frame = new JFrame();
private JButton startButton = new JButton("START");
private JButton resetButton = new JButton("Reset");
private boolean started = false;


JLabel timeLabel = new JLabel();

int elapsedTime = 0;
int seconds = 0;
int minutes = 0;
int remainingTime = 5;

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
        remainingTime = 10;
        timer.restart();
        }
    }
});

public PomodoroTimer() {
    
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

public void start() {
    timer.start();
}

public void stop(){
    timer.stop();
}

public void reset(){
    remainingTime = 10;
    timer.restart();
}

public JButton getStartButton(){
    return startButton;
}

public JButton getResetButton(){
    return resetButton;
}

public void setStarted(boolean b){
this.started = b;
}

public boolean getStarted(){
    return this.started;
}



}