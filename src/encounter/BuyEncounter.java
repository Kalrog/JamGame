package encounter;

import assets.SoundPlayer;
import game.ButtonCall;
import player.Player;
import world.World;

/**
 * Created by jonathan on 15.01.17.
 */
public class BuyEncounter extends Encounter
{
    public BuyEncounter(World world, String text, int[] prices)
    {
        super(world, null, text, null, 0, 0, 0, 0);
        solutions = new Solution[]{new BuySolution(this,prices[0], Player.ResourceType.FOOD), new BuySolution(this , prices[1], Player.ResourceType.RAW), new BuySolution(this,prices[2], Player.ResourceType.LUXARY)};
    }

    static class BuySolution implements Solution
    {
        Player.ResourceType resourceType;
        int price;
        String message;
        Encounter encounter;

        public BuySolution(Encounter encounter,int price, Player.ResourceType resourceType)
        {
            this.resourceType = resourceType;
            this.price = price;
            message = "Buy: " + Player.getResourceName(resourceType) + " " + price + "$";
            this.encounter = encounter;
        }

        @Override
        public String[] resolve(World w)
        {
            if (w.player.getMoney() > price)
            {
                w.player.changeMoney(-price);
                w.player.changeResource(resourceType, +1);
                encounter.texts[0].text = "Bought 1 " + Player.getResourceName(resourceType);
                try
                {
                    SoundPlayer.playSound("Assets/Audio/BoughtItem.wav");
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        public String getText()
        {
            return message;
        }
    }

    @Override
    protected ButtonCall getSolutionButton(Solution solution)
    {
        return new InMenuButton(solution);
    }

    class InMenuButton implements ButtonCall
    {
        private Solution solution;

        public InMenuButton(Solution solution)
        {
            this.solution = solution;
        }

        @Override
        public void call()
        {
            solution.resolve(world);
        }
    }
}
