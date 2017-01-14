package player;

/**
 * Manages player stats like health or skill
 * 
 * Created by jonathan on 13.01.17.
 */
public class Player
{

	String	name;

	public int money , moral, food, skill, health , distance;


	/**
	 * Creates a player and sets some values
	 * 
	 * @param name
	 *            Name of the player;
	 */
	public Player(String name)
	{
		health = 100;
		moral = 100;
		money = 100;
		skill = 100;
		distance = 0;
		food = 20;// <==filler :: not clear what units to use yet
		this.name = name;

	}
	
	public int totalCrewAbility()
	{
		return skill * (moral + 20) / 100;
	}

}
