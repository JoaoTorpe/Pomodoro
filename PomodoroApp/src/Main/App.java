package Main;

import Observer.SoundService;
import Observer.Subscriber;

public class App {
    public static void main(String[] args) throws Exception {
      
        PomodoroTimer timer = new PomodoroTimer();
        Subscriber audio = new SoundService();
        timer.addSubscriber(audio);
        Pomodoro pomodoro = new Pomodoro(timer);
    }
}
