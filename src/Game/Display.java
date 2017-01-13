package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;

/**
 * Created by jonathan on 13.01.17.
 */
public class Display extends JFrame {

    /**
     * Constructs a Display
     * @param width width of the Display
     * @param height height of the Display
     */
    public Display(int width, int height){
        super("Sailing");
        setSize(width,height);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addMouseListener(InputManager.getInstance());
        addKeyListener(InputManager.getInstance());
        addWindowListener(InputManager.getInstance());
        setVisible(true);
        createBufferStrategy(2);
        //pack();
        //setIgnoreRepaint(true);

    }

    public Graphics getBackBuffer(){
        return getBufferStrategy().getDrawGraphics();
    }

    public void flipBuffers(){
        getBufferStrategy().show();
    }
}
