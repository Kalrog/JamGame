package encounter;

import player.Player.Condition;
import world.World;

public class RemoveCondition extends Encounter
{

	private static Condition condition;
	
	public RemoveCondition(World w, String text, int distance, Condition condition)
	{
		super(w,null, text, new Solution[] {}, 0, w.player.getDistance() + distance, 20, 0);
		this.condition = condition;
		w.addWorldEncounter(this);
	}

	static class RemoveEffect implements Solution
	{

		@Override
		public String[] resolve(World w)
		{
			w.player.removeCondition(condition);
			return null;
		}

		@Override
		public String getText()
		{
			return "Continue";
		}

	}

	public static Condition getCondition()
	{
		return condition;
	}
}
