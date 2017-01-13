package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class TextBox
{

	Point point;

	String text;

	public TextBox(int posx, int posy, String text)
	{

		point = new Point(posx, posy);
		this.text = text;

	}

	public void draw(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(point.x, point.y, g.getFontMetrics().stringWidth(text) + 20, g.getFontMetrics().getHeight() + 20);
		g.setColor(Color.BLACK);
		g.drawRect(point.x, point.y, g.getFontMetrics().stringWidth(text) + 20, g.getFontMetrics().getHeight() + 20);
		g.drawString(text, point.x + 10, point.y + (g.getFontMetrics().getHeight() + 20) / 2);

	}
}
