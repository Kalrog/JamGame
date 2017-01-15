
package encounter;

import assets.AssetLoader;
import player.Player.Condition;
import world.World;

public class IslandEncounter extends Encounter
{

	private static World w;

	public IslandEncounter(World w, int distance)
	{
		super(w, AssetLoader.getRandomIslandTexture(),
				"Your lookout spotted an island in the distance/nExplore the island?", null, 5, distance, 1, 300);
		this.solutions = new Solution[] { new goIsland(this), new leaveIsland() };
		this.w = w;
	}

	static class goIsland implements Solution
	{

		Encounter e;

		public goIsland(Encounter e)
		{
			this.e = e;
		}

		String[] results;

		@Override
		public String[] resolve(World w)
		{
			int randome = 2;// ThreadLocalRandom.current().nextInt(0, 3);

			switch (randome)
			{
				case 0:
					results = manyRecources();
					break;
				case 1:
					results = disease();
					break;
				case 2:
					results = tribe();
					break;
				case 3:
					results = shark();
					break;
				case 4:
					results = volcano();
					break;
				case 5:
					results = mysticalTemple(e);
					break;
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

		results[0] = "The Island has plenty of resources to scavenge";
		int foodGain = ((int) (Math.random() * 4) + 7);
		results[1] = "Food: +" + foodGain;
		w.player.changeFood(+foodGain);
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
		new RemoveCondition(w, "Your Crew overcame their illness", untilCured,
				Condition.ILL);
		results[2] = "Condition: Illness";
		w.player.addCondition(Condition.ILL);
		return results;
	}

	private static String[] shark()
	{
		String[] results = new String[3];
		results[0] = "Your crew tries to reach the Island but were atacked by sharks";
		int skillLoss = (int) (w.player.getSkill() * (Math.random() * 5 + 3) / 30.0);
		w.player.changeSkill(-skillLoss);
		results[1] = "Skill: -" + skillLoss;
		int moralLoss = (int) (Math.random() * 5 + 5);
		results[2] = "Moral; -" + moralLoss;
		w.player.changeMoral(-moralLoss);

		return results;
	}

	private static String[] tribe()
	{
		Encounter encounter;

		if (Math.random() >= 0.5)
		{
			//encounter = new TribeFriendly(w);
			encounter = new ShopEncounter(w, null, "They aproach you and wish to Trade", null);
		} else
		{
			encounter = new TribeHostile(w, (int) (w.player.totalCrewAbility() * (Math.random() + 0.5)));
		}

		encounter.startEncounter();

		new ContinueEncounter(w, new String[] { "You see a native Tribe living on the Island" }).startEncounter();

		return null;

	}

	private static String[] volcano()
	{

		String[] results = new String[3];
		results[0] = "As yor crew explores the Island a volcano errupts/n and many people of your crew lose their lives";
		int skillLoss = (int) (w.player.getSkill() * (Math.random() * 5 + 4) / 30.0);
		w.player.changeSkill(-skillLoss);
		results[1] = "Skill: -" + skillLoss;
		int moralLoss = (int) (Math.random() * 6 + 7);
		w.player.changeMoral(-moralLoss);
		results[2] = "Moral; -" + moralLoss;
		int healthLoss = (int) (Math.random() * 6 + 7);
		w.player.changeHealth(-healthLoss);
		results[3] = "Health: -" + healthLoss;

		return results;
	}

	private static String[] mysticalTemple(Encounter e)
	{
		String[] results = new String[3];
		results[0] = "You find an ancient temple ruin/nand your crew is blessed by it's godly power";
		new RemoveCondition(w, "Your blessing wore of", 10, Condition.BLESSED);
		if (w.player.conditions.contains(Condition.ILL))
		{
			for (Encounter encounter : w.getWorldEncounters())
			{
				if (encounter instanceof RemoveCondition
						&& ((RemoveCondition) encounter).getCondition() == Condition.ILL)
				{
					w.removeWorldEncounter(encounter);
					break;
				}
			}
			w.player.removeCondition(Condition.ILL);
		}

		if (w.player.conditions.contains(Condition.SEASICK)) w.player.removeCondition(Condition.SEASICK);
		for (Encounter encounter : w.getWorldEncounters())
		{
			if (encounter instanceof RemoveCondition
					&& ((RemoveCondition) encounter).getCondition() == Condition.SEASICK)
			{
				w.removeWorldEncounter(encounter);
				break;
			}
		}
		w.player.addCondition(Condition.BLESSED);
		results[1] = "Condition: Blessed";
		int moralGain = (int) (Math.random() * 5 + 7);
		results[2] = "Moral: +" + moralGain;
		w.player.changeMoral(+moralGain);

		Encounter encounter = new ContinueEncounter(w, results);
		encounter.startEncounter();

		return manyRecources();
	}

}
