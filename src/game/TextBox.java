package game;

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

	}
}
