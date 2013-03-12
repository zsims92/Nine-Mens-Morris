package nmm.model.user;

import java.util.Random;

public class AIPlayer extends Player {

	public static final String[] colors = {"black", "red", "yellow", "blue", "cyan", "darkGray", "gray", "green", "lightGray", "magenta", "orange", "pink"};
	
	/***
	 * Default constructor for an
	 * AI player
	 * @param name
	 * @param color
	 */
	public AIPlayer(String name, String color) {
		super(name, chooseRandomColor(color));		
	}
	
	/***
	 * Will choose a random color for the AI
	 * @param color
	 * @return
	 */
	private static String chooseRandomColor(String color) {
		Random rand = new Random();
		int i = rand.nextInt() % 12;
		while(i < 0 || i > 11)
			i = rand.nextInt() % 12;
		if(AIPlayer.colors[i] != null)
				return AIPlayer.colors[i];
		return color;
	}

	/**
	 * Will be in used in PVE mode
	 * 
	 * Tells the model that this is a
	 * computer
	 */
	@Override
	public boolean isHuman(){
		return false;
	}

}
