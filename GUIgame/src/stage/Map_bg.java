package stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Map_bg {
    private BufferedImage image;

    public Map_bg() {
        try {
            image = ImageIO.read(new File("assets/backgrounds/deepsea.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage() {
        return image;
    }
}
