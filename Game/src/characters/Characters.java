package characters;
public class Characters
{
	protected int health;
	protected String name;
	protected double critMultiplier;
	
	public Characters(String name)
	{
		this.name = name;
		health = 50;
		critMultiplier = 1.5;
	}
	
	public void setHealth(int health)
	{
		this.health = health;
	}
	
	public int getHealth()
	{
		return health;
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
	
}
