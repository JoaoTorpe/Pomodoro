package Command;

import Main.PomodoroTimer;
import Main.TimeSettingsWindow;

public class ChangeTimeCommand implements Command {

    private TimeSettingsWindow settingsWindow;

    @Override
    public void execute(PomodoroTimer timer) {

        new TimeSettingsWindow(timer, timer.getFrame());
    }
}

