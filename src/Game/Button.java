package Game;

public class Button
{
	int posx, posy, width, height;

	ButtonCall execute;

	String text;

	public Button(int posx, int posy, int width, int height, String text, ButtonCall execute)
	{

		this.posx = posx;
		this.posy = posy;
		this.width = width;
		this.height = height;
		this.text = text;
		this.execute = execute;

	}

}
