package player;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PlayerImage {
    private BufferedImage head_image;
    private BufferedImage body_image;
    private BufferedImage image;
    BufferedImage body_cropped;
    
    private int imageX = 0;
    private int imageY;
    private int imgindex = 0;
    
    private ArrayList<BufferedImage> changebody = new ArrayList<>();
    
    private BufferedImage secondBufferimg;
    private Graphics2D secondBufferG2d;

    public PlayerImage() {
        try {
            head_image = ImageIO.read(new File("assets/maidlilpa/lilpaface_sp.png"));
            body_image = ImageIO.read(new File("assets/maidlilpa/lilpadown_sp_strip10.png"));
            
            if(changebody.isEmpty()) cropBodyImage();
            
            combineImages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 자른 body_image 부분을 image에 저장하고 반환합니다.
    public BufferedImage getImage() {
        return image;
    }

    // 더블 버퍼링을 사용하여 이미지를 그리고 화면에 출력합니다.
    public void paint(Graphics g) {
        if (secondBufferimg == null) {
            secondBufferimg = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
            secondBufferG2d = secondBufferimg.createGraphics();
        }
        update(secondBufferG2d);
        g.drawImage(secondBufferimg, 0, 0, null);
    }

    public void update(Graphics g) {
        secondBufferimg = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
        secondBufferG2d = secondBufferimg.createGraphics();
        secondBufferG2d.drawImage(image, 0, 0, 50, 50, 0, 30, 30, 30, (ImageObserver) this);
    }

    // body_image의 일부를 잘라냅니다.
    private void cropBodyImage() {
        int imgcutX = 0;
        for (int i = 0; i < 9; i++) {
            int width = Math.min(30, body_image.getWidth() - imgcutX);
          
            int height = Math.min(30, body_image.getHeight() - imageY);

            body_cropped = body_image.getSubimage(imgcutX, imageY, width, height);
            changebody.add(body_cropped);
            imgcutX = imgcutX + 32;
        }
    }
    
    public void moveInit() {
        imageX = 0;
        imageY = 0;
        imgindex = 0;
        if(changebody.isEmpty()) cropBodyImage();
        combineImages();
    }

    // 아래로 움직일 경우 이미지를 변경합니다.
    public void moveDown() {
        //changebody 리스트에서 이미지를 순환합니다.
        imgindex = (imgindex + 1) % changebody.size();
        combineImages(); // 이동 후 이미지를 다시 합성합니다.
    }

    // 두 이미지를 합성하여 새로운 이미지를 생성합니다.
    private void combineImages() {
        BufferedImage body_cropped = changebody.get(imgindex);
        int combinedWidth = head_image.getWidth();
        int combinedHeight = head_image.getHeight() + body_cropped.getHeight();
        image = new BufferedImage(combinedWidth, combinedHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = image.createGraphics();
        g2d.drawImage(head_image, 0, 0, null); // head_image를 (0, 0)에 그립니다.
        g2d.drawImage(body_cropped, 10, 60, null); // body_cropped를 (10, 60 의 높이)에 그립니다.
        g2d.dispose(); // 그래픽 객체를 제거합니다.

        // 이미지 크기를 조정합니다.
        image = scaleImage(image, 130, 190); // 원하는 크기로 변경
    }

    // 이미지 크기를 조정하는 메서드
    private BufferedImage scaleImage(BufferedImage originalImage, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = scaledImage.createGraphics();
        g2d.drawImage(originalImage, 0, 0, width, height, null);
        g2d.dispose();
        return scaledImage;
    }

  
}
