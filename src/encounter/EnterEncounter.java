package encounter;

import world.World;

public class EnterEncounter extends Encounter
{
	public EnterEncounter(World world, int strength)
	{
		super(world, "Do you want to enter the pirates Ship?",
				new Solution[] { new EnterEven(strength), new NoFight() });
	}

	static class EnterEven implements Solution
	{

		int strength;

		public EnterEven(int strength)
		{
			this.strength = strength;
		}

		@Override
		public String[] resolve(World w)
		{
			String[] results;

			if (((int) (Math.random() * (w.player.totalCrewAbility() + strength)) - strength) < 0)
			{
				results = new String[5];
				results[0] = "You entered the pirates but they defeated your crew";
				int damage = ((int) (Math.random() * 15) + 5);
				w.player.health -= damage;
				results[1] = "Health: -" + damage;
				int moralloss = ((int) (Math.random() * 7) + 20);
				w.player.moral -= moralloss;
				results[2] = "Moral: -" + moralloss;
				int foodloss = ((int) (Math.random() * 10) + 4);
				w.player.food -= foodloss;
				results[3] = "Food: -" + foodloss;
				// skillLoss: calculates remaining crew in % and multiplies by
				// current skill
				int skillLoss = (int) (w.player.skill * (Math.random() * 5 + 4) / 30.0);
				w.player.skill -= skillLoss;
				results[4] = "Skill: -" + skillLoss;
			} else
			{
				results = new String[4];
				results[0] = "You defeated the pirates and looted their cargo";
				int damage = ((int) (Math.random() * 5) + 1);
				w.player.health -= damage;
				results[1] = "Health: -" + damage;
				int moralGain = ((int) (Math.random() * 7) + 20);
				w.player.moral += moralGain;
				results[2] = "Moral: +" + moralGain;
				int foodGain = ((int) (Math.random() * 10) + 20);
				w.player.food += foodGain;
				results[3] = "Food: +" + foodGain;
			}
			return results;
		}

		@Override
		public String getText()
		{
			return "Enter the Pirates";
		}
	}

	static class NoFight implements Solution
	{

		String[] results;

		@Override
		public String[] resolve(World w)
		{
			results = new String[4];
			results[0] = "You defeated the pirates";
			int damage = ((int) (Math.random() * 5) + 1);
			w.player.health -= damage;
			results[1] = "Health: -" + damage;
			int moralGain = ((int) (Math.random() * 5) + 5);
			w.player.moral += moralGain;
			results[2] = "Moral: +" + moralGain;
			int foodGain = ((int) (Math.random() * 2) + 5);
			w.player.food += foodGain;
			results[3] = "Food: +" + foodGain;

			return results;
		}

		@Override
		public String getText()
		{
			return "Don't enter the Pirates";
		}

	}

}
