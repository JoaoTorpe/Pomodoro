package Observer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

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

    
       
    @Override
    public void update() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException {
        try {

          try (InputStream audioSrc = getClass().getResourceAsStream("/Observer/Sounds/notification-sound.wav")) {
            if (audioSrc == null) {
                throw new IOException("Arquivo de som não encontrado.");
            }
        
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioSrc);

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
        }
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }

    }
}
