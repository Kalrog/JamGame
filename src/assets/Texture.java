package assets;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author Niklas Speckels at 13.01.2017
 */
public class Texture
{

    BufferedImage image;

    int width, height;

    public Texture(BufferedImage image, int width, int height)
    {
        this.height = height;
        this.width = width;
        this.image = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);
        this.image.getGraphics().drawImage(image, 0, 0, height, width, null);
    }

    public Texture(String path, int width, int height)
    {
        try
        {
            BufferedImage load = ImageIO.read(new File(path));
            new Texture(load, width, height);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void draw(Graphics g, int x, int y)
    {
        g.drawImage(image, x - width / 2, y - height, null);
    }

}
