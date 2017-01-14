package world;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import encounter.Encounter;
import game.Display;
import player.Player;
import player.Player.Condition;

/**
 * Created by jonathan on 13.01.17.
 */
public class World
{
	private static final int NO_ENCOUNTER_CHANCE = 60;

    private static final int FRAMES_PER_UPDATE = 120;

    int frame;

    public Player player;

	int size;

	Encounter[] encounters;

	private LinkedList<Encounter> worldEncounters;

	private LinkedList<Encounter> activeEncounters;

	public World(Player player, int size)
	{

        this.player = player;
        this.size = size;
        frame = 0;
        activeEncounters = new LinkedList<>();
        worldEncounters = new LinkedList<>();

		for (int i = 5; i < size - 5; i++)
		{
			int random = (int) (Math.random() * (sumOfEncounterChance(encounters) + NO_ENCOUNTER_CHANCE) + 1)
					- NO_ENCOUNTER_CHANCE;
			if (random > 0) worldEncounters.add(encounterFactory(random, i));
		}

		Collections.sort(worldEncounters, Encounter.getComparator());

		for (Encounter encounter : worldEncounters)
		{
			removeCooldownEvents(encounter);
		}

	}

	public void draw(Graphics g)
	{
		for(Encounter encounter : worldEncounters)
		{
			if(encounter.distance - player.getDistance() > Display.canvas.getWidth() + 50) break;
			encounter.draw(g);
		}
		player.draw(g);
		if (activeEncounters.size() > 0) activeEncounters.get(activeEncounters.size() - 1).draw(g);
	}

    public void update()
    {
        if(activeEncounters.size() == 0)
        {

            frame++;

            if (worldEncounters.getFirst().distance == player.getDistance())
            {
                worldEncounters.getFirst().startEncounter();
                worldEncounters.removeFirst();
            }

            if (frame > FRAMES_PER_UPDATE)
            {
                frame = 0;
                player.changeDistance(player.getDistance() + 1);
                player.changeFood(player.getFood() - 1);

            }
        }
    }

	public void addActiveEncounter(Encounter encounter)
	{
		activeEncounters.add(encounter);
	}

	public void removeActiveEncounter(Encounter encounter)
	{
		activeEncounters.remove(encounter);
	}

	public void addWorldEncounter(Encounter encounter)
	{
		worldEncounters.add(encounter);
		Collections.sort(worldEncounters, Encounter.getComparator());
		removeCooldownEvents(encounter);
	}

	public void removeWorldEncounter(Encounter encounter)
	{
		worldEncounters.remove(encounter);
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

	private void removeCooldownEvents(Encounter encounter)
	{
		for (Encounter encounter1 : worldEncounters)
		{
			if (encounter.distance <= encounter1.distance
					&& encounter.distance + encounter.cooldown >= encounter1.distance
					&& encounter1.priority <= encounter.priority)
			{
				worldEncounters.remove(encounter1);
			} else if (encounter1.distance > encounter.distance + encounter.cooldown) break;
		}
	}

    private Encounter encounterFactory(int listPosition, int worldDistance)
    {
        for (Encounter encounter : encounters)
        {
            listPosition -= encounter.chance;
            if (listPosition < 0)
            {
                return new Encounter(this,encounter.texture, encounter.text, encounter.solutions, 0, worldDistance, encounter.priority, encounter.cooldown);
            }
        }
        return null;
    }

}
