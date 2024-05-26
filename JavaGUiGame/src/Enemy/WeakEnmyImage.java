package Enemy;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WeakEnmyImage {
    private BufferedImage image;

    public WeakEnmyImage() {
        try {
            image = ImageIO.read(new File("assets/maidlilpa/lilpaface_sp.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    	
    
    //스프라이트 기법 활용하기
    
    public BufferedImage getImage() {
        return image;
    }
}
