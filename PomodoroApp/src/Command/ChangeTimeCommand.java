package Command;

import Main.PomodoroTimer;
import Main.TimeSettingsWindow;

public class ChangeTimeCommand implements Command {

    private TimeSettingsWindow settingsWindow;

    @Override
    public void execute(PomodoroTimer timer) {
        // Abre a janela de configuração
        new TimeSettingsWindow(timer);
    }
}

