package Encounter;

import java.awt.Color;
import java.awt.Graphics;

import Game.Game;

/**
 * Created by jonathan on 13.01.17.
 */
public class Encounter
{

	String text;

	Solution[] solutions;

	public Encounter()
	{

	}

	public void startEncouner(Graphics g)
	{
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
	}
}
