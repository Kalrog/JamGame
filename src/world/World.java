package world;

import java.awt.*;
import java.util.*;

import assets.AssetLoader;
import assets.Texture;
import encounter.*;
import game.Display;
import game.Game;
import player.Player;
import player.Player.Condition;
import sun.awt.image.ImageWatched;

import javax.xml.soap.Text;

/**
 * Created by jonathan on 13.01.17.
 */
public class World
{
    private static final int NO_ENCOUNTER_CHANCE = 1000;

    private static final int FRAMES_PER_UPDATE = 2;

    int frame;

    int wave;

    int distanceCounter = 0;
    
    public Player player;

    int size;

    Encounter[] encounters;

    private LinkedList<Encounter> worldEncounters;

    private LinkedList<Encounter> activeEncounters;

    private LinkedList<Encounter> inactiveEncounters;

    private LinkedList<Sprite> sprites;

    public World(Player player, int size)
    {
        //new WhirlpoolEncounter(this, 0, 60),
        encounters = new Encounter[]{new ShopEncounter(this,AssetLoader.getRandomCityTexture(),"You see a City in the Distance/nand approach it to trade with them.",null),new IslandEncounter(this, 0), new PirateEncounter(this, 40, 10, 0),
                new WhirlpoolEncounter(this, 0, 60),new StormEncounter(this, 0, (int) (player.getSkill() * (Math.random() + 0.5)))};
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

        if (worldEncounters.size() > 1) Collections.sort(worldEncounters, Encounter.getComparator());

        LinkedList<Encounter> removalList = new LinkedList<>();
        for (Iterator<Encounter> encounters = worldEncounters.iterator(); encounters.hasNext(); )
        {
            Encounter encounter = encounters.next();

            if (removalList.contains(encounter)) continue;

            removalList.addAll(removeCooldownEvents(encounter));
        }

        worldEncounters.removeAll(removalList);

        // new BuyEncounter(this, "Buy some stuff" , new
        // int[]{10,10,10}).startEncounter();
        //new ShopEncounter(this, null, "Ay a shop at the Port", null).startEncounter();
        
		new ContinueEncounter(this, new String[] { "Ayy shitty Game we made" }).startEncounter();

    }

    static class Sprite
    {
        Texture texture;

        int x, y;

        public Sprite(Texture texture, int x, int y)
        {
            this.texture = texture;
            this.x = x;
            this.y = y;
        }

        public static Comparator<Sprite> getComparator()
        {
            return new Comparator<Sprite>()
            {
                @Override
                public int compare(Sprite sprite, Sprite t1)
                {
                    return (sprite.y + sprite.texture.yShift) - (t1.y + t1.texture.yShift);
                }
            };
        }

        public void draw(Graphics g)
        {
            texture.draw(g, x, y);
        }

    }

    public void draw(Graphics g)
    {
        sprites = new LinkedList<>();
        g.clearRect(0, 0, Game.WIDTH, Game.HEIGHT);
        for (int waveNum = 0; waveNum <= 20; waveNum++)
        {

            sprites.add(new Sprite(AssetLoader.wave,
                    wave + ((waveNum % 2) * 45) % 90 + AssetLoader.wave.width / 2 - 135, 180 + waveNum * 16));
            // AssetLoader.wave.draw(g, wave + ((waveNum % 6) * 15) % 90 +
            // AssetLoader.wave.width / 2 - 150, 180 + waveNum * 12);
        }
        for (Encounter encounter : worldEncounters)
        {

            if (!(player.getDistance() - encounter.distance + Display.canvas.getWidth() > -100 && player.getDistance()
                    - encounter.distance + Display.canvas.getWidth() < Display.canvas.getWidth() + 100))
            {
                break;
            }
            if (encounter.texture != null)
                sprites.add(new Sprite(encounter.texture,
                        player.getDistance() - encounter.distance + Game.display.getWidth(), Game.SEE_LEVEL));
            // g, world.player.getDistance() - distance +
            // Game.display.getWidth(), Game.SEE_LEVEL
            // encounter.draw(g);

        }
        //System.out.println("Encounters world");
        for (Encounter encounter : inactiveEncounters)
        {
            if (encounter.texture != null)
            {
                if (player.getDistance() - encounter.distance + Display.canvas.getWidth() > -100 && player.getDistance()
                        - encounter.distance + Display.canvas.getWidth() < Display.canvas.getWidth() + 100)
                    sprites.add(new Sprite(encounter.texture,
                            player.getDistance() - encounter.distance + Game.display.getWidth(), Game.SEE_LEVEL));
            }
            // encounter.draw(g);
        }
        if (player.texture != null)
            sprites.add(new Sprite(player.texture, Display.canvas.getWidth() - player.texture.width / 2 - 20, Game.SEE_LEVEL));
        // player.draw(g);
        if (sprites.size() > 1)
            Collections.sort(sprites, Sprite.getComparator());

        for (Sprite sprite : sprites)
        {
            sprite.draw(g);
        }

        if (activeEncounters.size() > 0) activeEncounters.get(activeEncounters.size() - 1).draw(g);
    }

    public void updateAndRender(Graphics g)
    {
		if (player.getHealth() < 0 && activeEncounters.size() == 0){
			new DeathEncounter(this).startEncounter();
		}
    	
        if (activeEncounters.size() == 0)
        {

            frame++;

            if (worldEncounters.size() > 0
                    && worldEncounters.getFirst().distance == player.getDistance() + (player.texture.width + 10))
            {
                worldEncounters.getFirst().startEncounter();
                if (worldEncounters.getFirst().texture != null)
                    inactiveEncounters.add(worldEncounters.getFirst());
                worldEncounters.removeFirst();
            }

            if (frame > FRAMES_PER_UPDATE)
            {
                frame = 0;
                draw(g);
                player.changeDistance(+1);
                distanceCounter++;
                if(distanceCounter >= 200){
                player.changeFood(-1);
                distanceCounter = 0;
                }
                wave++;
                wave %= 90;

            }
        } else
        {
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
        ArrayList<Encounter> removalList = removeCooldownEvents(encounter);
        worldEncounters.removeAll(removalList);
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
        if(encounter.cooldown > -1)
        {
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
                    // worldEncounters.remove(encounter1);
                } else if (encounter1.distance > encounter.distance + encounter.cooldown)
                {
                    break;
                }
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
                /*if (encounter instanceof IslandEncounter)
				{
					Encounter result = new Encounter(this, encounter.texture.clone(), encounter.text,
							encounter.solutions, 0, worldDistance, encounter.priority, encounter.cooldown);
					int shift = (int) (Math.random() * 12 - 6);
					if (shift > 0)
						shift += 1;
					else
						shift -= 1;
					result.texture.setYShift(shift * 16 - 10);
					return result;
				}*/
                if (encounter instanceof PirateEncounter || encounter instanceof IslandEncounter || encounter instanceof WhirlpoolEncounter || encounter instanceof StormEncounter || encounter instanceof ShopEncounter)
                {
                    Encounter result = new Encounter(this, encounter.texture.clone(), encounter.text,
                            encounter.solutions, 0, worldDistance, encounter.priority, encounter.cooldown);
                    int shift = (int) (Math.random() * 12 - 6);
                    if (shift > 0)
                        shift += 1;
                    else
                        shift -= 1;
                    result.texture.setYShift(shift * 16 - 10);
                    return result;
                }
                return new Encounter(this, (encounter.texture != null) ? encounter.texture.clone() : null, encounter.text, encounter.solutions, 0,
                        worldDistance, encounter.priority, encounter.cooldown);
            }
        }
        return null;
    }

}
