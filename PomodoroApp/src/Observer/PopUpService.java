package Observer;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

public class PopUpService implements Subscriber {
    private static PopUpService popService = null;
   
    public static PopUpService getPopUpServiceInstance(){
        if(popService != null){
            return popService;
        }
        popService = new PopUpService();
        return popService;
    }

    @Override
    public void update() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        JOptionPane.showMessageDialog(null, "Modo alterado !");
    }
    
}
