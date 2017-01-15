package encounter;

import assets.Texture;
import game.Game;
import world.World;

public class DeathEncounter extends Encounter
{

	public DeathEncounter(World w)
	{
		super(w, null, "You died!", new Solution[] { new Death() }, 0, 0, 100, 0);
		// TODO Auto-generated constructor stub
	}

	static class Death implements Solution
	{

		@Override
		public String[] resolve(World w)
		{
			Game.prepareShutdown();
			return null;
		}

		@Override
		public String getText()
		{
			// TODO Auto-generated method stub
			return "Continue";
		}

	}

}
