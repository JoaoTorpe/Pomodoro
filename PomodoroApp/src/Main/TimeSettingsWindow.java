package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Observer.Subscriber;

import javax.swing.*;

import Observer.PopUpService;
import Observer.SoundService;

public class TimeSettingsWindow extends JFrame {

    private PomodoroTimer timer;
    private JTextField focusTimeField;
    private JTextField breakTimeField;
    private JCheckBox soundCheckBox;
    private JCheckBox popupCheckBox;

    private Subscriber popUpService = PopUpService.getPopUpServiceInstance();
    private Subscriber audioService = SoundService.getSoundServiceInstance();

    public TimeSettingsWindow(PomodoroTimer timer, JFrame parentFrame) {
        this.timer = timer;
        setupUI();
        setLocationRelativeTo(parentFrame);
    }

    private void setupUI() {
        setTitle("Settings");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setResizable(false);

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

        soundCheckBox = new JCheckBox("Play sound");
        soundCheckBox.setBounds(20, 100, 100, 25);
        soundCheckBox.setSelected(timer.isSoundEnable()); // Define o estado inicial com base no timer
        add(soundCheckBox);

        popupCheckBox = new JCheckBox("Show popup");
        popupCheckBox.setBounds(140, 100, 120, 25);
        popupCheckBox.setSelected(timer.isPopupEnable()); // Define o estado inicial com base no timer
        add(popupCheckBox);

        JButton applyButton = new JButton("Apply");
        applyButton.setBounds(20, 140, 100, 30);
        add(applyButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(140, 140, 100, 30);
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

            // Configurações do som e do popup
            timer.setSoundEnabled(soundCheckBox.isSelected());
            timer.setPopupEnabled(popupCheckBox.isSelected());

          

            if(timer.isPopupEnable() ){
               timer.addSubscriber(popUpService);
            }
            else{
              timer.removeSubscriber(popUpService);
            }

            if(timer.isSoundEnable()){
               
               timer.addSubscriber(audioService);
            }

            else{
              
               timer.removeSubscriber(audioService);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.");
        }
    }
}
