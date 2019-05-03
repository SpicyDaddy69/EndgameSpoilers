
public class BattleMechanics
{
	private Character attacker;
	
	public BattleMechanics(Character attack)
	{
		attacker = attack;
	}
	
	public String attack(int damage, Character defender)
	{
		int hit = attacker.rollCriticalHit(damage);
		defender.setHealth(defender.getHealth() - hit);
		return "The attack dealt " + hit + " damage!";
	}
}
