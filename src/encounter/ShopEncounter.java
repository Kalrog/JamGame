package encounter;

import assets.AssetLoader;
import assets.Texture;
import encounter.BuyEncounter.InMenuButton;
import game.ButtonCall;
import world.World;

public class ShopEncounter extends Encounter
{
	static int price1, price2, price3, sell1, sell2, sell3;

	public ShopEncounter(World w,Texture texture, String text, Encounter origin)
	{
		super(w, texture, text, null, 5, 0, 1, 0);
		solutions =  new Solution[] {new Buy(this), new Sell(this), new Repair(), new LeaveMenu(this,origin)};

		price1 = (int) (Math.random() * 6 + 5);
		price2 = (int) (Math.random() * 9 + 6);
		price3 = (int) (Math.random() * 101 + 20);
		sell1 = setSellPrice(price1, 5, 4);
		sell2 = setSellPrice(price2, 8, 5);
		sell3 = setSellPrice(price3, 91, 15);

	}
	public ShopEncounter(World w, String text, Encounter origin,int[] buy,int[] sell)
	{
		super(w, null, text, null, 0, 0, 1, 220);
		solutions =  new Solution[] {new Buy(this), new Sell(this), new Repair(), new LeaveMenu(this,origin)};

		price1 = buy[0];
		price2 = buy[1];
		price3 = buy[2];
		sell1 = sell[0];
		sell2 = sell[1];
		sell3 = sell[2];

	}

	private int setSellPrice(int buyPrice, int mult, int base)
	{
		int price = 0;
		while (price == 0 || price > buyPrice)
			price = (int) (Math.random() * mult + base);

		return price;
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
			return "Set Sail";
		}
	}

	static class Buy implements Solution
	{

		Encounter encounter;
		public Buy(Encounter encounter)
		{
			this.encounter = encounter;
		}
		@Override
		public String[] resolve(World w)
		{
			encounter.showResult(null);
			new BuyEncounter(w, "What do you wish to buy?/nMoney: " + w.player.getMoney() + " $", new int[] { price1, price2, price3 },encounter).startEncounter();

			return null;
		}

		@Override
		public String getText()
		{
			// TODO Auto-generated method stub
			return "Buy";
		}

	}

	static class Sell implements Solution
	{
		Encounter encounter;
		public Sell(Encounter encounter)
		{
			this.encounter = encounter;
		}
		@Override
		public String[] resolve(World w)
		{
			encounter.showResult(null);
			new SellEncounter(w, "What do you whish to sell?", new int[] { sell1, sell2, sell3 } ,encounter).startEncounter();

			return null;
		}

		@Override
		public String getText()
		{
			// TODO Auto-generated method stub
			return "Sell";
		}

	}

	static class Repair implements Solution
	{

		@Override
		public String[] resolve(World w)
		{
			if(w.player.getHealth()< 100 && w.player.getMoney() > 10)
			{
				System.out.println(w.player.getMoney());
				w.player.changeMoney(-10);
				System.out.println(w.player.getMoney());
				System.out.println(w.player.getHealth());
				w.player.changeHealth(+5);
				System.out.println(w.player.getHealth());
			}
			
			return null;
		}

		@Override
		public String getText()
		{	
			return "Repair: -" + 10 + "$/n" + "Health: +" + 5;
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
