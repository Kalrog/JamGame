package encounter;

import java.awt.Color;
import java.awt.Graphics;

import game.Game;
import game.ButtonCall;
import world.World;

/**
 * Created by jonathan on 13.01.17.
 */
public class Encounter
{
    World world;

	String text;

	Solution[] solutions;

	public int chance;

	class solutionButton implements ButtonCall{
        private Solution solution;
        private Encounter encounter;

        public solutionButton(Solution solution,Encounter encounter){
            this.solution = solution;
            this.encounter = encounter;
        }

        @Override
        public void call() {
            solution.resolve(world);
        }
    }

	public Encounter(World world,String text,Solution [] solutions)
	{
	    this.world = world;
	    this.text = text;
	    this.solutions = solutions;
	}

	public void startEncounter()
	{
	}

	public void showResult()
    {
    }

    public void endEncounter()
    {

    }


	public void draw()
    {

	}
}
