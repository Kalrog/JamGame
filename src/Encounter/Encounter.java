package Encounter;

import java.awt.Color;
import java.awt.Graphics;

import Game.Game;
import Game.ButtonCall;
import World.World;

/**
 * Created by jonathan on 13.01.17.
 */
public class Encounter
{
    World world;

	String text;

	Solution[] solutions;

	class solutionButton implements ButtonCall{
        private Solution solution;

        public solutionButton(Solution solution){
            this.solution = solution;
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

	public void startEncouner(Graphics g)
	{
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
	}

	public void draw(){

	}
}
