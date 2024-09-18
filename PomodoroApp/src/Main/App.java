package Main;


public class App {
    public static void main(String[] args) throws Exception {
      
        PomodoroTimer timer = new PomodoroTimer();
        Pomodoro pomodoro = new Pomodoro(timer);
    }
}
