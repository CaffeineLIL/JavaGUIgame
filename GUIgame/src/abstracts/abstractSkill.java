package abstracts;

abstract class abstractSkill {
	int attackDMG(int atk, int skill) {
        // 기본 데미지 공식 = 기본 공격력 * 스킬 계수
        int atkDamage = atk * skill / 100;
        return atkDamage;
    }
}
