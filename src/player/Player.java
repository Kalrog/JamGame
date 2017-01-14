package player;

import assets.Texture;

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

	public double modifier = 1;

	public Texture texture;


	public enum Condition
	{
		SEASICK, ILL;
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
		return skill * (moral + 20) / 100;
	}
	
	public int getSkill()
	{
		return (int) (modifier * skill);
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
	    if(condition == Condition.ILL)
	        return ((int) (moral * 0.6));
	    else
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

	public double getModifier()
	{
		return modifier;
	}

	public void setModifier(double modifier)
	{
		this.modifier = modifier;
	}

	public void setSkill(int skill)
	{
		this.skill = skill;
	}

}
