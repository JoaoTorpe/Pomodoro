package Observer;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

import Main.PomodoroTimer;

public class PopUpService implements Subscriber {
    private static PopUpService popService = null;
   
    public static PopUpService getPopUpServiceInstance(){
        if(popService != null){
            return popService;
        }
        popService = new PopUpService();
        return popService;
    }

    @Override
    public void update() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if( PomodoroTimer.cycleCount !=0 && PomodoroTimer.cycleCount %2 ==0){
        JOptionPane.showMessageDialog(null, "Parabéns, você completou um Pomodoro !");
        }
    }
    
}
