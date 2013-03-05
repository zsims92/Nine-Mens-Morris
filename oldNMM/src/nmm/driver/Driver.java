package nmm.driver;

import java.io.IOException;

import nmm.virtual.parts.MainWindow;

public class Driver{
	
	public static void main(String[] args){
		MainWindow mw = new MainWindow("Zach", "Steve");
		mw.run();
		
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		mw.showGameBoard();
		
	}		
}
