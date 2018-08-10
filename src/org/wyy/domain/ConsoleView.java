package org.wyy.domain;

public class ConsoleView {
	private Field field;

	public ConsoleView(Field field) {
//		super(field);
		this.field = field;
	}

	public void paint() {
		clearConsole();
		for (int r = 0; r < field.getRows(); r++) {
			for (int c = 0; c < field.getCols(); c++) {
				Cell cell = field.getCell(r, c);
				if (cell.isAlive()) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
				System.out.print(" ");
			}
			System.out.println();
		}

	}

	private static void clearConsole() {
		for(int i=0;i<30;i++) {
			System.out.println();
		}
//		try {
//			String os = System.getProperty("os.name");
//
//			if (os.contains("Windows")) {
//				Runtime.getRuntime().exec("cmd cls");
//			} else {
//				Runtime.getRuntime().exec("clear");
//			}
//		} catch (Exception exception) {
//			exception.printStackTrace();
//		}
	}
}
