package encounter;

import java.util.concurrent.ThreadLocalRandom;

import player.Player.Condition;
import world.World;

public class IslandEncounter extends Encounter
{

	private static World w;

	public IslandEncounter(World w, int distance)
	{
		super(w, "Yout lookout spotted an Island in the distance/nExplore the isalnd?", new Solution[] {}, 5, distance,
				1, 13);
		this.w = w;
	}

	static class goIsland implements Solution
	{

		String[] results;

		@Override
		public String[] resolve(World w)
		{
			int randome = ThreadLocalRandom.current().nextInt(0, 1);

			switch (randome)
			{
				case 0:
					results = manyRecources();
				case 1:
					results = disease();
				case 2:
					results = tribe();
				case 3:
					results = shark();
				case 4:
					results = volcano();
				case 5:
					results = mysticalTemple();
			}

			return results;
		}

		@Override
		public String getText()
		{
			// TODO Auto-generated method stub
			return "Yes";
		}

	}

	static class leaveIsland implements Solution
	{

		@Override
		public String[] resolve(World w)
		{
			return null;
		}

		@Override
		public String getText()
		{
			return "No";
		}

	}

	private static String[] manyRecources()
	{
		String[] results = new String[4];

		results[0] = "The Island has plenty of recources to scavenge";
		int foodGain = ((int) (Math.random() * 4) + 7);
		results[1] = "Food: +" + foodGain;
		w.player.changFood(+foodGain);
		int moralGain = ((int) (Math.random() * 4) + 5);
		results[2] = "Moral: +" + moralGain;
		w.player.changeMoral(+moralGain);
		results[3] = "Resources: +";// TODO add actual recources
		return results;
	}

	private static String[] disease()
	{
		String[] results = new String[3];
		results[0] = "The Island is infected with a plague/n Your crew gets sick";
		int moralLoss = ((int) (Math.random() * 4) + 7);
		results[1] = "Moral: -" + moralLoss;
		w.player.changeMoral(-moralLoss);
		int untilCured = 6;
		RemoveCondition removeCondition = new RemoveCondition(w, "Your Crew overcame their illness", untilCured, Condition.ILL);
		results[2] = "Condition: Illness";
		w.player.addCondition(Condition.ILL);
		return null;
	}

}
