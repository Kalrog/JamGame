package world;

import java.awt.Graphics;
import java.util.ArrayList;

import encounter.Encounter;
import player.Player;
import player.Player.Condition;

/**
 * 
 * 
 * Created by jonathan on 13.01.17.
 */
public class World
{
	public Player player;

	int difficulty;

	Condition currentCondition = player.condition;

	Encounter[] encounters;

	private ArrayList<Encounter> activeEncounters;

	public World(Player player, int difficulty)
	{

		this.player = player;
		this.difficulty = difficulty;
		activeEncounters = new ArrayList<>();

	}

	public void draw(Graphics g)
	{
		if (activeEncounters.size() > 0) activeEncounters.get(activeEncounters.size() - 1).draw(g);
	}

	public void update()
	{
		int total = sumOfEncounterChance(encounters);

		for (Encounter encounter : encounters)
		{
			total -= encounter.chance;
			if (total < 0)
			{
				encounter.startEncounter();
				player.setDistance(player.getDistance() + 1);
				player.setFood(player.getFood() + 1);
				;
			}
		}
	}


	public void addEncounter(Encounter encounter)
	{
		activeEncounters.add(encounter);
	}

	public void removeEncounter(Encounter encounter)
	{
		activeEncounters.remove(encounter);
	}

	public int sumOfEncounterChance(Encounter[] encounters)
	{
		int total = 0;

		for (Encounter encounter : encounters)
		{
			total += encounter.chance;
		}

		return total;
	}

}
