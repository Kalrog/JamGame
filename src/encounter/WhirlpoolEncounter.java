package encounter;

import assets.AssetLoader;
import assets.Texture;
import player.Player.Condition;
import world.World;

public class WhirlpoolEncounter extends Encounter
{

	public WhirlpoolEncounter(World w, int distance, int strength)
	{
		super(w, AssetLoader.wirl, "", new Solution[] { new Whirlpool(strength) }, 5, distance, 1, 250);
		// TODO add textures
	}

	@Override
	public void startEncounter()
	{
		this.showResult(solutions[0].resolve(world));
		world.addActiveEncounter(this);
	}

	static class Whirlpool implements Solution
	{
		int strength;

		public Whirlpool(int strength)
		{
			this.strength = strength;
		}

		@Override
		public String[] resolve(World w)
		{
			String[] results;

			int fightResult = ((int) (Math.random() * (w.player.totalCrewAbility() + strength))) - strength;
			if (fightResult < 0)
			{
				results = new String[3];
				results[0] = "A Whirlpool suddently appeared/nand your crew was taken by surprise";
				int dist = w.player.getDistance() + 300;
				RemoveCondition remove = new RemoveCondition(w, "Your Crew overcame their seasickness", dist,
						Condition.SEASICK);
				w.addWorldEncounter(remove);
				w.player.addCondition(Condition.SEASICK);
				results[1] = "Condition: Seasick";
				int healthLoss = (int) (Math.random() * 6 + 5);
				results[2] = "Health: -" + healthLoss;
				w.player.changeHealth(-healthLoss);

			} else{
				results = new String[2];
				results[0] = "A Whirlpool suddently appeared/nluckily your crew managed to get through the event";
				int healthLoss = (int) (Math.random() * 3 + 3);
				results[1] = "Health: -" + healthLoss;
				w.player.changeHealth(-healthLoss);

			}
			return results;
		}

		@Override
		public String getText()
		{
			// TODO Auto-generated method stub
			return "ok";
		}

	}

}
