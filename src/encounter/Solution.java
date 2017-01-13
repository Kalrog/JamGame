package encounter;

import world.World;

/**
 * Base for all solutions to an encounter
 * 
 * @author Niklas Speckels
 */
public interface Solution
{

	public String [] resolve(World w);

	public String getText();
}
