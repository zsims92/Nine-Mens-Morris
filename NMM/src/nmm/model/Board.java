package nmm.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import nmm.model.user.Player;

public class Board {
	
	// Class variables
	private ArrayList<Location> location_list;
	private ArrayList<Edge> edge_list;
	private int current_phase;

	// Game phases
	public static final int GAMEOVER_PHASE = -1;
	public static final int PLACEMENT_PHASE = 0;
	public static final int MOVEMENT_PHASE = 1;
	public static final int REMOVAL_PHASE = 2;
	
	// Number to letter array.
	public static final char[] ALPHABET = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	
	public Board(){
		// Load the edge and location list from file
		location_list = new ArrayList<Location>();
		edge_list = new ArrayList<Edge>();
		this.LoadBoard();
		
		// Sort the location list for printing the visual.
		Collections.sort(location_list);
		
		this.current_phase = PLACEMENT_PHASE;
		//this.PrintEdges();
		
		//this.boardArray = newBoard();
		
	}

	public int GetCurrentPhase(Player curplayer) {
		// See if there are pieces yet to be placed.
		if (curplayer.getPiecesPlayed() < 9)
			return PLACEMENT_PHASE;

		// See if phase has been set to REMOVAL or GAMEOVER
		else if (current_phase == REMOVAL_PHASE || current_phase == GAMEOVER_PHASE)
			return current_phase;
		
		// Only other option is movement phase.
		else
			return MOVEMENT_PHASE;
	}

	public void SetCurrentPhase(int current_phase) {
		this.current_phase = current_phase;
	}
	
	private Location GetLocationByLabel(String label)
	{
		for(int i = 0; i < location_list.size(); i++)
			if (location_list.get(i).getLabel().equals(label))
				return location_list.get(i);
				
		return null;
	}
	
	/**************************************
	 * Gameplay Related Methods
	 **************************************/
	public boolean PlacePiece(Player player, int pieceID, String locLabel)
	{
		GamePiece curPiece = player.getPiece(pieceID);
		Location newLoc = GetLocationByLabel(locLabel);
		
		// Check for invalid input.
		if (curPiece == null || newLoc == null)
		{
			System.out.println("| Invalid piece id or location label.");
			return false;
		}
		
		// Make sure piece selection isn't placed already.
		if (curPiece.getStatus() != GamePiece.UNPLACED)
		{
			System.out.println("| Invalid Piece - It is already placed or dead.");
			return false;
		}
		
		// Make sure the location is empty
		if (!newLoc.ContainsPiece(null))
		{
			System.out.println("| Invalid Location - It contains a piece already.");
			return false;
		}
		
		// We're ok to place the piece.
		newLoc.setPiece(curPiece);
		curPiece.setStatus(GamePiece.PLACED);
		return true;

	}
	
	public boolean MovePiece(Player player, int pieceID, String locLabel)
	{
		GamePiece curPiece = player.getPiece(pieceID);
		Location curLoc = GetPieceLocation(curPiece);
		Location newLoc = GetLocationByLabel(locLabel);
		
		// Check for invalid input.
		if (curPiece == null || newLoc == null)
		{
			System.out.println("| Invalid piece id or location label.");
			return false;
		}
		
		// Make sure piece selection is placed already.
		if (!curPiece.inPlay())
		{
			System.out.println("| Invalid Piece - It is not placed nor in play.");
			return false;
		}
		
		// Detect if we are in fly mode. If not, make sure we're adjacent.
		// Make sure the locations are neighbors.
		if (player.getScore() > 3 && !AreNeighbors(curLoc, newLoc))
		{
			System.out.println("| Invalid Location - It is not adjacent.");
			return false;
		}
		
		// Make sure the location is empty
		if (!newLoc.ContainsPiece(null))
		{
			System.out.println("| Invalid Location - It contains a piece already.");
			return false;
		}
		
		// We're ok to move the piece.
		curPiece.setStatus(GamePiece.MOVED);
		newLoc.setPiece(curPiece);
		curLoc.setPiece(null);
		
		// Check for a created mill.
		if (IsMill(newLoc))
		{
			// We will return false so current player is not nexted.
			// Set current phase to removal phase.
			System.out.printf("| %s has created a mill!\n", player.getName());
			this.SetCurrentPhase(REMOVAL_PHASE);
			return false;
		}
		
		return true;

	}
	
