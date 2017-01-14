package encounter;

import world.World;

public class EnterEncounter extends Encounter
{
	public EnterEncounter(World world, int strength)
	{
		super(world,null, "Do you want to enter the pirates Ship?",
				new Solution[] { new EnterEven(strength), new NoFight()},  0 , 0 , 0 , 0);
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
				w.player.changeHealth(w.player.getHealth() - damage);
				results[1] = "Health: -" + damage;
				int moralLoss = ((int) (Math.random() * 7) + 20);
				w.player.changeMoral(w.player.getMoral() - moralLoss);
				results[2] = "Moral: -" + moralLoss;
				int foodLoss = ((int) (Math.random() * 10) + 4);
				w.player.changFood(w.player.getFood() + foodLoss);
				results[3] = "Food: -" + foodLoss;
				// skillLoss: calculates remaining crew in % and multiplies by
				// current skill
				int skillLoss = (int) (w.player.getSkill() * (Math.random() * 5 + 4) / 30.0);
				w.player.changeSkill(w.player.getSkill()- skillLoss);
				results[4] = "Skill: -" + skillLoss;
			} else
			{
				results = new String[4];
				results[0] = "You defeated the pirates and looted their cargo";
				int damage = ((int) (Math.random() * 5) + 1);
				w.player.changeHealth(w.player.getHealth() - damage);
				results[1] = "Health: -" + damage;
				int moralGain = ((int) (Math.random() * 7) + 20);
				w.player.changeMoral(w.player.getMoral() + moralGain);
				results[2] = "Moral: +" + moralGain;
				int foodGain = ((int) (Math.random() * 10) + 20);
				w.player.changFood(w.player.getFood() + foodGain);
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
			w.player.changeHealth(w.player.getHealth() - damage);
			results[1] = "Health: -" + damage;
			int moralGain = ((int) (Math.random() * 5) + 5);
			w.player.changeMoral(w.player.getMoral() + moralGain);
			results[2] = "Moral: +" + moralGain;
			int foodGain = ((int) (Math.random() * 2) + 5);
			w.player.changFood(w.player.getFood() + foodGain);
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
