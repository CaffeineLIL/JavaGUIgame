package player;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PlayerImage {
    private BufferedImage head_image;
    private BufferedImage body_image;
    private BufferedImage body_cropped;
    private BufferedImage image;
    private int imageX;
    private int imageY;

    public PlayerImage() {
        try {
            head_image = ImageIO.read(new File("assets/maidlilpa/lilpaface_sp.png"));
            body_image = ImageIO.read(new File("assets/maidlilpa/lilpadown_sp_strip10.png"));
            
            cropBodyImage();
            combineImages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 자른 body_image 부분을 image에 저장하고 반환합니다.
    public BufferedImage getImage() {
        return image;
    }

    // body_image의 일부를 잘라냅니다.
    private void cropBodyImage() {
        // 자를 부분의 시작 x, y 좌표를 설정
        imageX = 0;
        imageY = 0;
        // 자를 부분의 크기가 원본 이미지의 범위를 벗어나지 않도록 조절
        int width = Math.min(30, body_image.getWidth() - imageX);
        int height = Math.min(30, body_image.getHeight() - imageY);
        body_cropped = body_image.getSubimage(imageX, imageY, width, height);
    }

    // 두 이미지를 합성하여 새로운 이미지를 생성합니다.
    private void combineImages() {
        int combinedWidth = Math.max(head_image.getWidth(), body_cropped.getWidth());
        int combinedHeight = head_image.getHeight() + body_cropped.getHeight(); 
        image = new BufferedImage(combinedWidth, combinedHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = image.createGraphics();
        g2d.drawImage(head_image, 0, 0, null); // head_image를 (0, 0)에 그립니다.
        g2d.drawImage(body_cropped, 0, head_image.getHeight(), null); // body_cropped를 (0, head_image의 높이)에 그립니다.
        g2d.dispose(); // 그래픽 객체를 제거합니다.
    }

    public void moveInit() {
        imageX = 0;
        imageY = 0;
        int width = Math.min(30, body_image.getWidth() - imageX);
        int height = Math.min(30, body_image.getHeight() - imageY);
        body_cropped = body_image.getSubimage(imageX, imageY, width, height);
        combineImages();
    }

    // 아래로 움직일 경우 이미지를 변경합니다.
    public void moveDown() {
        // x, y 좌표를 증가시키고 자를 부분을 다시 설정합니다.
        imageX += 30;
        if (imageX >= body_image.getWidth()) {
            imageX = 0;
        }
        int width = Math.min(body_image.getWidth() - imageX , 30 );
        body_cropped = body_image.getSubimage(imageX, imageY, width, 30);
        combineImages(); // 이동 후 이미지를 다시 합성합니다.
    }
}
