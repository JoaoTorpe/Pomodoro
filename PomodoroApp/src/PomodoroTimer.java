import javax.swing.Timer;
import java.awt.*;

import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class PomodoroTimer    {

JFrame frame = new JFrame();
JButton startButton = new JButton("START");
JButton resetButton = new JButton("RESET");
JLabel timeLabel = new JLabel();
int totalTimeInSeconds = 120;
int elapsedTime = 0;
int seconds = 0;
int minutes = 0;
boolean started = false;
String seconds_string = String.format("%02d", seconds);
String minutes_string = String.format("%02d", minutes);

Timer timer = new Timer(1000, new ActionListener() {
    int remainingTime = totalTimeInSeconds;

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
            System.out.println("Tempo esgotado!");
            ((Timer) e.getSource()).stop(); // Para o timer
        }
    }
});

public PomodoroTimer() {
    // Configuração da janela
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300, 200);
    frame.setLayout(null);

    // Configuração dos componentes
    timeLabel.setBounds(100, 50, 100, 50);
    timeLabel.setText(minutes_string + ":" + seconds_string);
    startButton.setBounds(50, 120, 100, 50);
    resetButton.setBounds(150, 120, 100, 50);

    // Adicionando componentes ao frame
    frame.add(timeLabel);
    frame.add(startButton);
    frame.add(resetButton);

    // Ação do botão START
    startButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!started) {
                start();
                started = true;
                startButton.setText("PAUSE");
            } else {
                timer.stop();
                started = false;
                startButton.setText("START");
            }
        }
    });

    // Ação do botão RESET
    resetButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            timer.stop();
            started = false;
      
            seconds_string = String.format("%02d", totalTimeInSeconds % 60);
            minutes_string = String.format("%02d", totalTimeInSeconds / 60);
            timeLabel.setText(minutes_string + ":" + seconds_string);
            startButton.setText("START");
        }
    });

    frame.setVisible(true);
}

public void start() {
    timer.start();
}

public static void main(String[] args) {
    new PomodoroTimer();
}
}