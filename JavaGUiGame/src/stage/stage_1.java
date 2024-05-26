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
	    playerX = player.get_x();
	    playerY = player.get_y();
	    
        add(player);
        
        WeakEnemy en1 = new WeakEnemy(playerX, playerY);
	    
	}
    
}
