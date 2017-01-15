package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class TextBox
{
	private final int NEXT_LINE = 5;

	public Rectangle rect;

	public String text;

	String[] textlines;

	public TextBox(int posx, int posy, int width, int height, String text)
	{

		rect = new Rectangle(posx, posy, width, height);
		this.text = text;

	}

	public void draw(Graphics g)
	{
		g.setColor(Color.DARK_GRAY);
		g.fillRoundRect(rect.x+5, rect.y+5, rect.width, rect.height, 15,15);
		g.setColor(Color.WHITE);
		g.fillRoundRect(rect.x, rect.y, rect.width, rect.height, 15,15);
		g.setColor(Color.BLACK);
		g.drawRoundRect(rect.x, rect.y, rect.width, rect.height,15,15);
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
