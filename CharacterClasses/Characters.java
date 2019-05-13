
public class Characters
{
	protected int strength, endurance, health;
	protected String name;
	protected double critMultiplier;
	
	public Characters(int str, int end, String name)
	{
		strength = str;
		endurance = end;
		this.name = name;
		health = 245 + (end * 5);
		critMultiplier = 1.5;
	}
	
	public void setHealth(int health)
	{
		this.health = health;
	}
	
	public void setStrength(int strength)
	{
		this.strength = strength;
	}
	
	public void setEndurance(int endurance)
	{
		this.endurance = endurance;
		health = 250 + (this.endurance-1 * 5);
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public int getStrength()
	{
		return strength;
	}
	
	public int getEndurance()
	{
		return endurance;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int rollCriticalHit(int damage)
	{
		int roll = (int) (Math.random() * 20 + 1);
		
		if(roll > 18)
			return (int) (damage * critMultiplier);
		else
			return damage;
	}
	
	public String toString()
	{
		String output = "";
		output += "Name: " + health + "\nGender: " + "\nStrength: " + strength + "\nEndurance: " + endurance;
		
		return output;
	}
}
