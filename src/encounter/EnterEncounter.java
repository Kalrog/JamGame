package encounter;

import world.World;

public class EnterEncounter extends Encounter
{
	public EnterEncounter(World world, int strength)
	{
		super(world, "Do you want to enter the pirates Ship?", new Solution[] { new enterEvent(strength) });
	}

	static class enterEvent implements Solution
	{

		int strength;

		public enterEvent(int strength)
		{
			this.strength = strength;
		}

		@Override
		public String[] resolve(World w)
		{
			String[] results;

			if (((int) (Math.random() * (w.player.totalCrewAbility() + strength)) - strength) < 0)
			{
				results = new String[4];
				results[0] = "You entered th pirates but they defeated your crew";
				int damage = ((int) Math.random() * 15 + 5);
				w.player.health -= damage;
				results[1] = "Health: -" + damage;
				int moralloss = ((int) Math.random() * 7 + 20);
				w.player.moral -= moralloss;
				results[2] = "Moral: -" + moralloss;
				int foodloss = ((int) Math.random() * 10 + 4);
				w.player.food -= foodloss;
				results[3] = "Food: -" + foodloss;
			} else
			{
				results = new String[4];
				results[0] = "You defeated the pirates and looted their cargo";
				int damage = ((int) Math.random() * 5 + 1);
				w.player.health -= damage;
				results[1] = "Health: -" + damage;
				int moralGain = ((int) Math.random() * 7 + 20);
				w.player.moral += moralGain;
				results[2] = "Moral: +" + moralGain;
				int foodGain = ((int) Math.random() * 10 + 20);
				w.player.food += foodGain;
				results[3] = "Food: +" + foodGain;
			}
			return results;
		}

		@Override
		public String getText()
		{
			// TODO Auto-generated method stub
			return "Enter the Pirates";
		}
	}

	static class noFight implements Solution
	{
		
		String[] results;

		@Override
		public String[] resolve(World w)
		{
			results = new String[4];
			results[0] = "You defeated the pirates";
			int damage = ((int) Math.random() * 5 + 1);
			w.player.health -= damage;
			results[1] = "Health: -" + damage;
			int moralGain = ((int) Math.random() * 5 + 5);
			w.player.moral += moralGain;
			results[2] = "Moral: +" + moralGain;
			int foodGain = ((int) Math.random() * 2 + 5);
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