	public boolean RemovePiece(Player player, int pieceID)
	{
		GamePiece curPiece = player.getPiece(pieceID);
		Location curLoc = GetPieceLocation(curPiece);
		
		// Check for invalid input.
		if (curPiece == null)
		{
			System.out.println("| Invalid Piece ID - Piece not found.");
			return false;
		}
		
		// Make sure piece selection is placed already.
		if (!curPiece.inPlay())
		{
			System.out.println("| Invalid Piece - It is not placed or alive/in play.");
			return false;
		}
		
		// Make sure the piece is not part of a mill if the player has more than 3 pieces remaining.
		if (player.getScore() > 3 && IsMill(curLoc))
		{
			System.out.println("| Invalid Piece - You cannot remove a member of a mill.");
			return false;
			
			//blah
		}
		
		
		// We're ok to remove the piece. Change to movement phase.
		curLoc.setPiece(null);
		curPiece.setStatus(GamePiece.DEAD);
		
		
		// Grab the player's new calculated score.
		int score = player.getScore();
		
		// Print out the player's new score. If player has 3 pieces, print out a message indicating fly mode for that player.
		System.out.printf("| %s's Score: %d\n", player.getName(), score);
		if (score == 3)
			System.out.printf("| %s has engaged fly mode with 3 pieces remaining.\n", player.getName());
		
		// See if the player has less than 3 pieces remaining, that leads to gameover phase.
		// if not set the phase to movement phase.
		if(score < 3)
		{
			SetCurrentPhase(GAMEOVER_PHASE);
			// We will return false so we crown the correct victor 
			// We would stay on this player otherwise, because this method was
			// passed the opposite player of the current player to remove his piece.
			return false;
		} else
			SetCurrentPhase(MOVEMENT_PHASE);
		
		return true;

	}
	
	
	private boolean IsMill(Location loc)
	{
		int vertCount = CountAdjacent(loc, 0);
		int horizCount = CountAdjacent(loc, 1);
		
		if (Math.max(vertCount, horizCount) > 2)
			return true;
		else
			return false;
	}
	
	private int CountAdjacent(Location loc, int dir)
	{
		Player owner = loc.getPiece().getOwner();
		int status1, status2;
		
		ArrayList<Location> nghbrs = SomeNeighbors(loc, dir, owner);
		if (nghbrs.size() == 2)
		{
			// Store the neighbors status's to make sure at least one has moved (req to become a mill)
			status1 = nghbrs.get(0).getPiece().getStatus();
			status2 = nghbrs.get(1).getPiece().getStatus();	
			
		} else if (nghbrs.size() == 1) {
			status1 = nghbrs.get(0).getPiece().getStatus();
			
			// See if the neighbor has another adjacent neighbor.
			nghbrs = SomeNeighbors(nghbrs.get(0), dir, owner);
			nghbrs.remove(loc);
			
			if(nghbrs.size() == 1)
			{
				status2 = nghbrs.get(0).getPiece().getStatus();
			} else
				return 0;
			
		} else
			return 0;
		
		// Make sure at least one piece of the mill has moved.
		if (status1 == GamePiece.MOVED || status2 == GamePiece.MOVED || loc.getPiece().getStatus() == GamePiece.MOVED)
			return 3;
		else
			return 0;
		
	}
	
	private boolean AreNeighbors(Location loc1, Location loc2)
	{
		ArrayList<Location> all_neighbors = AllNeighbors(loc1);
		if (all_neighbors.contains(loc2))
			return true;
		else
			return false;
	}
	
