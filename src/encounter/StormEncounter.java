package encounter;

import assets.AssetLoader;
import player.Player.Condition;
import world.World;

public class StormEncounter extends Encounter
{

	public StormEncounter(World world, int distance, int strength)
	{
		super(world, AssetLoader.getRandomStormTexture(), "You see a Storm brewing up in the distance",
				new Solution[] { new AvoidStorm(), new ThroughStorm(strength) }, 20, distance, 1, 5);
	}

	static class AvoidStorm implements Solution
	{

		String[] results;

		@Override
		public String[] resolve(World w)
		{
			results = new String[2];
			results[0] = "You travel around the Storm";
			int foodLoss = 2;
			results[1] = "Food: -" + foodLoss;
			w.player.changeFood(-foodLoss);

			return results;
		}

		@Override
		public String getText()
		{
			return "Try to navigate around the Storm";
		}

	}

	static class ThroughStorm implements Solution
	{

		String[] results;

		int strength;

		public ThroughStorm(int strength)
		{
			this.strength = strength;
		}

		@Override
		public String[] resolve(World w)
		{
			int stormResult = ((int) (Math.random() * (w.player.totalCrewAbility() + strength))) - strength;

			if (stormResult < 0)
			{
				results = new String[3];
				results[0] = "You sail through the Strom but your crew didn't take it so well";
				w.player.addCondition(Condition.SEASICK);
				int untilCured = 3;
				new RemoveCondition(w, "Your Crew overcame their seasickness",
						untilCured, Condition.SEASICK);
				results[1] = "Condition: Seasickness";
				int moralLoss = ((int) (Math.random() * 8) + 5);
				results[2] = "Moral: -" + moralLoss;
				w.player.changeMoral(-moralLoss);

			} else
			{
				results = new String[2];
				results[0] = "You sail through the Strom and made it out without a problem";
				int moralGain = ((int) (Math.random() * 8) + 5);
				results[1] = "Moral: +:" + moralGain;
				w.player.changeMoral(+moralGain);
			}

			return results;
		}

		@Override
		public String getText()
		{
			return "Sail through the Storm";
		}

	}

}
