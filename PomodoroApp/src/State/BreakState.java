package State;

import Main.PomodoroTimer;

import java.awt.*;

public class BreakState implements State {

    @Override
    public void executeState(PomodoroTimer timer) {
        timer.setRemainingTime(timer.getBreakTime());
        timer.getFrame().getContentPane().setBackground(Color.green);
    }

    @Override
    public State nextState() {
        return new FocusState();
    }
}
