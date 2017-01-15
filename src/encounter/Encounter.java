package encounter;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Comparator;

import assets.Texture;
import game.*;
import world.World;

/**
 * Created by jonathan on 13.01.17.
 */
public class Encounter
{
	private final int MAINBOX_X = (int) Math.round(Display.canvas.getWidth() / 8.0);

	private final int MAINBOX_Y = (int) Math.round(Display.canvas.getHeight() / 8.0);

	private final int MAINBOX_WIDTH = (int) Math.round(Display.canvas.getWidth() * 0.75);

	private final int MAINBOX_HEIGHT = (int) Math.round(Display.canvas.getHeight() * 0.75);

	private final int TEXT_X = MAINBOX_X + 20;

	private final int TEXT_Y = MAINBOX_Y + 15;

	private final int TEXT_WIDTH = MAINBOX_WIDTH - 40;

	private final int TEXT_HEIGHT = 100;

	private final int BUTTON_X = TEXT_X;

	private final int BUTTON_Y = MAINBOX_Y + TEXT_HEIGHT + 32;

	private final int BUTTON_WIDTH = TEXT_WIDTH;

	private final int BUTTON_HEIGHT = 50;

	private final int BOX_DIFF = BUTTON_HEIGHT + 16;

	protected World world;

	public String text;

	public Texture texture;

	public Solution[] solutions;

	protected Button[] buttons;

    protected TextBox[] texts;

	public int chance;

	public int distance;

	public int priority;

	public int cooldown;

	protected State state;

	protected enum State
	{
		STARTED, RESULT, INACTIVE, COMPLETED
	}

	class SolutionButton implements ButtonCall
	{
		private Solution solution;

		public SolutionButton(Solution solution)
		{
			this.solution = solution;
		}

		@Override
		public void call()
		{
			Encounter.this.showResult(solution.resolve(world));
		}
	}

	class ContinueButton implements ButtonCall
	{

		@Override
		public void call()
		{
			Encounter.this.endEncounter();

		}

	}

	public Encounter(World world, Texture texture, String text, Solution[] solutions, int chance, int distance,
			int priority, int cooldown)
	{
		this.world = world;
		this.texture = texture;
		this.text = text;
		this.solutions = solutions;
		this.state = State.INACTIVE;
		this.chance = chance;
		this.distance = distance;
		this.priority = priority;
		this.cooldown = cooldown;
	}

	public void startEncounter()
	{
		this.state = State.STARTED;

		buttons = new Button[solutions.length];

		texts = new TextBox[1];

		if (solutions.length <= 3)
			for (int x = 0; x < solutions.length; x++)
			{
				buttons[x] = new Button(BUTTON_X, BUTTON_Y + x * BOX_DIFF, BUTTON_WIDTH, BUTTON_HEIGHT,
						solutions[x].getText(), getSolutionButton(solutions[x]));
			}
		else if (solutions.length <= 5)
			buttons = fourSmallButtons(solutions);
		else if (solutions.length <= 7) buttons = sixSmallButtons(solutions);

		// for (int x = 0; x < solutions.length; x++)
		// InputManager.addButton(buttons[x]);

		texts[0] = new TextBox(TEXT_X, TEXT_Y, TEXT_WIDTH, TEXT_HEIGHT, text);

		world.addActiveEncounter(this);

	}

	protected void showResult(String[] results)
	{

		if (buttons != null) for (Button button : buttons)
		{
			button.dispose();
		}

		if (results != null && results.length > 0)
		{
			this.state = State.RESULT;

			texts = new TextBox[results.length];

			texts = fourSmallBoxes(results);

			buttons = new Button[] { new Button(BUTTON_X, BUTTON_Y + 2 * BOX_DIFF, BUTTON_WIDTH, BUTTON_HEIGHT,
					"Continue", new ContinueButton()) };

			// InputManager.addButton(buttons[0]);
		} else
			endEncounter();
	}

	private TextBox makeStandartSmallBox(int x, int y, String text)
	{
		return new TextBox(x, y, BUTTON_WIDTH / 2 - BOX_DIFF / 4, BUTTON_HEIGHT, text);
	}

	private Button makeStandartSmallButton(int x, int y, Solution solution)
	{
		return new Button(x, y, BUTTON_WIDTH / 2 - BOX_DIFF / 4, BUTTON_HEIGHT, solution.getText(),
				getSolutionButton(solution));
	}

	private void endEncounter()
	{
		this.state = State.COMPLETED;
		buttons[0].dispose();
		// InputManager.removeButton(buttons[0]);
		world.removeActiveEncounter(this);
	}

	public void draw(Graphics g)
	{

		switch (state)
		{
			case STARTED:
			{
				mainBoxDraw(g);
				g.setColor(Color.WHITE);
				texts[0].draw(g);
				for (Button button : buttons)
				{
					button.draw(g);
				}

				break;

			}

			case RESULT:
			{
				mainBoxDraw(g);
				g.setColor(Color.WHITE);

				for (TextBox box : texts)
				{
					box.draw(g);
				}

				for (Button button : buttons)
				{
					button.draw(g);
				}

				break;

			}

			case COMPLETED:
			case INACTIVE:
			{
				if (texture != null)
					texture.draw(g, world.player.getDistance() - distance + Game.display.getWidth(), Game.SEE_LEVEL);
				break;
			}

		}
	}

