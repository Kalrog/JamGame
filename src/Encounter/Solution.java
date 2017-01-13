package Encounter;

import World.World;

/**
 * Base for all solutions to an encounter
 * 
 * @author Niklas Speckels
 */
public interface Solution
{

	public void resolve(World w);

	public String getText();
}
