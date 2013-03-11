package nmm.main;

import java.awt.EventQueue;
import java.util.Collections;
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
    			if (PlacementPhase())
    				nextPlayer();
    			break;
    		case Board.MOVEMENT_PHASE: //movement
    			MovementPhase();
    			break;
    		case Board.REMOVAL_PHASE: //removal
    			RemovalPhase();
    			break;
    		default:
    			System.out.println("Invalid game phase. Exiting.");
    			System.exit(1);
    			break;
    		}
    		gameboard.printBoard();
    		gamephase = gameboard.GetCurrentPhase(current_player);
    	}
    }
    
    private static void MovementPhase() 
    {
		
		
	}

	private static void RemovalPhase() 
	{
		
		
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
		pieceID = Integer.parseInt(tokens[0]);
		return gameboard.PlacePiece(current_player, pieceID, tokens[1]);
		
		
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
		System.out.println("+---------------------------------------\n");
	}

}
