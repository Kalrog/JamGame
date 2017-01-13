package Player;

/**
 * Manages Player stats like health or skill
 * 
 * Created by jonathan on 13.01.17.
 */
public class Player
{

	String	name;

	int		moral, food, skill, health;

	/**
	 * Creates a player and sets some values
	 * 
	 * @param name
	 *            Name of the Player;
	 */
	public Player(String name)
	{
		health = 100;
		moral = 50;
		food = 20;// <==filler :: not clear what units to use yet
		this.name = name;

	}

}
