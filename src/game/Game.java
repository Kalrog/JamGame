package game;

import java.awt.Graphics;

import assets.AssetLoader;
import encounter.Encounter;
import player.Player;
import world.World;

/**
 * Manages the game loop and initialisation of the game
 * 
 * Created by Jonathan Kohlhas on 13.01.17.
 */
public class Game
{

	public static Display display;

	public static GameState state;

	private static World world;

	private static Player player;

	private static Graphics g;

	public static boolean running;

	public static final int FPS = 120;

	public static final int HEIGHT = 480;

	public static final int WIDTH = 640;

	public static final int SEE_LEVEL = 350;

	static Encounter encounter;

	private enum GameState
	{
		RUNNING, STOPPED;
	}

	public static void main(String[] args)
	{
	    AssetLoader.loadTextures();
		init();
		run(1 / (double)FPS);
	}

	// test class
	/*static class testSolution implements Solution
	{

		@Override
		public String[] resolve(World w)
		{
			return new String[] { "Flavor", "TEST1", "REKT2", "NOW3", "AND4" };
		}

		@Override
		public String getText()
		{
			return "Ayy Lmao";
		}

	}*/

	// end of test class

	/**
	 * Initialises all required variables and Objects to run the game
	 */
	private static void init()
	{
		running = true;
		display = new Display(WIDTH, HEIGHT);
		state = GameState.RUNNING;
		player = new Player("Kalrog");
		world = new World(player, 2000);
		// InputManager.addButton(button);

		// these are for testing
		/*
		 * encounter = new Encounter(new World(null, 0),
		 * "This is a test foar encounters", new Solution[] { new
		 * testSolution(), new testSolution() });
		 */
		

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
				// assign the time for the next updateAndRender
				update();
				nextTime += delta;
				render();
			} else
			{
				// calculate the time to sleep
				int sleepTime = (int) (1000.0 * (nextTime - currTime));
				// sanity check
				if (sleepTime > 0)
				{
					// sleep until the next updateAndRender
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

	public static void prepareShutdown()
	{
		running = false;
	}

	private static void shutdown()
	{
		// TODO Close Display etc.
		display.dispose();
		System.exit(0);
	}

	private static void update()
	{
		//world.updateAndRender();
		// TODO updateAndRender world, player, encounter
	}

	/**
	 * Renders the game in 60 FPS
	 */

	static class exitButton implements ButtonCall
	{

		@Override
		public void call()
		{
			System.out.print("Test");
			System.exit(0);
		}
	}

	//static Button button = new Button(200, 200, 100, 100, "Herro", new exitButton());

	//static TextBox box = new TextBox(0, 0, 200, 100, "Hello this is a test for my textbox");

	private static void render()
	{
            g = display.getBackBuffer();
            world.updateAndRender(g);
            //world.draw(g);
            // button.draw(g);
            // box.draw(g);
            display.flipBuffers();
            // TODO render player(ship n stuff),world etc.
	}
}
