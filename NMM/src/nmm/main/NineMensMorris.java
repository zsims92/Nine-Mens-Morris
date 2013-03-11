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
    		switch(gamephase)
    		{
    		case Board.PLACEMENT_PHASE: //placement
    			// If placement is successful, move on to next player.
    			if (PlacementPhase())
    				nextPlayer();
    			break;
    			
    		case Board.MOVEMENT_PHASE: //movement
    			if(MovementPhase())
    				nextPlayer();
    			break;
    			
    		case Board.REMOVAL_PHASE: //removal
    			RemovalPhase();
    			break;
    			
    		default:
    			System.out.println("Invalid game phase. Exiting.");
    			System.exit(1);
    			break;
    		}
    		//gameboard.PrintEdges();
    		gamephase = gameboard.GetCurrentPhase(current_player);
    	}
    }
    
    private static boolean MovementPhase() 
    {
		String input;
		String tokens[];
		int pieceID;
		Scanner scanner = new Scanner(System.in);
		
		System.out.printf("| [Movement] Player '%s', enter movement location [<PieceID>,<LocLabel>]: ", current_player.getName());
		input = scanner.nextLine();
		
		tokens = input.split(",");
		if(!isInt(tokens[0]))
		{
			System.out.println("| Invalid piece ID - not an integer.");
			return false;
		}
		pieceID = Integer.parseInt(tokens[0]);
			
		return gameboard.MovePiece(current_player, pieceID, tokens[1]);
		
		
	}

	private static void RemovalPhase() 
	{
		System.out.println("Removal phase! WOOT");
		
	}

	private static boolean PlacementPhase() 
	{
		/*
		String input;
		String tokens[];
		int pieceID;
		Scanner scanner = new Scanner(System.in);
		
		System.out.printf("| [Placement] Player '%s', enter placement location [<PieceID>,<LocLabel>]: ", current_player.getName());
		input = scanner.nextLine();
		
		tokens = input.split(",");
		if(!isInt(tokens[0]))
		{
			System.out.println("| Invalid piece ID - not an integer.");
			return false;
		}
		pieceID = Integer.parseInt(tokens[0]);
		*/
		gameboard.PlacePiece(player1, 0, "A");
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
		nextPlayer();

		
		return true;//gameboard.PlacePiece(current_player, pieceID, tokens[1]);
		
		
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
		if (current_player == player1)
			current_player = player2;
		else
			current_player = player1;
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
		System.out.println("+---------------------------------------");
	}

}
