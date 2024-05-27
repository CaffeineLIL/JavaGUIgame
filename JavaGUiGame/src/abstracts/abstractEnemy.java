package abstracts;

public abstract class abstractEnemy extends abstractBasis {
    final private double HP = 10;
    final private int ATK = 1;
    protected int playerX; // 플레이어의 X 위치
    protected int playerY; // 플레이어의 Y 위치

    public abstract void move(); // 이동 메서드

    // 플레이어 위치 설정 메서드
    public void setPlayerPosition(int playerX, int playerY) {
        this.playerX = playerX;
        this.playerY = playerY;
    }
    
    public double setHp(){
    	return HP;
    }
}
