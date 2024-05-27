package abstracts;
import javax.swing.*;

public abstract class abstractEnemy extends abstractBasis {
	final private int HP = 10;
	final private int ATK = 1;
	
    public abstract void move(int playerX, int playerY);
    
    
}
