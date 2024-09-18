package Observer;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundService implements Subscriber {

    private static SoundService soundService = null;
   
    public static SoundService getSoundServiceInstance(){
        if(soundService != null){
            return soundService;
        }
        soundService = new SoundService();
        return soundService;
    }

    
    private String filePath = new File("PomodoroApp/src/Observer/Sounds/notification-sound.wav").getAbsolutePath();

    
    @Override
    public void update() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException {
        try {

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

            Clip audioClip = AudioSystem.getClip();

            audioClip.open(audioStream);
            audioClip.start();

            audioClip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    audioClip.close();
                    try {
                        audioStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }

    }
}
