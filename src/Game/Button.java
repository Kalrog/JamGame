package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Button
{
	ButtonCall execute;

	String text;

	Rectangle rect;

	public Button(int posx, int posy, int width, int height, String text, ButtonCall execute)
	{

		this.text = text;
		this.execute = execute;
		rect = new Rectangle(posx, posy, width, height);

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
		g.setColor(Color.ORANGE);
		g.fillRect(rect.x, rect.y, rect.width, rect.height);
		g.setColor(Color.BLACK);
		g.drawString(text, rect.width / 2, rect.height / 2);
	}

}
