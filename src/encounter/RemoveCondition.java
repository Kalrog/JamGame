package encounter;

import world.World;

public class RemoveCondition extends Encounter
{

	public RemoveCondition(World w, String text, int distance)
	{
		super(w, text, new Solution[] {}, 0, w.player.getDistance() + distance, 20, 0);
	}

	static class RemoveEffect implements Solution
	{

		@Override
		public String[] resolve(World w)
		{
			w.player.condition = null;
			return null;
		}

		@Override
		public String getText()
		{
			return "Continue";
		}

	}

}
