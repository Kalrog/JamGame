package encounter;

import java.awt.Color;
import java.awt.Graphics;

import game.Button;
import game.Game;
import game.ButtonCall;
import game.TextBox;
import world.World;

/**
 * Created by jonathan on 13.01.17.
 */
public class Encounter
{

    private final int BUTTON_X = 50;
    private final int BUTTON_Y = 50;
    private final int BUTTON_WIDTH = 100;
    private final int BUTTON_HEIGHT = 18;
    private final int BUTTON_DIFF = 20;

    private World world;

	private String text;

	private Solution[] solutions;

    private String[] results;

    private Button[] buttons;

    private TextBox[] texts;

	public int chance;

	private State state;


	private enum State{
	    STARTED,RESULT,INACTIVE
	}

	class solutionButton implements ButtonCall{
        private Solution solution;

        public solutionButton(Solution solution){
            this.solution = solution;
        }

        @Override
        public void call() {
            Encounter.this.showResult(solution.resolve(world));
        }
    }

	public Encounter(World world,String text,Solution [] solutions)
	{
	    this.world = world;
	    this.text = text;
	    this.solutions = solutions;
	    this.state = State.INACTIVE;

	    for(int x = 0; x < solutions.length;x++){
            buttons[x] = new Button(BUTTON_X,BUTTON_Y+ x * BUTTON_DIFF , BUTTON_WIDTH,BUTTON_HEIGHT,solutions[x].getText(),new solutionButton(solutions[x]));
        }
	}

	public void startEncounter()
	{
	    this.state = State.STARTED;
	}

	public void showResult(String[] result)
    {
        this.results = result;
        this.state = State.RESULT;
    }

    public void endEncounter()
    {
        this.state = State.INACTIVE;
    }


	public void draw()
    {

	}
}
