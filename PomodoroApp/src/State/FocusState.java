package State;

import Main.PomodoroTimer;

import java.awt.*;

public class FocusState implements State {

    @Override
    public void executeState(PomodoroTimer timer) {
        timer.setRemainingTime(timer.getFocusTime());
        timer.getFrame().getContentPane().setBackground(Color.decode("#e86666"));
    }

    @Override
    public State nextState() {
        return new BreakState();
    }
}
