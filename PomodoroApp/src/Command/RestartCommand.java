package Command;

import Main.PomodoroTimer;

public class RestartCommand implements Command{

    @Override
    public void execute(PomodoroTimer timer) {
       timer.restart(); 
    }

    
    
}
