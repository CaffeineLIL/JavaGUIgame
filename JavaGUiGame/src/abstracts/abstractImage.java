package abstracts;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class abstractImage extends JPanel {
    protected BufferedImage img;
    protected Map<String, BufferedImage> images = new HashMap<>();

    public abstractImage(String file) {
        try {
            img = ImageIO.read(new File(file));
            cropImages();
            combineImages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage(String key) {
        return images.get(key);
    }

    public abstract void cropImages();

    public abstract void combineImages();
    
    
}
