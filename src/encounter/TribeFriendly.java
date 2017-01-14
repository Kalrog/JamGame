package encounter;

import assets.Texture;
import world.World;

public class TribeFriendly extends Encounter
{

	public TribeFriendly(World w)
	{
		super(w, null, "They aproach you and wish to Trade", new Solution[] {}, 0, 0, 1, 0);
	}
}
