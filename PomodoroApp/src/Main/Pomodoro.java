package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import Command.Command;
import Command.ResetCommand;
import Command.RestartCommand;
import Command.StartCommand;
import Command.StopCommand;
import Command.ChangeTimeCommand;

public class Pomodoro {

    private PomodoroTimer timer;

    private Command start;
    private Command stop;
    private Command reset;
    private Command restart;
    private Command changeTime;

    private JButton startButton;
    private JButton resetButton;
    private JButton settingsButton;

    public Pomodoro(PomodoroTimer timer) {
        this.timer = timer;
        this.startButton = timer.getStartButton();
        this.resetButton = timer.getResetButton();
        this.settingsButton = timer.getSettingsButton();

        this.start = new StartCommand();
        this.stop = new StopCommand();
        this.reset = new ResetCommand();
        this.restart = new RestartCommand();
        this.changeTime = new ChangeTimeCommand();

        // ActionListener para o botão "Start"
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timer.getPaused()) {
                    restart();
                    timer.setPaused(false);
                    timer.setStarted(true);
                    startButton.setText("Pause");
                } else {
                    if (!timer.getStarted()) {
                        start();
                        timer.setStarted(true);
                        startButton.setText("Pause");
                    } else {
                        stop();
                        timer.setStarted(false);
                        timer.setPaused(true);
                        startButton.setText("Start");
                    }
                }
            }
        });

        // ActionListener para o botão "Reset"
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

        // ActionListener para o botão "Settings"
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeTime();
            }
        });
    }

    public void start() {
        start.execute(timer);
    }

    public void stop() {
        stop.execute(timer);
    }

    public void reset() {
        reset.execute(timer);
    }

    public void restart() {
        restart.execute(timer);
    }

    public void changeTime() {
        changeTime.execute(timer);
    }
}
