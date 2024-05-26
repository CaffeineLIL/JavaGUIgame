package stage;
import javax.swing.*;

import player.Player;
import Enemy.WeakEnemy;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import stage.Map_bg;

public class stage_1 extends JPanel{
	private BufferedImage backgroundImage;
	private int playerX, playerY;
	
	public stage_1() {
		// 이미지 로드
	    Map_bg mapBg = new Map_bg();
	    backgroundImage = mapBg.getImage();
	    
	    Player player = new Player();
	    int playerX = player.getPlayerX();
        int playerY = player.getPlayerY();
	    
        add(player);
        
        WeakEnemy en1 = new WeakEnemy(playerX, playerY);
        add(en1);
	    
	}
	
	 @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);

	        // 배경 이미지 그리기
	        if (backgroundImage != null) {
	            // 이미지를 JPanel에 꽉 차도록 그립니다.
	            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
	        }
	    }
	 
	  @Override
	    public Dimension getPreferredSize() {
	        // JPanel의 크기를 JFrame과 동일하게 반환합니다.
	        return new Dimension(1000, 600);
	    }
}
