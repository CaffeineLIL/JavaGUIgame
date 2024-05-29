package status_basis;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import abstracts.abstractImage;

public class UI extends abstractImage {
    JPanel uiPanel, TopPanel, hpPanel; 
    JLabel[] hpLabels;

    public UI(int hp) {
        super("assets/UI/UI_Sprite-0001.png");
        initUI(hp);
    }

    public void initUI(int hp) {
        // 메인 UI 패널 생성
        uiPanel = new JPanel()   /* {
         @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                // 배경색을 완전히 투명하게 설정
                g2d.setComposite(AlphaComposite.SrcOver.derive(0.0f));
                g2d.setColor(new Color(0, 0, 0, 0)); // 완전히 투명한 색상
                g2d.drawRect(0, 0, getWidth(), getHeight());
                g2d.dispose();
            }
        }*/
        ;
        uiPanel.setLayout(new BorderLayout());
        uiPanel.setOpaque(false);

        // 상단에 넣을 패널 생성
        TopPanel = new JPanel() /* {
           @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                // 배경색을 완전히 투명하게 설정
                g2d.setComposite(AlphaComposite.SrcOver.derive(0.0f));
                g2d.setColor(new Color(0, 0, 0, 0)); // 완전히 투명한 색상
                g2d.drawRect(0, 0, getWidth(), getHeight());
                g2d.dispose();
            }
        }*/
        ;
        TopPanel.setLayout(new BorderLayout());
        TopPanel.setOpaque(false);

        // hp 표시할 패널 생성
        hpPanel = new JPanel();
        hpPanel.setOpaque(false);

        // HP 레이블 생성
        hpLabels = new JLabel[4];
        for (int i = 0; i < hpLabels.length; i++) {
            hpLabels[i] = new JLabel(new ImageIcon(images.get("full_hp")));
            hpPanel.add(hpLabels[i]);
        }

        // HP에 따라 빈 하트 표시
        for (int i = hp; i < hpLabels.length; i++) {
            hpLabels[i].setIcon(new ImageIcon(images.get("empty_hp")));
        }

        // TopPanel에 hpPanel을 추가
        TopPanel.add(hpPanel, BorderLayout.WEST);

        // uiPanel에 TopPanel을 추가
        uiPanel.add(TopPanel, BorderLayout.WEST);

        // uiPanel을 프레임에 추가
        add(uiPanel);
    }

    @Override
    public void cropImages() {
        int width = 50;
        int height = 40;
        
        images.put("full_hp", createTransparentImage(img.getSubimage(0, 74, width, height)));
        images.put("empty_hp", createTransparentImage(img.getSubimage(30, 114, 40, 30)));
        images.put("halfLeft_hp", createTransparentImage(img.getSubimage(77, 110, 23, 30)));
        images.put("halfRight_hp", createTransparentImage(img.getSubimage(90, 115, 23, 30)));

        images.put("1", createTransparentImage(img.getSubimage(0, 180, 42, 30)));
        images.put("2", createTransparentImage(img.getSubimage(40, 180, 18, 30)));
        images.put("3", createTransparentImage(img.getSubimage(56, 180, 18, 30)));
        images.put("4", createTransparentImage(img.getSubimage(71, 180, 18, 30)));
        images.put("5", createTransparentImage(img.getSubimage(86, 180, 18, 30)));
        images.put("6", createTransparentImage(img.getSubimage(101, 180, 18, 30)));
        images.put("7", createTransparentImage(img.getSubimage(116, 180, 18, 30)));
        images.put("8", createTransparentImage(img.getSubimage(131, 180, 18, 30)));
        images.put("9", createTransparentImage(img.getSubimage(146, 180, 18, 30)));
        images.put("0", createTransparentImage(img.getSubimage(162, 185, 16, 20)));
    }

    private BufferedImage createTransparentImage(BufferedImage source) {
        BufferedImage transparentImage = new BufferedImage(
            source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = transparentImage.createGraphics();
        g2d.drawImage(source, 0, 0, null);
        g2d.dispose();
        return transparentImage;
    }

    @Override
    public void combineImages() {
        // 구현 생략
    }
}
