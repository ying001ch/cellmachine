package org.wyy.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Field {
	private Cell[][] contents;
	
	private int rows;
	private int cols;
	
	public Cell getCell(int row,int col) {
		return contents[row][col];
	}
	public Cell[] getNeighbors(int row,int col) {
		int[] rowT = {row-1,row,row+1};
		int[] colT = {col-1,col,col+1};
		List<Cell> neighbors = new ArrayList<>(rowT.length * colT.length);
		for(int r:rowT) {
			if(r < 0 || r >=rows) {
				continue;
			}
			for(int c:colT) {
				if(c < 0 || c >=cols) {
					continue;
				}
				if(r==row && c==col) {
					continue;
				}
				neighbors.add(contents[r][c]);
			}
		}
		
		return neighbors.toArray(new Cell[0]);
	}

	public Field(int rows, int cols, double probability) {
		super();
		this.rows = rows;
		this.cols = cols;
		
		init(probability);
	}
	
	private void init(double probability) {
		contents = new Cell[rows][cols];
		
		Random random = new Random();
		for (int row=0;row<rows;row++) {
			// 一行
			for (int col=0;col<cols;col++) {
				// 一列
				contents[row][col] = new Cell();
				if(random.nextDouble() < probability) {
					// 细胞默认是死的  根据给定的概率 来复活细胞
					contents[row][col].reBorn();
				}
			}
		}
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getCols() {
		return cols;
	}
	public void setCols(int cols) {
		this.cols = cols;
	}
	
}
