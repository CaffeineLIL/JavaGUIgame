package status_basis;

import java.awt.Graphics;

import abstracts.abstractImage;

public class UI extends abstractImage {

    public UI() {
        super("assets/UI/UI_Sprite-0001.png");
    }

    @Override
    public void cropImages() {
        final int width = 50;
        final int height = 40;
        
        images.put("full_hp", img.getSubimage(0, 73, width, height));
        images.put("bomb", img.getSubimage(130, 90, width, height));
        images.put("half_hp", img.getSubimage(150, 100, width, height));
        // 필요에 따라 추가 이미지 잘라내기
    }

    @Override
    public void combineImages() {
        // 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // "hp" 이미지 그리기
        g.drawImage(images.get("full_hp"), 100, 10, this);
        g.drawImage(images.get("half_hp"), 100, 50, this);
        // "bomb" 이미지 그리기
        g.drawImage(images.get("bomb"), 100, 100, this);
      
        
    }
}
