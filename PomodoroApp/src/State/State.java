package State;

import Main.PomodoroTimer;

public interface State {

    void executeState(PomodoroTimer timer);
    State nextState();
}
