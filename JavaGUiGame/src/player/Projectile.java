package player;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Projectile {
    private int x, y;
    private double directionX, directionY;
    private double speed;
    private int size;
    private boolean active;
    private BufferedImage image;
    private BufferedImage bulletImage;
    private ArrayList<BufferedImage> changeBullet = new ArrayList<>();

    public Projectile(int x, int y, double directionX, double directionY, double speed, int size) {
        this.x = x;
        this.y = y;
        this.directionX = directionX;
        this.directionY = directionY;
        this.speed = speed;
        this.size = size;
        this.active = true;
        
        this.image = image;
    }

    public void update() {
        x += directionX * speed;
        y += directionY * speed;
    }

    public void draw(Graphics2D g) {
        if (active) {
            g.setColor(Color.cyan);
            g.fillRect((int)x, (int)y, size, size);
            
            //g.drawImage(image, x, y,size,size, null);
        }
    }
    
    public void setImage() {
        try {
        	bulletImage = ImageIO.read(new File("assets/effect/불타는눈물_2-Sheet.png"));
            cropImages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
 // body_image의 일부를 잘라냅니다.
    private void cropImages() {
        int imgcutX = 0;
        for (int i = 0; i < 9; i++) {
            int width = Math.min(30, bulletImage.getWidth() - imgcutX);
          
            int height = Math.min(30, bulletImage.getHeight() - 30);

            image = bulletImage.getSubimage(imgcutX, 30, width, height);
            changeBullet.add(image);
            imgcutX = imgcutX + 32;
        }
    }
    
    // 자른 body_image 부분을 image에 저장하고 반환합니다.
    public BufferedImage getImage() {
        return image;
    }

    public boolean checkCollision(Rectangle target) {
        Rectangle projectileRect = new Rectangle((int)x, (int)y, size, size);
        return projectileRect.intersects(target);
    }

    public void deactivate() {
        active = false;
    }

    public boolean isActive() {
        return active;
    }

	public int getX() {
		return this.x;
	}
}
