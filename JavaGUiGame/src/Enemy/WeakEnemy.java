package Enemy;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import abstracts.abstractEnemy;
import abstracts.abstractHitbox;
import player.Player;
import player.Projectile;
import status_basis.RectangleHitbox;
import utils.CollisionDetector;

public class WeakEnemy extends abstractEnemy {
    private double hp = setHp();
    private double x = 200; // 변경: int -> double
    private double y = 200; // 변경: int -> double
    private final int SIZE = 50;
    private final double MOVE_AMOUNT = 3; // 변경: int -> double
    private boolean alive = true;
    
    Player player = new Player();
    private double PlayerDmg;
    
    private Rectangle  Enemyhitbox;
    private BufferedImage enemyImage;
    private WeakEnemyImage enemyImg;
   
    
    //충돌 감지 참조변수 추가
    private CollisionDetector collisionDetector;
    //투사체 참조변수 추가
    private ArrayList<Projectile> projectiles;
    
    private int playerX; // 변경: int -> double
    private int playerY; // 변경: int -> double

    public double getenHp() {
        return this.hp;
    }

    // 추가: 히트박스를 반환하는 메서드
    public Rectangle getHitbox() {
        return Enemyhitbox;
    }
    
    
    
    //적 객체 생성자
    public WeakEnemy( int playerX, int playerY) { // 변경: int -> double
        
        setOpaque(false);
        Enemyhitbox = new Rectangle((int) x, (int) y, SIZE, SIZE);
        enemyImg = new WeakEnemyImage();
        enemyImage = enemyImg.getImage();
        
        this.playerX = playerX;
        this.playerY = playerY;
        
        //장애물 충돌 감지 설정
        collisionDetector = new CollisionDetector();
        projectiles = player.getProjectiles();
    }
    
    public void setPlayerPosition(int playerX, int playerY) {
        this.playerX = playerX;
        this.playerY = playerY;
        //System.out.println("player x:" + playerX + "  player y : " + playerY);
    }
    
    public void setCollisionDetector(CollisionDetector collisionDetector) {
        this.collisionDetector = collisionDetector;
    }
    
 // 투사체와 충돌 시 피격 처리
    public void checkHit() {
        for (Projectile projectile : projectiles) {
            if (projectile.checkCollision(Enemyhitbox)) {
                this.hp -= player.getDamage();
                System.out.println("hit");
                if (this.hp <= 0) {
                    this.alive = false;
                    System.out.println("Enemy dead");
                }
                projectile.deactivate();
            }
        }
    }
    

    // 플레이어와 충돌 시 체력 감소 처리
    private void checkCollisionWithPlayer() {
    	if (Enemyhitbox.getBounds().intersects(player.getHitbox().getBounds())) { // getHitbox().getBounds() 사용
            player.decreaseHp(1); // 플레이어의 체력을 1 감소시킴
            System.out.println("Player hit");
        }
    }

    @Override
    public void move() {
    	
    	int newX;
    	int newY;
    
        //지속적으로 플레이어의 데미지 갱신
        PlayerDmg = player.getDamage();
        checkHit();
        checkCollisionWithPlayer();
        
        // x축과 y축에 대해 개별적으로 이동 벡터 계산
        double dx = playerX - x;
        double dy = playerY - y;

        // 이동 벡터의 크기 계산
        double distanceToTarget = Math.sqrt(dx * dx + dy * dy);
        
        // 플레이어와 적이 충돌할 거리 설정
        double collisionDistance = 1;
        
        // 적과 플레이어의 좌표가 일치하면 목표 위치에 도달한 것으로 간주
	        if (distanceToTarget <= collisionDistance || ((int) x == (int) playerX && (int) y == (int) playerY)) {
	            newX = playerX;
	            newY = playerY;
	        } else {
	            // x축과 y축에 대해 이동 거리 계산
	            double xMoveAmount = MOVE_AMOUNT * (dx / distanceToTarget);
	            double yMoveAmount = MOVE_AMOUNT * (dy / distanceToTarget);
	            
	            newX = (int)(x + xMoveAmount);
	            newY = (int)(y + yMoveAmount);
	            
	            //벽에 닿지 않을 경우 위치 변환
	            if (!collisionDetector.checkCollision(new Rectangle(newX, newY, SIZE, SIZE))) {
	                x = newX;
	                y = newY;
	                
	                // x, y 좌표가 각각 플레이어 위치를 넘어가지 않도록 조정
	                if (Math.abs(playerX - x) < Math.abs(xMoveAmount)) {
	                    x = playerX;
	                }
	                if (Math.abs(playerY - y) < Math.abs(yMoveAmount)) {
	                    y = playerY;
	                }
	            }
	        }
        

        // 히트박스 업데이트
        Enemyhitbox.setLocation((int) x, (int) y);

        // 플레이어와 충돌 감지 및 체력 감소 처리
        checkCollisionWithPlayer();
        
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
        return this.hp;
    }
    
    //적의 생존 상태 반환 메서드
    public boolean isAlive() {
        return this.alive;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 캐릭터 이미지 그리기
        if (enemyImage != null) {
            g.drawImage(enemyImage, (int) x, (int) y, SIZE, SIZE, null);
        }

        // 히트박스 그리기 (예외 처리 추가)
        try {
            if (Enemyhitbox != null) {
            	
                g.drawRect(Enemyhitbox.x, Enemyhitbox.y, Enemyhitbox.width, Enemyhitbox.height);
            } else {
                throw new RuntimeException("히트박스가 초기화되지 않았습니다.");
            }
        } catch (RuntimeException e) {
            System.out.println("히트박스 인식 오류: " + e.getMessage());
        }
    }
}
