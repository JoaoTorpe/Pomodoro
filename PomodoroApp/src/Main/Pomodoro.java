package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Command.Command;
import Command.ResetCommand;
import Command.StartCommand;
import Command.StopCommand;

public class Pomodoro {

    private PomodoroTimer timer;

    private Command start;
    private Command stop;
    private Command reset;

    private JButton startButton;
    private JButton resetButton;

    public Pomodoro(PomodoroTimer timer){

        this.timer = timer;
        this.startButton = timer.getStartButton();
        this.resetButton = timer.getResetButton();
        this.start = new StartCommand();
        this.stop = new StopCommand();
        this.reset = new ResetCommand();

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!timer.getStarted()) {
                    start();
                  timer.setStarted(true);
                    startButton.setText("PAUSE");
                } else {
                    stop();
                   timer.setStarted(false);
                    startButton.setText("START");
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
          
                    reset();
                
            }

        });


    }

    

    public void start(){
        start.execute(timer);
    }

    public void stop(){
        stop.execute(timer);
    }

    public void reset(){
        reset.execute(timer);
    }
    
}
