package encounter;

import game.ButtonCall;
import player.Player;
import world.World;

/**
 * Created by jonathan on 15.01.17.
 */
public class SellEncounter extends Encounter
{
    public SellEncounter(World world, String text, int[] prices)
    {
        super(world, null, text, null, 0, 0, 0, 0);
        solutions = new Solution[]{new SellSolution(this,prices[0], Player.ResourceType.FOOD), new SellSolution(this , prices[1], Player.ResourceType.RAW), new SellSolution(this,prices[2], Player.ResourceType.LUXARY)};
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
            System.out.print("Got input");
            if (w.player.getResource(resourceType) > 0)
            {
                w.player.changeMoney(+price);
                w.player.changeResource(resourceType, -1);
                encounter.text = "Sold 1 " + Player.getResourceName(resourceType);

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
