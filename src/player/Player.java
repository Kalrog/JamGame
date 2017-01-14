package player;

/**
 * Manages player stats like health or skill
 * 
 * Created by jonathan on 13.01.17.
 */
public class Player
{

	String name;

	private int money, moral, food, skill, health, distance;

	public Condition condition = null;

	public enum Condition
	{
		SEASICK;
	}

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
		return getSkill() * (getMoral() + 20) / 100;
	}

	public int getSkill()
	{
		if (condition == Condition.SEASICK)
			return (int) (skill * 0.8);
		else
			return skill;
	}

	public int getMoney()
	{
		return money;
	}

	public void setMoney(int money)
	{
		this.money = money;
	}

	public int getMoral()
	{
		return moral;
	}

	public void setMoral(int moral)
	{
		this.moral = moral;
	}

	public int getFood()
	{
		return food;
	}

	public void setFood(int food)
	{
		this.food = food;
	}

	public int getHealth()
	{
		return health;
	}

	public void setHealth(int health)
	{
		this.health = health;
	}

	public int getDistance()
	{
		return distance;
	}

	public void setDistance(int distance)
	{
		this.distance = distance;
	}

	public Condition getCondition()
	{
		return condition;
	}

	public void setCondition(Condition condition)
	{
		this.condition = condition;
	}

	public void setSkill(int skill)
	{
		this.skill = skill;
	}

}
