package nmm.main;

import java.io.IOException;
import java.util.Scanner;

import nmm.model.Board;
import nmm.model.user.Player;


public class NineMensMorris {
	private static Player player1;
	private static Player player2;
	private static Player current_player;
	private static Board gameboard;
	
    public static void main(String[] args) throws IOException 
    {	
    	// Open up system.in for input.
        Scanner kbd = new Scanner(System.in);
        
    	gameboard = new Board();
    	GameSetup(kbd);
    	
    	int gamephase = gameboard.GetCurrentPhase(current_player);
    	
    	while(gamephase != Board.GAMEOVER_PHASE)
    	{
        	// Print out the game board visual.
        	PrintBreak();
        	gameboard.printBoard();
        	PrintBreak();
        	
    		switch(gamephase)
    		{
    		case Board.PLACEMENT_PHASE: //placement
    			// If placement is successful, move on to next player.
    			if (PlacementPhase(kbd))
    				nextPlayer();
    			break;
    			
    		case Board.MOVEMENT_PHASE: //movement
    			// Next player on successful move.
    			// Will return false if a move results in a mill, so that the player isn't skipped.
    			if(MovementPhase(kbd))
    				nextPlayer();
    			break;
    			
    		case Board.REMOVAL_PHASE: //removal
    			if(RemovalPhase(kbd))
    				nextPlayer();
    			break;
    			
    		default:
    			System.out.println("Invalid game phase. Exiting.");
    			System.exit(1);
    			break;
    		}
    		
    		// Update the current game phase as retrieved from the game board.
    		gamephase = gameboard.GetCurrentPhase(current_player);
    	}
    	
    	
    	// Print victory message.
    	PrintSection(String.format("%s winss the game with a score of %d!", current_player.getName(), current_player.getScore()));
    	kbd.close();
    }
    
    private static boolean MovementPhase(Scanner kbd) 
    {
		String input;
		String tokens[];
		int pieceID;
		
		// Detect if the player has no moves available, and their score is above 3 (not fly mode)
		if (gameboard.numMovesAvailable(current_player) == 0 && current_player.getScore() > 3)
		{
			PrintIndent(String.format("[Movement] Player %s has no moves available. Their turn is skipped.", current_player.getName()));
			return true;
		}
		
		System.out.printf("| [Movement] Player '%s', enter movement location [<PieceID>,<LocLabel>]: ", current_player.getName());
		input = kbd.next();
		
		tokens = input.split(",");
		
		
		if(tokens.length < 2)
		{
			PrintIndent("Input not recognized. Please try again.");
			return false;
		}
		if(!isInt(tokens[0]))
		{
			PrintIndent("Invalid Piece ID - Not an integer.");
			return false;
		}
		pieceID = Integer.parseInt(tokens[0]);
		
		return gameboard.MovePiece(current_player, pieceID, tokens[1]);
		
		
	}

	private static boolean RemovalPhase(Scanner kbd) 
	{
		String input;
		int pieceID;
		
		System.out.printf("| [Removal] Player '%s', enter opponent's piece ID for removal [<PieceID>]: ", current_player.getName());
		input = kbd.next();
		
		
		if(!isInt(input))
		{
			System.out.println("| Invalid piece ID - not an integer.");
			return false;
		}
		pieceID = Integer.parseInt(input);
		
			
		return gameboard.RemovePiece(inactivePlayer(), pieceID);
		
	}

	private static boolean PlacementPhase(Scanner kbd) 
	{
		String input;
		String tokens[];
		int pieceID;
		
		System.out.printf("| [Placement] Player '%s', enter placement location [<PieceID>,<LocLabel>]: ", current_player.getName());
		input = kbd.next();
		
		tokens = input.split(",");
		if(tokens.length < 2)
		{
			PrintIndent("Input not recognized. Please try again.");
			return false;
		}
		if(!isInt(tokens[0]))
		{
			System.out.println("| Invalid piece ID - not an integer.");
			return false;
		}
		pieceID = Integer.parseInt(tokens[0]);
		
		return gameboard.PlacePiece(current_player, pieceID, tokens[1]);
		
		
	}
	
	private static boolean isInt(String input)
	{
		try 
		{
			Integer.parseInt(input);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static void GameSetup(Scanner kbd)
    {
    	String name1 = "Player1", name2 = "Player2";
    	
    	PrintSection("Welcome To Nine Men's Morris!");
    	PrintIndent("Game Setup:");
    	
    	// Request a name for player 1.
    	System.out.print("| Enter Player 1's Name: ");
		name1 = kbd.nextLine();
			
		// Request a name for player 2
    	System.out.print("| Enter Player 2's Name: ");
		name2 = kbd.nextLine();
    	    	
    	player1 = new Player(name1, "RED");
    	player2 = new Player(name2, "BLUE");
    	
    	// Randomly select a starting player
    	if (Math.random() * 50.0d >= 25)
    		current_player = player1;
    	else
    		current_player = player2;
    }
	
	private static void nextPlayer()
	{
		current_player = inactivePlayer();
	}
	
	private static Player inactivePlayer()
	{
		if (current_player == player1)
			return player2;
		else
			return player1;
	}
	
	public static void PrintSection(String input)
	{
    	PrintBreak();
    	PrintIndent(input);
    	PrintBreak();
    	
	}
	
	public static void PrintIndent(String input)
	{
		System.out.println("| " + input);
	}
	
	public static void PrintBreak()
	{
		System.out.println("+------------------------------------------------------------------------------");
	}

}
