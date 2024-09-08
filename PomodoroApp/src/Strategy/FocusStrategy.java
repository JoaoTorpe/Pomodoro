package Strategy;

import java.awt.Color; 
import Main.PomodoroTimer;

public class FocusStrategy implements Strategy {
int timeInSeconds = 10;

    @Override
    public void ExecuteStrategy(PomodoroTimer timer) {

      
        timer.setRemainingTime(timeInSeconds);
        timer.getFrame().getContentPane().setBackground(Color.red);
    }
    

    
}
