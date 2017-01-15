package encounter;

import assets.SoundPlayer;
import game.ButtonCall;
import player.Player;
import world.World;

/**
 * Created by jonathan on 15.01.17.
 */
public class SellEncounter extends Encounter
{
    public SellEncounter(World world, String text, int[] prices , Encounter origin)
    {
        super(world, null, text, null, 0, 0, 0, 0);
        solutions = new Solution[]{new SellSolution(this,prices[0], Player.ResourceType.FOOD), new SellSolution(this , prices[1], Player.ResourceType.RAW), new SellSolution(this,prices[2], Player.ResourceType.LUXARY),new LeaveMenu(this,origin)};
    }

    static class LeaveMenu implements Solution{
        Encounter encounter;
        Encounter origin;

        public LeaveMenu(Encounter encounter , Encounter origin)
        {
         this.encounter = encounter;
         this.origin = origin;
        }

        @Override
        public String[] resolve(World w)
        {
            encounter.showResult(null);

            if(origin != null)
            origin.startEncounter();
            return null;
        }

        @Override
        public String getText()
        {
            return "Exit";
        }
    }

    static class SellSolution implements Solution
    {
        Player.ResourceType resourceType;
        int price;
        Encounter encounter;
        String message;

        public SellSolution(Encounter encounter,int price, Player.ResourceType resourceType)
        {
            this.resourceType = resourceType;
            this.price = price;
            message = "Sell: " + Player.getResourceName(resourceType) + " " + price + "$";
            this.encounter = encounter;
        }

        @Override
        public String[] resolve(World w)
        {
            if (w.player.getResource(resourceType) > 0)
            {
                w.player.changeMoney(+price);
                w.player.changeResource(resourceType, -1);
                encounter.texts[0].text = "Sold 1 " + Player.getResourceName(resourceType) + "./n " + w.player.getResource(resourceType) + " left.";
                try
                {
                    SoundPlayer.playSound("Assets/Audio/BoughtItem.wav", 10000);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }

            }else
            {
                encounter.texts[0].text = "We are all out of " + Player.getResourceName(resourceType) + "s Captain!";
                try
                {
                    SoundPlayer.playSound("Assets/Audio/NoMoney.wav", 20000);
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
