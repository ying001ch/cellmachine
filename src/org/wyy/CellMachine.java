package org.wyy;

import java.util.Arrays;

import javax.swing.JFrame;

import org.wyy.domain.Cell;
import org.wyy.domain.Field;
import org.wyy.domain.View;

public class CellMachine {
	public static void main(String[] args) {
		Field field = new Field(35, 35,0.85);
		
//		View consoleView = new ConsoleView(field);
		View view = new View(field);
		JFrame jFrame = new JFrame("cell machine");
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.add(view);
		jFrame.pack();
		jFrame.setResizable(false);
		
		int num =5000;
		int k = 0;
		while(k < num) {
			if(true) {
				continue;
				
			}
			int aliveNum=0;
			for (int r=0;r<field.getRows();r++) {
				for (int c=0;c<field.getCols();c++) {
					Cell[] neighbors = field.getNeighbors(r, c);
					long count = Arrays.stream(neighbors).filter(Cell::isAlive).count();
					Cell cell = field.getCell(r, c);
					if(count <2 || count>3) {
						cell.die();
					}else if(count == 3) {
						cell.reBorn();
					}
					
					if(cell.isAlive()) {
						aliveNum++;
					}
				}
			}
			
//			consoleView.paint();
			jFrame.repaint();
//			System.out.println("活细胞数量："+aliveNum);
			try {
				Thread.sleep(80);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			k++;
		}
		
		System.out.println("game over!");
	}
}
