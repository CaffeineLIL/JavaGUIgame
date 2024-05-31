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
    private Rectangle hitbox;  // 변경: Rectangle 사용

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
        this.hitbox = new Rectangle(x, y, size, size); // Rectangle 초기화

        this.image = image;
    }

    public void update() {
        x += directionX * speed;
        y += directionY * speed;
        hitbox.setLocation(x, y);  // hitbox 위치 업데이트
    }

    public void draw(Graphics2D g) {
        if (active) {
            g.setColor(Color.cyan);
            g.fillRect((int)x, (int)y, size, size);

            //g.drawImage(image, x, y, size, size, null);
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

    public BufferedImage getImage() {
        return image;
    }

    public boolean checkCollision(Rectangle targetHitbox) {
        return hitbox.intersects(targetHitbox); // 변경: Rectangle을 사용하여 충돌 감지
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

	public Rectangle getHitbox() {
		// TODO Auto-generated method stub
		return hitbox;
	}
}