	/*
	 *  Returns locations with a defined direction and a owner that
	 *  are neighbors to the supplied location.
	 */
	private ArrayList<Location> SomeNeighbors(Location loc, int dir, Player owner)
	{
		ArrayList<Location> NeighborList = new ArrayList<Location>();
		Edge curEdge;
		Location oppLoc;
		
		// Find all edges that hold this location
		for (int i=0; i < edge_list.size(); i++)
		{
			curEdge = edge_list.get(i);
			oppLoc = curEdge.GetOpposite(loc);
			
			// If the edge has the location, add the adjacent location to the list.
			// Make sure it matches directional and player owned conditions.
			if (curEdge.HasLocation(loc) && curEdge.GetAlignment() == dir)
			{
				// If we want the owner to be null (aka no piece located there),
				// add the location if it holds no piece.
				if(owner == null && oppLoc.getPiece() == null)
					NeighborList.add(curEdge.GetOpposite(loc));
				// We want there to be a piece in the location owned by a particular owner.
				else if(oppLoc.getPiece() != null && oppLoc.getPiece().getOwner() == owner)
					NeighborList.add(curEdge.GetOpposite(loc));
					
			}
		}
		
		return NeighborList;
	}
	
	private ArrayList<Location> AllNeighbors(Location loc)
	{
		ArrayList<Location> NeighborList = new ArrayList<Location>();
		
		// Find all edges that hold this location
		for (int i=0; i < edge_list.size(); i++)
		{
			// If the edge has the location, add the adjacent location to the list.
			if (edge_list.get(i).HasLocation(loc))
				NeighborList.add(edge_list.get(i).GetOpposite(loc));
		}
		
		return NeighborList;
	}
	
	public Location GetPieceLocation(GamePiece piece)
	{
		for (int i = 0; i < location_list.size(); i++)
			if (location_list.get(i).ContainsPiece(piece))
				return location_list.get(i);
		
		return null;
	}
	
	
	/**************************************
	 * Gameboard Creation Related Methods
	 **************************************/
	
	private Location AddLocation(String label)
	{
		// First see if there is already a location with this label.
		Location newLocation = GetLocationByLabel(label);
		
		// If null, create a new one and add it to the list.
		if (newLocation == null)
		{
			newLocation = new Location(label);
			this.location_list.add(newLocation);
		}
		
		// Return either old location or the newly created one.
		return newLocation;
	}
	private void AddEdge(String label, Location loc1, Location loc2, int align)
	{
		Edge newEdge = new Edge(label, loc1, loc2, align);
		edge_list.add(newEdge);
	}
	
	
	public void PrintEdges()
	{
		for(int i=0; i < edge_list.size(); i++)
			System.out.println(edge_list.get(i));
	}
	
