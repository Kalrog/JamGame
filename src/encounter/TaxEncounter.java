package encounter;

import assets.Texture;
import world.World;

public class TaxEncounter extends Encounter
{

	public TaxEncounter(World w)
	{
		super(w, null, "You meet a navy fleet", new Solution[] { new Tax() }, 0, 0, 1, 200);
		// TODO Auto-generated constructor stub
	}

	static class Tax implements Solution
	{

		String[] results;

		@Override
		public String[] resolve(World w)
		{
			results = new String[2];
			results[0] = "They tax all your goods";
			int moneyLoss = (int) (0.1 * (w.player.getFood() + w.player.getLuxaryItems() + w.player.getRawMaterials()));
			w.player.changeMoney(-moneyLoss);
			results[1] = "Money: -" + moneyLoss;

			return null;
		}

		@Override
		public String getText()
		{
			// TODO Auto-generated method stub
			return "Continue";
		}

	}

}
