package Command;

import Main.PomodoroTimer;

public class StartCommand implements Command {

    @Override
    public void execute(PomodoroTimer timer) {
       timer.start();
    }

   
    
}
