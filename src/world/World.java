package world;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import assets.AssetLoader;
import encounter.Encounter;
import encounter.IslandEncounter;
import encounter.PirateEncounter;
import game.Display;
import game.Game;
import player.Player;
import player.Player.Condition;
import sun.awt.image.ImageWatched;

/**
 * Created by jonathan on 13.01.17.
 */
public class World
{
    private static final int NO_ENCOUNTER_CHANCE = 800;

    private static final int FRAMES_PER_UPDATE = 2;

    int frame;

    int wave;

    public Player player;

    int size;

    Encounter[] encounters;

    private LinkedList<Encounter> worldEncounters;

    private LinkedList<Encounter> activeEncounters;

    private LinkedList<Encounter> inactiveEncounters;

    public World(Player player, int size)
    {

        encounters = new Encounter[]{new IslandEncounter(this, 0),new PirateEncounter(this, 40 , 10 , 0)};
        this.player = player;
        wave = 0;
        this.size = size;
        frame = 0;
        activeEncounters = new LinkedList<>();
        inactiveEncounters = new LinkedList<>();
        worldEncounters = new LinkedList<>();

        for (int i = 300; i < size - 300; i++)
        {
            int random = (int) (Math.random() * (sumOfEncounterChance(encounters) + NO_ENCOUNTER_CHANCE))
                    - NO_ENCOUNTER_CHANCE;
            if (random > 0) worldEncounters.add(encounterFactory(random, i));
        }

        if (worldEncounters.size() > 1)
            Collections.sort(worldEncounters, Encounter.getComparator());

        LinkedList<Encounter> removalList = new LinkedList<>();
        for (Iterator<Encounter> encounters = worldEncounters.iterator(); encounters.hasNext(); )
        {
            Encounter encounter = encounters.next();

            if (removalList.contains(encounter)) continue;

            removalList.addAll(removeCooldownEvents(encounter));
        }

        worldEncounters.removeAll(removalList);

    }

    public void draw(Graphics g)
    {
        g.clearRect(0, 0, Game.WIDTH, Game.HEIGHT);
        for(int waveNum = 0; waveNum <= 40; waveNum++){
            AssetLoader.wave.draw(g, wave + ((waveNum % 2) * 45) % 90 + AssetLoader.wave.width / 2 - 135, 180 + waveNum * 8);
        }
        for (Encounter encounter : worldEncounters)
        {
            if (!(player.getDistance() - encounter.distance + Display.canvas.getWidth() > -60 && player.getDistance() - encounter.distance + Display.canvas.getWidth() < Display.canvas.getWidth() + 60))
            {
                break;
            }
            encounter.draw(g);

        }
        for (Encounter encounter : inactiveEncounters)
        {
            if (!(player.getDistance() - encounter.distance + Display.canvas.getWidth() > -30 && player.getDistance() - encounter.distance + Display.canvas.getWidth() < Display.canvas.getWidth() + 30))
                break;
            encounter.draw(g);
        }
        player.draw(g);
        if (activeEncounters.size() > 0) activeEncounters.get(activeEncounters.size() - 1).draw(g);
    }

    public void updateAndRender(Graphics g)
    {
        if (activeEncounters.size() == 0)
        {

            frame++;

            if (worldEncounters.size() > 0 && worldEncounters.getFirst().distance == player.getDistance() + (player.texture.width + 10))
            {
                worldEncounters.getFirst().startEncounter();
                inactiveEncounters.add(worldEncounters.getFirst());
                worldEncounters.removeFirst();
            }

            if (frame > FRAMES_PER_UPDATE)
            {
                frame = 0;
                draw(g);
                player.changeDistance(+1);
                player.changeFood(-1);
                wave++;
                wave %=90;

            }
        }else{
            draw(g);
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

    public LinkedList<Encounter> getWorldEncounters()
    {
        return worldEncounters;
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

    private ArrayList<Encounter> removeCooldownEvents(Encounter encounter)
    {
        ArrayList<Encounter> removalList = new ArrayList<>();
        for (Encounter encounter1 : worldEncounters)
        {

            if (encounter.equals(encounter1) && encounter == encounter1 && encounter.distance == encounter1.distance)
            {
                continue;
            }

            if (encounter.distance <= encounter1.distance
                    && encounter.distance + encounter.cooldown >= encounter1.distance
                    && encounter1.priority <= encounter.priority)
            {
                removalList.add(encounter1);
                //worldEncounters.remove(encounter1);
            } else if (encounter1.distance > encounter.distance + encounter.cooldown)
            {
                break;
            }
        }
        return removalList;
    }

    private Encounter encounterFactory(int listPosition, int worldDistance)
    {
        for (Encounter encounter : encounters)
        {
            listPosition -= encounter.chance;
            if (listPosition <= 0)
            {
                if (encounter instanceof IslandEncounter)
                {
                    Encounter result = new Encounter(this, encounter.texture.clone(), encounter.text, encounter.solutions, 0, worldDistance, encounter.priority, encounter.cooldown);
                    result.texture.setYShift((int) (Math.random() * -130.0 ));
                    return result;
                }
                if (encounter instanceof PirateEncounter)
                {
                    Encounter result = new Encounter(this, encounter.texture.clone(), encounter.text, encounter.solutions, 0, worldDistance, encounter.priority, encounter.cooldown);
                    result.texture.setYShift((int) (Math.random() * -130.0 ));
                    return result;
                }
                return new Encounter(this, encounter.texture.clone(), encounter.text, encounter.solutions, 0, worldDistance, encounter.priority, encounter.cooldown);
            }
        }
        return null;
    }

}