	private void mainBoxDraw(Graphics g)
	{
        g.setColor(Color.DARK_GRAY);
        g.fillRoundRect(MAINBOX_X + 5, MAINBOX_Y +5 , MAINBOX_WIDTH, MAINBOX_HEIGHT,15,15);
		g.setColor(Color.GRAY);
		g.fillRoundRect(MAINBOX_X, MAINBOX_Y, MAINBOX_WIDTH, MAINBOX_HEIGHT,15,15);
	}

	private TextBox[] fourSmallBoxes(String[] strings)
	{
		TextBox[] texts = new TextBox[strings.length];

		for (int x = 0; x < strings.length; x++)
		{
			switch (x)
			{
				case 0:
				{
					texts[x] = new TextBox(TEXT_X, TEXT_Y, TEXT_WIDTH, TEXT_HEIGHT, strings[x]);
					break;
				}
				case 1:
				{
					texts[x] = makeStandartSmallBox(BUTTON_X, BUTTON_Y, strings[x]);
					break;
				}
				case 2:
				{
					texts[x] = makeStandartSmallBox(BUTTON_X + BUTTON_WIDTH / 2 + BOX_DIFF / 4, BUTTON_Y, strings[x]);
					break;
				}
				case 3:
				{
					texts[x] = makeStandartSmallBox(BUTTON_X, BUTTON_Y + BOX_DIFF, strings[x]);
					break;
				}
				case 4:
				{
					texts[x] = makeStandartSmallBox(BUTTON_X + BUTTON_WIDTH / 2 + BOX_DIFF / 4, BUTTON_Y + BOX_DIFF,
							strings[x]);
					break;
				}
			}

		}

		return texts;
	}

	private Button[] fourSmallButtons(Solution[] solutions)
	{
		Button[] buttons = new Button[solutions.length];

		for (int x = 0; x < solutions.length; x++)
		{
			switch (x)
			{
				case 0:
				{
					buttons[x] = makeStandartSmallButton(BUTTON_X, BUTTON_Y, solutions[x]);
					break;
				}
				case 1:
				{
					buttons[x] = makeStandartSmallButton(BUTTON_X + BUTTON_WIDTH / 2 + BOX_DIFF / 4, BUTTON_Y,
							solutions[x]);
					break;
				}
				case 2:
				{
					buttons[x] = makeStandartSmallButton(BUTTON_X, BUTTON_Y + BOX_DIFF, solutions[x]);
					break;
				}
				case 3:
				{
					buttons[x] = makeStandartSmallButton(BUTTON_X + BUTTON_WIDTH / 2 + BOX_DIFF / 4,
							BUTTON_Y + BOX_DIFF, solutions[x]);
					break;
				}

			}

		}

		return buttons;
	}

	private Button[] sixSmallButtons(Solution[] solutions)
	{
		Button[] buttons = new Button[solutions.length];

		for (int x = 0; x < solutions.length; x++)
		{
			switch (x)
			{
				case 0:
				{
					buttons[x] = makeStandartSmallButton(BUTTON_X, BUTTON_Y, solutions[x]);
					break;
				}
				case 1:
				{
					buttons[x] = makeStandartSmallButton(BUTTON_X + BUTTON_WIDTH / 2 + BOX_DIFF / 4, BUTTON_Y,
							solutions[x]);
					break;
				}
				case 2:
				{
					buttons[x] = makeStandartSmallButton(BUTTON_X, BUTTON_Y + BOX_DIFF, solutions[x]);
					break;
				}
				case 3:
				{
					buttons[x] = makeStandartSmallButton(BUTTON_X + BUTTON_WIDTH / 2 + BOX_DIFF / 4,
							BUTTON_Y + BOX_DIFF, solutions[x]);
					break;
				}
				case 4:
				{
					buttons[x] = makeStandartSmallButton(BUTTON_X, BUTTON_Y + 2 * BOX_DIFF, solutions[x]);
					break;
				}
				case 5:
				{
					buttons[x] = makeStandartSmallButton(BUTTON_X + BUTTON_WIDTH / 2 + BOX_DIFF / 4,
							BUTTON_Y + 2 * BOX_DIFF, solutions[x]);
					break;
				}
			}

		}

		return buttons;
	}

	public static Comparator<Encounter> getComparator()
	{
		return new Comparator<Encounter>()
		{
			@Override
			public int compare(Encounter encounter, Encounter t1)
			{
				int diff = encounter.distance - t1.distance;

				if (diff == 0)
				{
					if (encounter.priority > t1.priority) return -1;
					return 1;
				} else
					return diff;
			}
		};
	}

	protected ButtonCall getSolutionButton(Solution solution)
    {
        return new SolutionButton(solution);
    }
}
