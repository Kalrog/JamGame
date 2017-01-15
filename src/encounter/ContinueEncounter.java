package encounter;

import assets.Texture;
import world.World;

public class ContinueEncounter extends Encounter
{

	String[] results;

	public ContinueEncounter(World w, String[] results)
	{
		super(w, null, "", null, 0, 0, 1, 0);
		this.results = results;

	}

	@Override
	public void startEncounter()
	{
		this.showResult(results);
		world.addActiveEncounter(this);
	}

}
