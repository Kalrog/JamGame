package assets;

import javax.imageio.ImageIO;
import javax.xml.soap.Text;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author Niklas Speckels at 13.01.2017
 */
public class Texture
{

    BufferedImage image;

    public int width, height, yShift;

    public Texture(BufferedImage image, int width, int height)
    {
        this.height = height;
        this.width = width;
        this.image = new BufferedImage(width,height, BufferedImage.TYPE_INT_ARGB);
        this.image.getGraphics().drawImage(image, 0, 0, width, height, null);
    }

    public Texture(String path, int width, int height)
    {

        try
        {
            this.height = height;
            this.width = width;
            this.image = new BufferedImage(width,height, BufferedImage.TYPE_INT_ARGB);
            this.image.getGraphics().drawImage(ImageIO.read(new File(path)), 0, 0, width , height, null);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void draw(Graphics g, int x, int y)
    {
        g.drawImage(image, x - width / 2, y - height + yShift, null);
    }

    @Override
    public Texture clone()
    {
        return new Texture(image,width,height);
    }

    public void setYShift(int shift)
    {
        this.yShift = shift;
    }

}
