package Game;

import javax.swing.event.MouseInputListener;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by jonathan on 13.01.17.
 */
public class InputManager implements MouseInputListener, KeyListener, WindowListener
{

	private static InputManager inputManager;

	private static ArrayList<Button> buttons;

	private InputManager()
	{

		buttons = new ArrayList<>();

	}

	public static InputManager getInstance()
	{
		if (inputManager == null)
		{
			inputManager = new InputManager();
		}
		return inputManager;
	}

	public static void addButton(Button button){
	    buttons.add(button);
    }

    public static void removeButton(Button button){
	    buttons.remove(button);
    }

	/*
	 * Keyboard
	 */

	@Override
	public void keyTyped(KeyEvent keyEvent)
	{
	}

	@Override
	public void keyPressed(KeyEvent keyEvent)
	{

	}

	@Override
	public void keyReleased(KeyEvent keyEvent)
	{

	}

	/*
	 * Mouse
	 */

	@Override
	public void mouseClicked(MouseEvent mouseEvent)
	{

		for (Button button : buttons)
		{
			if (button.clicked(mouseEvent.getX(), mouseEvent.getY())) break;
		}

	}

	@Override
	public void mousePressed(MouseEvent mouseEvent)
	{

	}

	@Override
	public void mouseReleased(MouseEvent mouseEvent)
	{

	}

	@Override
	public void mouseEntered(MouseEvent mouseEvent)
	{

	}

	@Override
	public void mouseExited(MouseEvent mouseEvent)
	{

	}

	@Override
	public void mouseDragged(MouseEvent mouseEvent)
	{

	}

	@Override
	public void mouseMoved(MouseEvent mouseEvent)
	{

	}

	/*
	 * Window
	 */

	@Override
	public void windowOpened(WindowEvent windowEvent)
	{

	}

	@Override
	public void windowClosing(WindowEvent windowEvent)
	{
		Game.prepareShutdown();
	}

	@Override
	public void windowClosed(WindowEvent windowEvent)
	{

	}

	@Override
	public void windowIconified(WindowEvent windowEvent)
	{

	}

	@Override
	public void windowDeiconified(WindowEvent windowEvent)
	{

	}

	@Override
	public void windowActivated(WindowEvent windowEvent)
	{

	}

	@Override
	public void windowDeactivated(WindowEvent windowEvent)
	{

	}
}
