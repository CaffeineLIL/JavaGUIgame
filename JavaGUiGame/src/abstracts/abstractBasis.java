package abstracts;
import javax.swing.*;
import java.math.*;

public abstract class abstractBasis extends JPanel{
	private double effectiveDamage;
	
	//데미지 계산 공식
	//baseDmg : 캐릭터 기본 공격력, TotalDmgUps : 아이템으로 증가한 공격력.
	public double effectiveDmg(double baseDmg, double TotalDmgUps) {
		effectiveDamage = baseDmg * Math.sqrt(TotalDmgUps *1.2 + 1);
		return effectiveDamage;
	}
	
 }
