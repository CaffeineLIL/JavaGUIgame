package status_basis;

import java.awt.*;
import javax.swing.*;
import abstracts.abstractImage;

public class UI extends abstractImage {
    JPanel uiPanel, TopPanel, WestPanel; 
    JLabel Fullhp_1, Fullhp_2, Fullhp_3;

    public UI() {
        super("assets/UI/UI_Sprite-0001.png");
        cropImages();
        initUI();
    }

    public void initUI() {
        // 메인 UI 패널 생성
        uiPanel = new JPanel();
        uiPanel.setLayout(new BorderLayout());
       
        
        // 상단에 넣을 패널 생성
        TopPanel = new JPanel();
        TopPanel.setLayout(new BorderLayout());
        
     	// 상단 좌측에 넣을 패널 생성
        WestPanel = new JPanel();
        WestPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 100, 0));


        
        Fullhp_1 = new JLabel(new ImageIcon(images.get("full_hp")));
        Fullhp_2 = new JLabel(new ImageIcon(images.get("full_hp")));
        Fullhp_3 = new JLabel(new ImageIcon(images.get("full_hp")));

        WestPanel.add(Fullhp_1);
        WestPanel.add(Fullhp_2);
        WestPanel.add(Fullhp_3);
        
        TopPanel.add(WestPanel, BorderLayout.WEST);
        
        
        // UI 패널에 상단 좌측 패널 추가
        uiPanel.add(TopPanel, BorderLayout.NORTH);
        
        //설정한 Panel add하기
        add(uiPanel);
        
    }

    @Override
    public void cropImages() {
        int width = 50;
        int height = 40;
        
        // 최대 x값 : 160 , 최대 y값 : 170 
        // HP 그림 불러오기
        images.put("full_hp", img.getSubimage(0, 74, width, height));
        images.put("empty_hp", img.getSubimage(30, 114, 40, 30));
        images.put("halfLeft_hp", img.getSubimage(77, 110, 23, 30));
        images.put("halfRight_hp", img.getSubimage(90, 115, 23, 30));
        
        // 숫자 이미지 불러오기
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

   /*@Override
    protected void paintComponent(Graphics g) {
        // "hp" 이미지 그리기
        g.drawImage(images.get("full_hp"), 100, 10, this);
        g.drawImage(images.get("empty_hp"), 110, 30, this);
        g.drawImage(images.get("halfLeft_hp"), 120, 30, this);
        g.drawImage(images.get("halfRight_hp"), 130, 40, this);
        g.drawImage(images.get("2"), 130, 100, this);
        g.drawImage(images.get("0"), 170, 100, this);
    }
    */
}
