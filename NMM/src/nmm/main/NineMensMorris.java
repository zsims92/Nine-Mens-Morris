package nmm.main;

import java.awt.EventQueue;
import java.util.Scanner;

import nmm.model.Board;
import nmm.model.user.Player;


public class NineMensMorris {
	private static Player player1;
	private static Player player2;
	private static Player current_player;
	private static Board gameboard;
	
    public static void main(String[] args) 
    {
    	gameboard = new Board();
    	GameSetup();
    	
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
    			if (PlacementPhase())
    				nextPlayer();
    			break;
    			
    		case Board.MOVEMENT_PHASE: //movement
    			// Next player on successful move.
    			// Will return false if a move results in a mill, so that the player isn't skipped.
    			if(MovementPhase())
    				nextPlayer();
    			break;
    			
    		case Board.REMOVAL_PHASE: //removal
    			if(RemovalPhase())
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
    }
    
    private static boolean MovementPhase() 
    {
		String input;
		String tokens[];
		int pieceID;
		Scanner scanner = new Scanner(System.in);
		
		// Detect if the player has no moves available, and their score is above 3 (not fly mode)
		if (gameboard.numMovesAvailable(current_player) == 0 && current_player.getScore() > 3)
		{
			PrintIndent(String.format("[Movement] Player %s has no moves available. Their turn is skipped.", current_player.getName()));
			return true;
		}
		
		System.out.printf("| [Movement] Player '%s', enter movement location [<PieceID>,<LocLabel>]: ", current_player.getName());
		input = scanner.nextLine();
		
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

	private static boolean RemovalPhase() 
	{
		String input;
		String tokens[];
		int pieceID;
		Scanner scanner = new Scanner(System.in);
		
		System.out.printf("| [Removal] Player '%s', enter opponent's piece ID for removal [<PieceID>]: ", current_player.getName());
		input = scanner.nextLine();
		
		if(!isInt(input))
		{
			System.out.println("| Invalid piece ID - not an integer.");
			return false;
		}
		pieceID = Integer.parseInt(input);
			
		return gameboard.RemovePiece(inactivePlayer(), pieceID);
		
	}

	private static boolean PlacementPhase() 
	{
		String input;
		String tokens[];
		int pieceID;
		Scanner scanner = new Scanner(System.in);
		
		System.out.printf("| [Placement] Player '%s', enter placement location [<PieceID>,<LocLabel>]: ", current_player.getName());
		input = scanner.nextLine();
		
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
		
		// Code below for testing movement phase. Comment out code above and below to use.
		/*gameboard.PlacePiece(player1, 0, "A");
		gameboard.PlacePiece(player1, 1, "B");
		gameboard.PlacePiece(player1, 2, "C");
		gameboard.PlacePiece(player1, 3, "D");
		gameboard.PlacePiece(player1, 4, "E");
		gameboard.PlacePiece(player1, 5, "F");
		gameboard.PlacePiece(player1, 6, "G");
		gameboard.PlacePiece(player1, 7, "H");
		gameboard.PlacePiece(player1, 8, "I");
		gameboard.PlacePiece(player2, 0, "J");
		gameboard.PlacePiece(player2, 1, "K");
		gameboard.PlacePiece(player2, 2, "L");
		gameboard.PlacePiece(player2, 3, "M");
		gameboard.PlacePiece(player2, 4, "N");
		gameboard.PlacePiece(player2, 5, "O");
		gameboard.PlacePiece(player2, 6, "P");
		gameboard.PlacePiece(player2, 7, "Q");
		gameboard.PlacePiece(player2, 8, "R");*/
		/*gameboard.PlacePiece(player1, 0, "A");
		gameboard.PlacePiece(player2, 0, "B");
		gameboard.PlacePiece(player1, 1, "C");
		gameboard.PlacePiece(player2, 1, "D");
		gameboard.PlacePiece(player1, 2, "E");
		gameboard.PlacePiece(player2, 2, "F");
		gameboard.PlacePiece(player1, 3, "G");
		gameboard.PlacePiece(player2, 3, "H");
		gameboard.PlacePiece(player1, 4, "I");
		gameboard.PlacePiece(player2, 4, "J");
		gameboard.PlacePiece(player1, 5, "K");
		gameboard.PlacePiece(player2, 5, "L");
		gameboard.PlacePiece(player1, 6, "M");
		gameboard.PlacePiece(player2, 6, "N");
		gameboard.PlacePiece(player1, 7, "O");
		gameboard.PlacePiece(player2, 7, "P");
		gameboard.PlacePiece(player1, 8, "Q");
		gameboard.PlacePiece(player2, 8, "R");
		nextPlayer();*/
		
		//return true;
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

	public static void GameSetup()
    {
    	String name1, name2;
    	Scanner scanner = new Scanner(System.in);
    	
    	PrintSection("Welcome To Nine Men's Morris!");
    	PrintIndent("Game Setup:");
    	
    	// Request a name for player 1.
    	System.out.print("| Enter Player 1's Name: ");
    	name1 = scanner.nextLine();
    	System.out.print("| Enter Player 2's Name: ");
    	name2 = scanner.nextLine();
    	
    	player1 = new Player(name1, "RED");
    	player2 = new Player(name2, "BLUE");
    	current_player = player1;
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
