package Enemy;

import java.awt.*;
import java.awt.image.BufferedImage;
import abstracts.abstractEnemy;
import abstracts.abstractHitbox;
import status_basis.RectangleHitbox;

public class WeakEnemy extends abstractEnemy {
    private double hp = setHp();
    private int x = 200;
    private int y = 200;
    private final int SIZE = 50;
    private final int MOVE_AMOUNT = 3;
    private abstractHitbox hitbox;
    private BufferedImage enemyImage;
    private WeakEnemyImage enemyImg;
    
    private int playerX; // 플레이어의 X 좌표
    private int playerY; // 플레이어의 Y 좌표

    public WeakEnemy(int playerX, int playerY) {
        setOpaque(false);
        hitbox = new RectangleHitbox(x, y, SIZE, SIZE);
        enemyImg = new WeakEnemyImage();
        enemyImage = enemyImg.getImage();
        
        
        this.playerX = playerX; // 플레이어의 X 좌표 설정
        this.playerY = playerY; // 플레이어의 Y 좌표 설정
        
        move(); // 적의 이동 메서드 호출
        hitbox.setPosition(x, y);
        repaint();
    }
    
 // 적을 이동시키는 메서드
    @Override
    public void move() {
        // 현재 위치와 목표 위치 간의 이동 벡터 계산
        double dx = playerX - x;
        double dy = playerY - y;

        // 이동 벡터의 크기 계산
        double distanceToTarget = Math.sqrt(dx * dx + dy * dy);

        // 이동 벡터의 크기가 이동 속도보다 작으면 목표 위치에 도달한 것으로 간주
        if (distanceToTarget <= MOVE_AMOUNT) {
            x = playerX;
            y = playerY;
        } else {
            // 이동 속도에 맞춰 이동 벡터를 스케일링하여 현재 위치에 추가
            double scale = MOVE_AMOUNT / distanceToTarget;
            x += dx * scale;
            y += dy * scale;
        }
        
        // JPanel을 다시 그려서 적의 위치를 갱신합니다.
        repaint();
    }


    // 적의 위치를 반환하는 메서드
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    //적의 체력 반환 메서드
    public double getEnHp() {
    	return hp;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 캐릭터 이미지 그리기
        if (enemyImage != null) {
            g.drawImage(enemyImage, x, y, SIZE, SIZE, null);
        }

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
