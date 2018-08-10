package org.wyy.domain;

import java.awt.Graphics;

public class Cell {
	private boolean alive = false;
	
	public boolean isAlive() {
		return alive;
	}
	public void die() {
		alive = false;
	}
	public void reBorn() {
		alive = true;
	}
	public void draw(Graphics g, int i, int j, int gridSize) {
		g.drawRect(i, j, gridSize, gridSize);
		if ( alive ) {
			g.fillRect(i, j, gridSize, gridSize);
		}
		
	}
}
