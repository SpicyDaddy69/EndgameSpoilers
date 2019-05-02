
public class Character
{
	protected int strength, endurance, health;
	protected String name, gender;
	protected double critMultiplier;
	
	public Character(int str, int end, String name, String gender)
	{
		strength = str;
		endurance = end;
		this.name = name;
		this.gender = gender;
		health = 250 + (end-1 * 5);
		critMultiplier = 1.5;
	}
	
	public int rollCriticalHit(int damage)
	{
		int roll = (int) (Math.random() * 20 + 1);
		
		if(roll > 18)
			return (int) (damage * critMultiplier);
		else
			return damage;
	}
}
