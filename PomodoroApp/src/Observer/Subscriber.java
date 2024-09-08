package Observer;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public interface Subscriber {

    public  void update() throws UnsupportedAudioFileException, IOException, LineUnavailableException;
    
}
