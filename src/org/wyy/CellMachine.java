package org.wyy;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

import javax.swing.JFrame;

import org.wyy.domain.Cell;
import org.wyy.domain.Field;
import org.wyy.domain.View;

public class CellMachine {
	static int stop = 0;
	public static void main(String[] args) {
		int rows = 35;
		int cols = 35;
		int all = rows*cols;
		Field field = new Field(rows, cols,0.15);

//		View consoleView = new ConsoleView(field);
		View view = new View(field);
		JFrame jFrame = new JFrame("cell machine");
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.add(view);
		jFrame.pack();
		jFrame.setResizable(false);
		jFrame.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				int keyCode = e.getKeyCode();
				System.out.println("keycode: "+keyCode);
				if(keyCode == 32) { // 空格键
					if(stop == 0){
						stop = 1;
					}else{
						synchronized (jFrame) {
							jFrame.notifyAll();
							stop = 0;
						}
					}
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {

			}
		});

		int num =5000;
		int k = 0;
		while(k < num) {
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
			System.out.println("活细胞数量："+aliveNum+", 存活率："+1.0*Math.round(1000.0*aliveNum/all)/10 +"%");
			try {
				if(stop == 1){
					synchronized (jFrame){
						jFrame.wait();
					}
				}
				Thread.sleep(80);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// 计数器
//			k++;
		}
		
		System.out.println("game over!");
	}
}
