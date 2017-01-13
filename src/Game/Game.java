package Game;

import java.awt.Graphics;

/**
 * Manages the game loop and initialisation of the game
 * 
 * Created by Jonathan Kohlhas on 13.01.17.
 */
public class Game
{

	public static Display display;

	public static GameState state;

	private static Graphics g;

	public static boolean running;

	public static final int FPS = 60;

	public static final int HEIGHT = 480;

	public static final int WIDTH = 640;

	private enum GameState
	{
		RUNNING, STOPED;
	}

	public static void main(String[] args)
	{
		init();
		run(1 / FPS);
	}

	/**
	 * Initialises all required variables and Objects to run the game
	 */
	private static void init()
	{
		running = true;
		display = new Display(WIDTH, HEIGHT);
		state = GameState.RUNNING;
		InputManager.addButton(button);
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

	/**
	 * Updates all values in the game;
	 *
	 * @param delta
	 */
	private static void update(double delta)
	{
		// TODO update world, player, encounter
	}

	/**
	 * Renders the game in 60 FPS
	 */

	static class exitButton implements ButtonCall{

        @Override
        public void call() {
            System.out.print("Test");
            System.exit(0);
        }
    }

    static Button button=new Button(0, 0, 100, 100, "Herro", new exitButton());

	private static void render()
	{
		g = display.getBackBuffer();
		button.draw(g);
		display.flipBuffers();
		// TODO render player(ship n stuff),world etc.
	}
}
