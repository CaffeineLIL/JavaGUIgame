package Enemy;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WeakEnemyImage {
    private BufferedImage body_image;
    private BufferedImage body_cropped;
    private BufferedImage image;
    private int imageX;
    private int imageY;
    private int imgindex = 0;
    private final int[] changeImg = {0, 30, 60};

    public WeakEnemyImage() {
        try {
            body_image = ImageIO.read(new File("assets/monster/enemy1/cutty.png"));
            cropBodyImage();
            combineImages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 자른 body_image 부분을 image에 저장하고 반환합니다.
    public BufferedImage getImage() {
        return scaleImage(image, 100, 100); // 필요한 크기로 조정
    }

    // body_image의 일부를 잘라냅니다.
    private void cropBodyImage() {
        // 자를 부분의 크기가 원본 이미지의 범위를 벗어나지 않도록 조절
        int width = Math.min(30, body_image.getWidth() - imageX);
        int height = Math.min(30, body_image.getHeight() - imageY);
        body_cropped = body_image.getSubimage(imageX, imageY, width, height);
    }

    // 두 이미지를 합성하여 새로운 이미지를 생성합니다.
    private void combineImages() {
        int combinedWidth = body_cropped.getWidth();
        int combinedHeight = body_cropped.getHeight(); 
        image = new BufferedImage(combinedWidth, combinedHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = image.createGraphics();
        g2d.drawImage(body_cropped, 0, 0, null); // body_cropped를 (0, 0)에 그립니다.
        g2d.dispose(); // 그래픽 객체를 제거합니다.
    }

    // 이미지 크기를 조정하는 메서드
    private BufferedImage scaleImage(BufferedImage originalImage, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = scaledImage.createGraphics();
        g2d.drawImage(originalImage, 0, 0, width, height, null);
        g2d.dispose();
        return scaledImage;
    }

    // 움직일 경우 이미지를 변경합니다.
    public void move() {
        // x 좌표를 증가시키고 자를 부분을 다시 설정합니다.
        imageX = changeImg[imgindex++];
        
        if(imgindex >= changeImg.length) {
            imgindex = 0;
        }
        
        cropBodyImage();
        combineImages(); // 이동 후 이미지를 다시 합성합니다.
    }
}
