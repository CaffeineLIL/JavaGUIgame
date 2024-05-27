package abstracts;
import javax.swing.*;
import java.math.*;
import abstracts.abstractBasis;

public abstract class abstractPlayer extends abstractBasis {
	final private int HP = 5;
	final private double ATK = 3.5;
	private double effectiveDmg;
	//캐릭터 공격 데미지 계산 공식
	//예상 피해값 = 캐릭터 기본 공격력 * √(획득한 아이템들의 대미지 합계 * 1.2 + 1) + 아이템의 특수 공격력 수치
	public double PlayerAtk(double baseAtk, double totalDmgup, double FlatDmgup ) {
		effectiveDmg = baseAtk * Math.sqrt(totalDmgup * 1.2 + 1) + FlatDmgup;
		return effectiveDmg;
	}
}
