package Strategy;

import Main.PomodoroTimer;
import java.awt.Color; 

public class BreakStrategy implements Strategy{
int timeInSeconds = 5;
    @Override
    public void ExecuteStrategy(PomodoroTimer timer) {

        timer.setRemainingTime(timeInSeconds);
        timer.getFrame().getContentPane().setBackground(Color.green);
    }

    
    
}
