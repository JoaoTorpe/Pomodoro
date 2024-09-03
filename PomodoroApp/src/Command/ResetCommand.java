package Command;

import Main.PomodoroTimer;

public class ResetCommand implements Command {

    @Override
    public void execute(PomodoroTimer timer) {
       timer.reset();
    }

}
