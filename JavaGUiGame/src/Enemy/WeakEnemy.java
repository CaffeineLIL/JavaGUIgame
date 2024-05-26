package Enemy;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import status_basis.RectangleHitbox;
import abstracts.abstractEnemy;
import abstracts.abstractHitbox;
import Enemy.WeakEnmyImage;

public class WeakEnemy extends abstractEnemy {
	
	private int hp = 10;
    private int x = 200;
    private int y = 200;
    private final int SIZE = 50;
    private final int MOVE_AMOUNT = 3;
   
    
    private abstractHitbox hitbox;
    private BufferedImage enemyImage;
    
    public WeakEnemy(int playerX, int playerY) {
    	
    	WeakEnmyImage enemyimg = new WeakEnmyImage();
    	enemyImage = enemyimg.getImage();
    	
    	repaint();
    	
    	hitbox.setPosition(x, y);
    }
    
    public void move(int playerX, int playerY) {
    	
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // 캐릭터 이미지 그리기
        g.setColor(Color.WHITE);
        g.fillRect(50, 50, 200, 150); // 좌표 및 크기는 필요에 맞게 조정
        
        // 히트박스 그리기 (예외 처리 추가)
        try {
            if (hitbox != null) {
                hitbox.draw(g);
            } else {
                throw new RuntimeException("히트박스가 초기화되지 않았습니다.");
            }
        } catch (RuntimeException e) {
            System.out.println("히트박스 인식 오류: " + e.getMessage());
        }
    }
    
    
}
