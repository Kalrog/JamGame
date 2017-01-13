package encounter;

import java.awt.Color;
import java.awt.Graphics;

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

	private World world;

	private String text;

	private Solution[] solutions;

	private Button[] buttons;

	private TextBox[] texts;

	public int chance;

	private State state;

	private enum State
	{
		STARTED, RESULT, INACTIVE
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

	class ContiniueButton implements ButtonCall
	{

		@Override
		public void call()
		{
			Encounter.this.endEncounter();

		}

	}

	public Encounter(World world, String text, Solution[] solutions)
	{
		this.world = world;
		this.text = text;
		this.solutions = solutions;
		this.state = State.INACTIVE;
	}

	public void startEncounter()
	{
		this.state = State.STARTED;

		buttons = new Button[solutions.length];

		texts = new TextBox[1];

		for (int x = 0; x < solutions.length; x++)
		{
			buttons[x] = new Button(BUTTON_X, BUTTON_Y + x * BOX_DIFF, BUTTON_WIDTH, BUTTON_HEIGHT,
					solutions[x].getText(), new SolutionButton(solutions[x]));
			InputManager.addButton(buttons[x]);
		}

		texts[0] = new TextBox(TEXT_X, TEXT_Y, TEXT_WIDTH, TEXT_HEIGHT, text);

	}

	public void showResult(String[] results)
	{
		this.state = State.RESULT;

		texts = new TextBox[results.length];

		for (Button button : buttons)
		{
			InputManager.removeButton(button);
		}

		for (int x = 0; x < results.length; x++)
		{
			switch (x)
			{
				case 0:
				{
					texts[x] = new TextBox(TEXT_X, TEXT_Y, TEXT_WIDTH, TEXT_HEIGHT, results[x]);
					break;
				}
				case 1:
				{
					texts[x] = makeStandartSmallBox(BUTTON_X, BUTTON_Y, texts[x], results[x]);
					break;
				}
				case 2:
				{
					texts[x] = makeStandartSmallBox(BUTTON_X + BUTTON_WIDTH / 2 + BOX_DIFF / 4, BUTTON_Y, texts[x],
							results[x]);
					break;
				}
				case 3:
				{
					texts[x] = makeStandartSmallBox(BUTTON_X, BUTTON_Y + BOX_DIFF, texts[x], results[x]);
					break;
				}
				case 4:
				{
					texts[x] = makeStandartSmallBox(BUTTON_X + BUTTON_WIDTH / 2 + BOX_DIFF / 4, BUTTON_Y + BOX_DIFF,
							texts[x], results[x]);
					break;
				}
			}

		}

		buttons = new Button[] { new Button(BUTTON_X, BUTTON_Y + 2 * BOX_DIFF, BUTTON_WIDTH, BUTTON_HEIGHT, "Continue",
				new ContiniueButton()) };

		InputManager.addButton(buttons[0]);
	}

	public TextBox makeStandartSmallBox(int x, int y, TextBox box, String text)
	{
		return box = new TextBox(x, y, BUTTON_WIDTH / 2 - BOX_DIFF / 4, BUTTON_HEIGHT, text);
	}

	public void endEncounter()
	{
		this.state = State.INACTIVE;
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

			case INACTIVE:
			{
				break;
			}

		}
	}

	public void mainBoxDraw(Graphics g)
	{
		g.setColor(Color.GRAY);
		g.fillRect(MAINBOX_X, MAINBOX_Y, MAINBOX_WIDTH, MAINBOX_HEIGHT);
	}
}
