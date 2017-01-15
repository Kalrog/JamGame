package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Button
{
	private final int NEXT_LINE = 5;

	ButtonCall execute;

	String text;

	String[] textlines;

	Rectangle rect;

	public Button(int posx, int posy, int width, int height, String text, ButtonCall execute)
	{

		this.text = text;

		this.execute = execute;
		rect = new Rectangle(posx, posy, width, height);
		InputManager.addButton(this);
	}

	public boolean clicked(int posx, int posy)
	{
		if (rect.contains(new Point(posx, posy)))
		{
			execute.call();

			return true;
		}

		return false;
	}

	public void draw(Graphics g)
	{
		g.setColor(Color.DARK_GRAY);
		g.fillRoundRect(rect.x + 5, rect.y + 5, rect.width, rect.height, 15, 15);
		g.setColor(Color.ORANGE);
		g.fillRoundRect(rect.x, rect.y, rect.width, rect.height, 15, 15);
		g.setColor(Color.BLACK);
		textlines = text.split("/n");
		for (int x = 0; x < textlines.length; x++)
		{
			g.drawString(textlines[x], rect.x + 10, (int) (rect.y + (rect.getHeight()
					- (textlines.length * g.getFontMetrics().getHeight() + (textlines.length - 1) * NEXT_LINE) / 2.0)));

		}
	}

	public void dispose()
	{
		InputManager.removeButton(this);
	}
}
