package encounter;

import assets.Texture;
import world.World;

public class ShopEncounter extends Encounter
{

	static int price1, price2, price3, sell1, sell2, sell3;

	public ShopEncounter(World w, String text)
	{
		super(w, null, text, new Solution[] {}, 0, 0, 1, 0);

		price1 = (int) (Math.random() * 6 + 5);
		price2 = (int) (Math.random() * 9 + 6);
		price3 = (int) (Math.random() * 101 + 20);
		sell1 = setSellPrice(price1, 5, 4);
		sell2 = setSellPrice(price1, 8, 5);
		sell3 = setSellPrice(price1, 91, 15);

	}

	private int setSellPrice(int buyPrice, int mult, int base)
	{
		int price = 0;
		while (price == 0 || price > buyPrice)
			price = (int) (Math.random() * mult + base);

		return price;
	}

	static class Buy implements Solution
	{

		@Override
		public String[] resolve(World w)
		{
			new BuyEncounter(w, "What do you whish to buy?", new int[] { price1, price2, price3 }).startEncounter();

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

		@Override
		public String[] resolve(World w)
		{

			new SellEncounter(w, "What do you whish to sell?", new int[] { sell1, sell2, sell3 }).startEncounter();

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

			w.player.changeMoney(-10);
			w.player.changeHealth(+5);
			
			return null;
		}

		@Override
		public String getText()
		{	
			return "Repair: -" + 10 + "$/n+" + "Health: +" + 5;
		}

	}

}
