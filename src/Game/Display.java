package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;

/**
 * Created by jonathan on 13.01.17.
 */
public class Display extends JFrame {

    private Canvas canvas;

    /**
     * Constructs a Display
     * @param width width of the Display
     * @param height height of the Display
     */
    public Display(int width, int height){
        super("Sailing");
        canvas = new Canvas();

        setSize(width,height);
        canvas.setSize(width,height);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        add(canvas);
        addMouseListener(InputManager.getInstance());
        addKeyListener(InputManager.getInstance());
        addWindowListener(InputManager.getInstance());
        setVisible(true);
        canvas.createBufferStrategy(2);
        //pack();
        setIgnoreRepaint(true);

    }

    public Graphics getBackBuffer(){
        return canvas.getBufferStrategy().getDrawGraphics();
    }

    public void flipBuffers(){
        canvas.getBufferStrategy().show();
    }

}
