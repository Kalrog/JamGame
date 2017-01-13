package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class TextBox
{

	Rectangle rect;

	String text;

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
		g.drawString(text, rect.x + 10, rect.y + rect.height / 2);

	}
}
