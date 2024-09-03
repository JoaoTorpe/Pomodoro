package Command;

import Main.PomodoroTimer;

public class StopCommand implements Command {

    @Override
    public void execute(PomodoroTimer timer) {
       timer.stop();
    }

   
    
}
