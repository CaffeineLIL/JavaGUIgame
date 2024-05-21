package player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PlayerImage {
    private BufferedImage image;

    public PlayerImage() {
        try {
            image = ImageIO.read(new File("assets/maidlilpa/lilpaface_sp.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage() {
        return image;
    }
}
