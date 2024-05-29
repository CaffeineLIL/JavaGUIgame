package Enemy;

import java.awt.*;
import java.awt.image.BufferedImage;
import abstracts.abstractEnemy;
import abstracts.abstractHitbox;
import status_basis.RectangleHitbox;

public class WeakEnemy extends abstractEnemy {
    private double hp = setHp();
    private int x = 200; // 변경: int -> double
    private int y = 200; // 변경: int -> double
    private final int SIZE = 50;
    private final double MOVE_AMOUNT = 3; // 변경: int -> double
    private abstractHitbox hitbox;
    private BufferedImage enemyImage;
    private WeakEnemyImage enemyImg;
    
    private int playerX; // 변경: int -> double
    private int playerY; // 변경: int -> double

    public double getenHp() {
    	return hp;
    }
    
    public WeakEnemy(int playerX, int playerY) { // 변경: int -> double
        setOpaque(false);
        hitbox = new RectangleHitbox((int) x, (int) y, SIZE, SIZE);
        enemyImg = new WeakEnemyImage();
        enemyImage = enemyImg.getImage();
        
        this.playerX = playerX;
        this.playerY = playerY;
    }
    
    public void setPlayerPosition(int playerX, int playerY) {
        this.playerX = playerX;
        this.playerY = playerY;
        //System.out.println("player x:" + playerX + "  player y : " + playerY);
    }

    @Override
    public void move() {
        // x축과 y축에 대해 개별적으로 이동 벡터 계산
        double dx = playerX - x;
        double dy = playerY - y;

        // 이동 벡터의 크기 계산
        double distanceToTarget = Math.sqrt(dx * dx + dy * dy);
        
        // 플레이어와 적이 충돌할 거리 설정
        double collisionDistance = 1;

        // 적과 플레이어의 좌표가 일치하면 목표 위치에 도달한 것으로 간주
        if (distanceToTarget <= collisionDistance || ((int) x == (int) playerX && (int) y == (int) playerY)) {
            x = playerX;
            y = playerY;	 
            
        } else {
            // x축과 y축에 대해 이동 거리 계산
            double xMoveAmount = MOVE_AMOUNT * (dx / distanceToTarget);
            double yMoveAmount = MOVE_AMOUNT * (dy / distanceToTarget);

            // 현재 위치에 이동 거리 추가
            x += xMoveAmount;
            y += yMoveAmount;

            // x, y 좌표가 각각 플레이어 위치를 넘어가지 않도록 조정
            if (Math.abs(playerX - x) < Math.abs(xMoveAmount)) {
                x = playerX;
            }
            if (Math.abs(playerY - y) < Math.abs(yMoveAmount)) {
                y = playerY;
            }
            
          //  System.out.println(" enemy x:" + x + "  enemy y : " + y);
        }

        // 히트박스 업데이트
        hitbox.setPosition((int) x, (int) y);
        repaint();
    }


    // 적의 위치를 반환하는 메서드
    public int getX() {
        return (int)x;
    }

    public int getY() {
        return (int)y;
    }
    
    // 적의 체력 반환 메서드
    public double getEnHp() {
        return hp;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 캐릭터 이미지 그리기
        if (enemyImage != null) {
            g.drawImage(enemyImage, (int) x, (int) y, (int)SIZE, (int)SIZE, null);
        }

        // 히트박스 그리기 (예외 처리 추가)
        try {
            if (hitbox != null) {
                //hitbox.draw(g);
            } else {
                throw new RuntimeException("히트박스가 초기화되지 않았습니다.");
            }
        } catch (RuntimeException e) {
            System.out.println("히트박스 인식 오류: " + e.getMessage());
        }
    }
}