	private void LoadBoard()
	{
		BufferedReader br = null;
		String curLine = "";
		String tokens[], locations[];
		Location newLoc1, newLoc2;
		
		try 
		{
			br = new BufferedReader(new FileReader("board.txt"));
			
			// Load in each line of the file, creating new edges
			// and locations as they are found.
			// Input line format: <EdgeLabel>:<LocLabel1>,<LocLabel2>:<Orientation[0=vert;1=horiz]>
			while ( (curLine = br.readLine()) != null)
			{
				// Split edge label from location labels
				tokens = curLine.split(":");
				
				// Split the location elements apart.
				locations = tokens[1].split(",");
				
				// Get the new locations connected by the edge. 
				// AddLocation will reference current locs if existant already.
				newLoc1 = AddLocation(locations[0]);
				newLoc2 = AddLocation(locations[1]);
				
				AddEdge(tokens[0], newLoc1, newLoc2, Integer.parseInt(tokens[2]));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Count the number of available moves a player has.
	 */
	public int numMovesAvailable(Player player)
	{
		Location curloc;
		int count = 0;
		
		for(int i=0; i < player.getPieces().size(); i++)
		{
			curloc = GetPieceLocation(player.getPiece(i));
			// Is this piece in a gameboard location?
			if (curloc != null)
			{
				// Add to count the amount of neighbors that hold no pieces (null owner)
				count += SomeNeighbors(curloc, 0, null).size();
				count += SomeNeighbors(curloc, 1, null).size();
			}
		}
		
		return count;
	}
	
	/*
	* Prints the board.  This is hard coded for the current board.txt file.
	* I had to sort the location list in the Board constructor.
	*/

	public void printBoard()
	{
		String player;

		for(int i=0; i<3; i++)
		{
			if(location_list.get(i).getPiece() == null){
				player = "  ";
			}else{
				player = location_list.get(i).getPiece().getOwner().getName();
				player = player + location_list.get(i).getPiece().getID();
			}
			player = location_list.get(i).getLabel() + "[" + player + "]";
			System.out.print(player + "\t\t\t");
		}

		System.out.println();
		System.out.print("\t");

		for(int i=3; i<6; i++)
		{
			if(location_list.get(i).getPiece() == null){
				player = "  ";
			}else{
				player = location_list.get(i).getPiece().getOwner().getName();
				player = player + location_list.get(i).getPiece().getID();
			}
			player = location_list.get(i).getLabel() + "[" + player + "]";
			System.out.print(player+ "\t\t");
		}
		
		System.out.println();
		System.out.print("\t\t");

		for(int i=6; i<9; i++){
			if(location_list.get(i).getPiece() == null){
				player = "  ";
			}else{
				player = location_list.get(i).getPiece().getOwner().getName();
				player = player + location_list.get(i).getPiece().getID();
			}
			player = location_list.get(i).getLabel() + "[" + player + "]";
			System.out.print(player + "\t");
		}

		System.out.println();

		for(int i=9; i<12; i++){
			if(location_list.get(i).getPiece() == null){
				player = "  ";
			}else{
				player = location_list.get(i).getPiece().getOwner().getName();
				player = player + location_list.get(i).getPiece().getID();
			}
			player = location_list.get(i).getLabel() + "[" + player + "]";
			System.out.print(player + "\t");
		}

		System.out.print("\t");

		for(int i=12; i<15; i++)
		{
			if(location_list.get(i).getPiece() == null){
				player = "  ";
			}else{
				player = location_list.get(i).getPiece().getOwner().getName();
				player = player + location_list.get(i).getPiece().getID();
			}
			player = location_list.get(i).getLabel() + "[" + player + "]";
			System.out.print(player + "\t");
		}

		System.out.println();

		System.out.print("\t\t");

		for(int i=15; i<18; i++)
		{
			if(location_list.get(i).getPiece() == null){
				player = "  ";
			}else{
				player = location_list.get(i).getPiece().getOwner().getName();
				player = player + location_list.get(i).getPiece().getID();
			}
			player = location_list.get(i).getLabel() + "[" + player + "]";
			System.out.print(player + "\t");
		}

		System.out.println();
		System.out.print("\t");

		for(int i=18; i<21; i++)
		{
			if(location_list.get(i).getPiece() == null){
				player = "  ";
			}else{
				player = location_list.get(i).getPiece().getOwner().getName();
				player = player + location_list.get(i).getPiece().getID();
			}
			player = location_list.get(i).getLabel() + "[" + player + "]";
			System.out.print(player + "\t\t");
		}

		System.out.println();

		for(int i=21; i<24; i++){
			if(location_list.get(i).getPiece() == null){
				player = "  ";
			}else{
				player = location_list.get(i).getPiece().getOwner().getName();
				player = player + location_list.get(i).getPiece().getID();
			}
			player = location_list.get(i).getLabel() + "[" + player + "]";
			System.out.print(player + "\t\t\t");
		}

		System.out.println();

	}

}
