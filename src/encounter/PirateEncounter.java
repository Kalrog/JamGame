package encounter;

import java.util.concurrent.ThreadLocalRandom;

import assets.AssetLoader;
import game.Game;
import world.World;

/**
 * Created by jonathan on 13.01.17.
 */
public class PirateEncounter extends Encounter
{

	public PirateEncounter(World world, int strength, int chance ,int distance)
	{
		super(world, AssetLoader.getRandomPirateTexture(), "You encounter a pirate ship",
				new Solution[] { new FightSolution(strength), new RunSolution(strength) }, chance, distance, 1, 200);

	}

	static class FightSolution implements Solution
	{
		int strength;

		public FightSolution(int strength)
		{
			this.strength = strength;
		}

		@Override
		public String[] resolve(World w)
		{
			String[] results;
			// Negative if Pirate win Positive if Player wins
			int fightResult = ((int) (Math.random() * (w.player.totalCrewAbility() + strength))) - strength;
			if (fightResult < 0)
			{
				results = new String[4];
				results[0] = "Your ship was badly damaged by the pirates/nand you lost some cargo";
				int damage = ((int) (Math.random() * 15) + 5);
				w.player.changeHealth(-damage);
				results[1] = "Health: -" + damage;
				int moralLoss = ((int) (Math.random() * 5) + 5);
				w.player.changeMoral(-moralLoss);
				results[2] = "Moral: -" + moralLoss;
				int foodLoss = ((int) (Math.random() * 10) + 1);
				w.player.changeFood(-foodLoss);
				results[3] = "Food: -" + foodLoss;
			} else
			{
				if (w.player.getSkill() > 50 && w.player.getMoral() > 75 && Math.random() >= 0.5)
				{
					EnterEncounter enterEncounter = new EnterEncounter(w, strength);
					enterEncounter.startEncounter();
					results = null;

				} else
				{
					results = new String[4];
					results[0] = "You defeated the pirates";
					int damage = ((int) (Math.random() * 5) + 1);
					w.player.changeHealth(-damage);
					results[1] = "Health: -" + damage;
					int moralGain = ((int) (Math.random() * 5) + 5);
					w.player.changeMoral(+moralGain);
					results[2] = "Moral: +" + moralGain;
					int foodGain = ((int) (Math.random() * 2) + 5);
					w.player.changeFood(+foodGain);
					results[3] = "Food: +" + foodGain;
				}

			}

			return results;
		}

		@Override
		public String getText()
		{
			return "Fight";
		}
	}

	static class RunSolution implements Solution
	{

		int strength;

		public RunSolution(int strength)
		{
			this.strength = strength;
		}

		int skill;

		@Override
		public String[] resolve(World w)
		{

			String[] results;
			int minRunChance = 30;
			int maxRunChance = minRunChance + w.player.totalCrewAbility();

			if (ThreadLocalRandom.current().nextInt(0, maxRunChance + strength) > maxRunChance)
			{
				results = new RunFight(strength).resolve(w);
			} else
			{
				int moralGain;
				results = new String[2];
				results[0] = "You manage to flee from the Pirates";
				moralGain = ((int) (Math.random() * 5) + 5);
				results[1] = "Moral: +" + moralGain;
				w.player.changeMoral(+moralGain);
			}
			return results;
		}

		@Override
		public String getText()
		{
			return "Flee you fool!";
		}

	}

	static class RunFight implements Solution
	{

		int strength;

		public RunFight(int strength)
		{
			this.strength = strength;
		}

		@Override
		public String[] resolve(World w)
		{
			String[] results;
			// Negative if Pirate win Positive if Player wins
			int fightResult = ((int) (Math.random() * (w.player.totalCrewAbility() + strength))) - strength;
			if (fightResult < 0)
			{
				results = new String[4];
				results[0] = "You didn't manage to flee from the Pirates/n and were slightly damaged";
				int damage = ((int) (Math.random() * 11) + 3);
				w.player.changeHealth(-damage);
				results[1] = "Health: -" + damage;
				int moralLoss = ((int) (Math.random() * 8) + 3);
				w.player.changeMoral(-moralLoss);
				results[2] = "Moral: -" + moralLoss;
				int foodLoss = ((int) (Math.random() * 6) + 2);
				w.player.changeFood(-foodLoss);
				results[3] = "Food: -" + foodLoss;
			} else
			{
				results = new String[4];
				results[0] = "You didn't manage to flee from the Pirates/n but luckily defeated them";
				int damage = ((int) (Math.random() * 7) + 1);
				w.player.changeHealth(-damage);
				results[1] = "Health: -" + damage;
				int moralGain = ((int) (Math.random() * 3) + 3);
				w.player.changeMoral(+moralGain);
				results[2] = "Moral: +" + moralGain;
				int foodGain = ((int) (Math.random() * 4) + 1);
				w.player.changeFood(+foodGain);
				results[3] = "Food: +" + foodGain;

			}
			return results;
		}

		@Override
		public String getText()
		{
			return null;
		}

	}
}
