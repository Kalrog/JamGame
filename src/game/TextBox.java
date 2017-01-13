package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class TextBox
{
	private final int NEXT_LINE = 5;

	public Rectangle rect;

	String text;

	String[] textlines;

	public TextBox(int posx, int posy, int width, int height, String text)
	{

		rect = new Rectangle(posx, posy, width, height);
		this.text = text;

	}

	public void draw(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(rect.x, rect.y, rect.width, rect.height);
		g.setColor(Color.BLACK);
		g.drawRect(rect.x, rect.y, rect.width, rect.height);
		textlines = text.split("/n");
		// TODO Split lines at /n and draw
		// g.drawString(text, rect.x + 10, rect.y + rect.height / 2);
		for (int x = 0; x < textlines.length; x++)
		{
			g.drawString(textlines[x], rect.x + 10,
					rect.y + 20 + x * NEXT_LINE + (x == 0 ? 0 :x * g.getFontMetrics().getHeight()));
		}

	}
}
