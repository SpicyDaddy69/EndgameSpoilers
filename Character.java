
public class Character
{
	protected int strength, endurance, agility, intelligence, perception;
	protected String name, gender;
	
	public Character(int str, int end, int agil, int intel, int per, String name, String gender)
	{
		strength = str;
		endurance = end;
		agility = agil;
		intelligence = intel;
		perception = per;
		this.name = name;
		this.gender = gender;
	}
}
