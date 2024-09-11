package Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeSettingsWindow extends JFrame {

    private PomodoroTimer timer;
    private JTextField focusTimeField;
    private JTextField breakTimeField;

    public TimeSettingsWindow(PomodoroTimer timer) {
        this.timer = timer;
        setupUI();
    }

    private void setupUI() {
        setTitle("Settings");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel focusTimeLabel = new JLabel("Focus Time (minutes):");
        focusTimeLabel.setBounds(20, 20, 150, 25);
        add(focusTimeLabel);

        focusTimeField = new JTextField(String.valueOf(timer.getFocusTime() / 60));
        focusTimeField.setBounds(180, 20, 80, 25);
        add(focusTimeField);

        JLabel breakTimeLabel = new JLabel("Break Time (minutes):");
        breakTimeLabel.setBounds(20, 60, 150, 25);
        add(breakTimeLabel);

        breakTimeField = new JTextField(String.valueOf(timer.getBreakTime() / 60));
        breakTimeField.setBounds(180, 60, 80, 25);
        add(breakTimeField);

        JButton applyButton = new JButton("Apply");
        applyButton.setBounds(20, 100, 100, 30);
        add(applyButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(140, 100, 100, 30);
        add(cancelButton);

        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applySettings();
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    private void applySettings() {
        try {
            int focusMinutes = Integer.parseInt(focusTimeField.getText());
            int breakMinutes = Integer.parseInt(breakTimeField.getText());

            if (focusMinutes > 0) {
                timer.setFocusTime(focusMinutes * 60);
            }
            if (breakMinutes > 0) {
                timer.setBreakTime(breakMinutes * 60);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.");
        }
    }
}
