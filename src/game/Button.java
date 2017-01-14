package game;

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
        g.setColor(Color.ORANGE);
        g.fillRect(rect.x, rect.y, rect.width, rect.height);
        g.setColor(Color.BLACK);
        g.drawString(text, rect.x + ((rect.width - g.getFontMetrics().stringWidth(text)) / 2), rect.y + rect.height / 2);
    }

    public void dispose()
    {
        InputManager.removeButton(this);
    }
}
