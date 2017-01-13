package assets;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * 
 * 
 * @author Niklas Speckels at 13.01.2017
 */
public class Texture
{

	BufferedImage image;

	int width, height;

	public Texture(BufferedImage image)
	{
		height = image.getHeight();
		width = image.getWidth();
		this.image = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);
		this.image.getGraphics().drawImage(image, 0, 0, null);
	}

	public void draw(Graphics g, int x, int y, BufferedImage image)
	{
		g.drawImage(image, x, y, null);
		// TODO draw image
	}

}
