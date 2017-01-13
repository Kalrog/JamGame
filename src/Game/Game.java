package Game;

/**
 * Manages the game loop and initialisation of the game
 * 
 * Created by Jonathan Kohlhas on 13.01.17.
 */
public class Game
{

	public static Display display;

	public static GameState state;

	public static boolean running;
	
	public static final int FPS = 60;

	private enum GameState
	{
		RUNNING, STOPED;
	}

	public static void main(String[] args)
	{
		init();
		run(1/FPS);
	}

	/**
	 * Initialises all required variables and Objects to run the game
	 */
	private static void init()
	{
		running = true;
		display = new Display();
		state = GameState.RUNNING;

	}

	/**
	 * Begin the game loop
	 * 
	 * @param delta
	 *            time between logic updates (in seconds)
	 */
	public static void run(double delta)
	{
		// convert the time to seconds
		double nextTime = (double) System.nanoTime() / 1000000000.0;
		while (running)
		{
			// convert the time to seconds
			double currTime = (double) System.nanoTime() / 1000000000.0;
			if (currTime >= nextTime)
			{
				// assign the time for the next update
				update(delta + Math.abs((nextTime - currTime)));
				nextTime += delta;
				render();
			} else
			{
				// calculate the time to sleep
				int sleepTime = (int) (1000.0 * (nextTime - currTime));
				// sanity check
				if (sleepTime > 0)
				{
					// sleep until the next update
					try
					{
						Thread.sleep(sleepTime);
					} catch (InterruptedException e)
					{
						// do nothing
					}
				}
			}
		}
		shutdown();
	}

	private static void shutdown()
	{
		// TODO Close Display et%c.

	}

	/**
	 * Updates all values in the game;
	 * @param delta 
	 */
	private static void update(double delta)
	{
		// TODO update world, player, encounter
	}

	/**
	 * Renders the game in 60 FPS
	 */
	private static void render()
	{
		// TODO render player(ship n stuff),world etc.
	}
}
