
public class BattleMechanics
{
	private Characters attacker;
	
	public BattleMechanics(Characters attack)
	{
		attacker = attack;
	}
	
	public String attack(int damage, Characters defender)
	{
		int hit = attacker.rollCriticalHit(damage);
		defender.setHealth(defender.getHealth() - hit);
		return "The attack dealt " + hit + " damage!\n";
	}
}
