package status_basis;

import java.awt.Graphics;

import abstracts.abstractImage;

public class UI extends abstractImage {

    public UI() {
        super("assets/UI/UI_Sprite-0001.png");
    }

    @Override
    public void cropImages() {
        int width = 50;
        int height = 40;
        
        //최대 x값 : 160 , 최대 y값 : 170 
        //HP 그림 불러오기
        images.put("full_hp", img.getSubimage(0, 74, width, height));
        images.put("empty_hp", img.getSubimage(30, 114, 40, 30));
        images.put("halfLeft_hp", img.getSubimage(77, 110, 23, 30));
        images.put("halfRight_hp", img.getSubimage(90, 115, 23, 30));
        
        //숫자 이미지 불러오기
        images.put("1", img.getSubimage(0, 180, 42, 30));
        images.put("2", img.getSubimage(40, 180, 18, 30));
        images.put("3", img.getSubimage(56, 180, 18, 30));
        images.put("4", img.getSubimage(71, 180, 18, 30));
        images.put("5", img.getSubimage(86, 180, 18, 30));
        images.put("6", img.getSubimage(101, 180, 18, 30));
        images.put("7", img.getSubimage(116, 180, 18, 30));
        images.put("8", img.getSubimage(131, 180, 18, 30));
        images.put("9", img.getSubimage(146, 180, 18, 30));
        images.put("0", img.getSubimage(162, 185, 16, 20));
        // 필요에 따라 추가 이미지 잘라내기
    }

    @Override
    public void combineImages() {
        // 
    }

    @Override
    protected void paintComponent(Graphics g) {
       
        // "hp" 이미지 그리기
        g.drawImage(images.get("full_hp"), 100, 10, this);
        g.drawImage(images.get("empty_hp"), 110, 30, this);
        g.drawImage(images.get("halfLeft_hp"), 120, 30, this);
        g.drawImage(images.get("halfRight_hp"), 130, 40, this);
        g.drawImage(images.get("2"), 130, 100, this);
        g.drawImage(images.get("0"), 170, 100, this);
       
        
    }
}
