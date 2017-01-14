
package encounter;

import assets.Texture;
import world.World;

public class TribeHostile extends Encounter
{

	public TribeHostile(World w)
	{
		super(w, null, "However the tribe seems Hostile", new Solution[] {}, 0, 0, 1, 0);

	}

	static class AvoidConflict implements Solution
	{
		int strength;

		public AvoidConflict(int strength)
		{
			this.strength = strength;
		}

		public String[] resolve(World w)
		{
			String[] results;

			int sneakResult = ((int) (Math.random() * (w.player.totalCrewAbility() + strength))) - strength;
			if (sneakResult < 0)
			{
				results = new String[3];
				results[0] = "You try to leave, but tribe sees you and hunts down your crew/nbefore you can leave";
				int skillLoss = (int) (w.player.getSkill() * (Math.random() * 5 + 3) / 30.0);
				w.player.changeSkill(-skillLoss);
				results[1] = "Skill: -" + skillLoss;
				int moralLoss = (int) (Math.random() * 5 + 5);
				results[2] = "Moral: -" + moralLoss;
				w.player.changeMoral(-moralLoss);
			} else
			{
				results = new String[1];
				results[0] = "You manage to leave without being seen";
			}
			return results;
		}

		@Override
		public String getText()
		{
			return "Avoid conflict";
		}

	}

	static class AttackTribe implements Solution
	{

		int strength;

		public AttackTribe(int strength)
		{
			this.strength = strength;
		}

		@Override
		public String[] resolve(World w)
		{
			String[] results;

			int sneakResult = ((int) (Math.random() * (w.player.totalCrewAbility() + strength))) - strength;
			if (sneakResult < 0)
			{
				results = new String[3];
				results[0] = "You fight valiantly, but were defeated";
				int skillLoss = (int) (w.player.getSkill() * (Math.random() * 5 + 3) / 30.0);
				w.player.changeSkill(-skillLoss);
				results[1] = "Skill: -" + skillLoss;
				int moralLoss = (int) (Math.random() * 5 + 5);
				results[2] = "Moral: -" + moralLoss;
				w.player.changeMoral(-moralLoss);

			} else
			{
				results = new String[5];
				results[0] = "You defeat the tribe and take their resources";
				int skillGain = (int) (Math.random() * 5 + 5);
				w.player.changeSkill(+skillGain);
				results[1] = "Skill: -" + skillGain;
				int moralGain = (int) (Math.random() * 5 + 5);
				results[2] = "Moral: +" + moralGain;
				w.player.changeMoral(+moralGain);
				int foodGain = (int) (Math.random() * 5 + 5);
				results[3] = "Food: +" + foodGain;
				w.player.changeFood(+foodGain);
				results[4] = "Resources: +";// TODO add resources
			}
			return results;
		}

		@Override
		public String getText()
		{
			return "Attack Tribe!";
		}
	}

}
