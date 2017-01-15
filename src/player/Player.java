package player;

import java.awt.*;
import java.util.ArrayList;

import assets.Texture;
import game.Display;
import game.Game;

/**
 * Manages player stats like health or skill
 * <p>
 * Created by jonathan on 13.01.17.
 */
public class Player
{

    String name;

    private int money, moral, food, skill, health, distance, luxaryItems, rawMaterials;

    public Texture texture;

    public ArrayList<Condition> conditions;

    public enum Condition
    {
        SEASICK, ILL, BLESSED
    }

    public enum ResourceType
    {
        FOOD, LUXARY, RAW
    }

    /**
     * Creates a player and sets some values
     *
     * @param name Name of the player;
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
        conditions = new ArrayList<>();
        texture = new Texture("Assets/ship.png", 119, 75);
        texture.yShift = -10;

    }

    public int totalCrewAbility()
    {
        return getSkill() * (getMoral() + 20) / 100;
    }

    public int getSkill()
    {
        if (conditions.contains(Condition.BLESSED))
            return (int) (skill * 2);
        else if (conditions.contains(Condition.SEASICK))
            return (int) (skill * 0.8);
        else
            return skill;
    }

    public int getMoney()
    {
        return money;
    }

    public void changeMoney(int money)
    {
        this.money += money;
    }

    public int getMoral()
    {
        if (conditions.contains(Condition.ILL))
            return ((int) (moral * 0.6));
        else
            return moral;
    }

    public void changeMoral(int moral)
    {
        this.moral += moral;
    }

    public int getFood()
    {
        return food;
    }

    public void changeFood(int food)
    {
        this.food += food;
    }

    public int getHealth()
    {
        return health;
    }

    public void changeHealth(int health)
    {
        this.health += health;
    }

    public int getDistance()
    {
        return distance;
    }

    public void changeDistance(int distance)
    {
        this.distance += distance;
    }

    public void addCondition(Condition condition)
    {
        conditions.add(condition);
    }

    public void removeCondition(Condition condition)
    {
        conditions.remove(condition);
    }

    public void changeSkill(int skill)
    {
        this.skill += skill;
    }

    public int getLuxaryItems()
    {
        return luxaryItems;
    }

    public void changeLuxaryItems(int luxaryItems)
    {
        this.luxaryItems += luxaryItems;
    }

    public int getRawMaterials()
    {
        return rawMaterials;
    }

    public void changeRawMaterial(int rawMaterials)
    {
        this.rawMaterials += rawMaterials;
    }

    public int getResource(ResourceType resourceType)
    {
        switch (resourceType)
        {
            case RAW:
                return rawMaterials;
            case LUXARY:
                return luxaryItems;
            case FOOD:
                return food;
        }
        return -1;
    }

    public void changeResource(ResourceType resourceType, int amount)
    {
        switch (resourceType)
        {
            case RAW:
                rawMaterials += amount;
                break;
            case LUXARY:
                luxaryItems += amount;
                break;
            case FOOD:
                food += amount;
                break;
        }
    }

    public static String getResourceName(ResourceType resourceType)
    {
        switch (resourceType)
        {
            case RAW:
                return "Raw Material ";
            case LUXARY:
                return "Luxary Good ";
            case FOOD:
                return "Food ";
        }
        return "";
    }

    public void draw(Graphics g)
    {
        texture.draw(g, Display.canvas.getWidth() - texture.width / 2 - 20, Game.SEE_LEVEL);
    }

}
