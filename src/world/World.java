package world;

import encounter.Encounter;
import player.Player;

/**
 * 
 * 
 * Created by jonathan on 13.01.17.
 */
public class World
{
	public Player player;

	int difficulty;

	Encounter[] encounters;

	public World(Player player, int difficulty)
	{

		this.player = player;
		this.difficulty = difficulty;

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
				player.distance++;
			}
		}
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
