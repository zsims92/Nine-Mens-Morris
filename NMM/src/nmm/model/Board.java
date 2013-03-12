package nmm.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JOptionPane;

import nmm.model.user.Player;
import nmm.view.MainWindow;

public class Board {
	
	// Class variables
	private ArrayList<Location> location_list;
	private ArrayList<Edge> edge_list;
	private int current_phase;
	private boolean cheatMode;

	// Game phases
	public static final int GAMEOVER_PHASE = -1;
	public static final int PLACEMENT_PHASE = 0;
	public static final int MOVEMENT_PHASE = 1;
	public static final int REMOVAL_PHASE = 2;
	
	// Number to letter array.
	public static final char[] ALPHABET = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X'};
	public static final String[] BOARDREFERENCE = {"0,0","0,3","0,6","1,1","1,3","1,5","2,2","2,3","2,4","3,0","3,1","3,2","3,4","3,5","3,6","4,2","4,3","4,4","5,1","5,3","5,5","6,0","6,3","6,6"};
	private GamePiece[][] boardArray;
	private MainWindow mw;
	
	//Copy constructor
	public Board(Board bd){
		location_list = bd.location_list;
		edge_list = bd.edge_list;
		current_phase = bd.current_phase;
		boardArray = bd.boardArray;
		cheatMode = bd.cheatMode;
		mw = bd.mw;
	}
	
	public Board(MainWindow mw){
		// Load the edge and location list from file
		location_list = new ArrayList<Location>();
		edge_list = new ArrayList<Edge>();
		this.LoadBoard();
		
		// Sort the location list for printing the visual.
		Collections.sort(location_list);
		
		this.current_phase = PLACEMENT_PHASE;
		this.boardArray = newBoard();
		this.cheatMode = false;
		this.mw = mw;
	}
	
	public void updateBoard(){
		for(int i=0; i<24; i++){
			String t[] = BOARDREFERENCE[i].split(",");
			int row = Integer.parseInt(t[0]);
			int col = Integer.parseInt(t[1]);
			String label = String.valueOf(ALPHABET[i]);
			Location loc = this.GetLocationByLabel(label);
			if(loc.getPiece() != null)
				this.boardArray[row][col] = loc.getPiece();
			else
				this.boardArray[row][col] = new GamePiece(new Player("", "red").getColor(), new Player("", "red"), -1);
		}
	}
	
	public GamePiece[][] getBoardArray() {
		return this.boardArray;
	}

	public int GetCurrentPhase(Player curplayer) {
		// See if there are pieces yet to be placed.
		if (curplayer.getPiecesPlayed() < 9)
			return PLACEMENT_PHASE;

		// See if phase has been set to REMOVAL
		else if (current_phase == REMOVAL_PHASE || current_phase == GAMEOVER_PHASE){
			return current_phase;
		}
		
		// Only other option is movement phase.
		else
			return MOVEMENT_PHASE;
	}
	
	public void setCheatMode(){
		this.cheatMode = true;
	}

	public void SetCurrentPhase(int current_phase) {
		this.current_phase = current_phase;
	}
	
	public Location GetLocationByLabel(String label)
	{
		for(int i = 0; i < location_list.size(); i++)
			if (location_list.get(i).getLabel().equals(label))
				return location_list.get(i);
				
		return null;
	}
	
	private GamePiece[][] newBoard() {
		GamePiece[][] bd = new GamePiece[7][7];
		for(int i=0; i<7; i++)
			for(int j=0; j<7; j++){
				Player p = new Player("", "red");
				bd[i][j] = new GamePiece(p.getColor(), p, -1);
			}
		ArrayList<ArrayList<Integer>> notPlaceableSpots = validSpots();
		int k = 0;
		for(ArrayList<Integer> i: notPlaceableSpots){
			for(Integer j: i){
				bd[k][j] = null;
			}
			k++;
		}
		
		return bd;
	}
	
	private ArrayList<ArrayList<Integer>> validSpots() {
		ArrayList<ArrayList<Integer>> p = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> row0 = new ArrayList<Integer>(Arrays.asList(1,2,4,5));
		ArrayList<Integer> row1 = new ArrayList<Integer>(Arrays.asList(0,2,4,6));
		ArrayList<Integer> row2 = new ArrayList<Integer>(Arrays.asList(0,1,5,6));
		ArrayList<Integer> row3 = new ArrayList<Integer>(Arrays.asList(3));
		ArrayList<Integer> row4 = new ArrayList<Integer>(Arrays.asList(0,1,5,6));
		ArrayList<Integer> row5 = new ArrayList<Integer>(Arrays.asList(0,2,4,6));
		ArrayList<Integer> row6 = new ArrayList<Integer>(Arrays.asList(1,2,4,5));
		
		p.add(row0);
		p.add(row1);
		p.add(row2);
		p.add(row3);
		p.add(row4);
		p.add(row5);
		p.add(row6);
		return p;
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
			JOptionPane.showMessageDialog(this.mw, "Invalid piece id or location label");
			return false;
		}
		
		// Make sure piece selection isn't placed already.
		if (curPiece.getStatus() != GamePiece.UNPLACED)
		{
			JOptionPane.showMessageDialog(this.mw, "Invalid Piece - It is already placed");
			return false;
		}
		
		// Make sure the location is empty
		if (!newLoc.ContainsPiece(null))
		{
			JOptionPane.showMessageDialog(this.mw, "Invalid Location - It contains a piece already");
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
			JOptionPane.showMessageDialog(this.mw, "Invalid piece id or location label");
			return false;
		}
		
		// Make sure piece selection is placed already.
		if (curPiece.getStatus() != GamePiece.PLACED)
		{
			JOptionPane.showMessageDialog(this.mw, "Invalid Piece - It is not placed nor in play");
			return false;
		}
		
			
		// Detect if we are in fly mode. If not, make sure we're adjacent.
		// Make sure the locations are neighbors.
		if (!this.cheatMode && player.getScore() > 3 && !AreNeighbors(curLoc, newLoc))
		{
			JOptionPane.showMessageDialog(this.mw, "Invalid Location - It is not adjacent");
			return false;
		}
		
		// Make sure the location is empty
		if (!newLoc.ContainsPiece(null))
		{
			JOptionPane.showMessageDialog(this.mw, "Invalid Location - It contains a piece already");
			return false;
		}
		
		// We're ok to move the piece.
		newLoc.setPiece(curPiece);
		curLoc.setPiece(null);
		
		// Check for a created mill.
		if (IsMill(newLoc))
		{
			// We will return false so current player is not nexted.
			// Set current phase to removal phase.
			String s = "%s has created a mill!";
			s = String.format(s, player.getName());
			JOptionPane.showMessageDialog(this.mw, s);
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
			JOptionPane.showMessageDialog(this.mw, "Invalid Piece ID - Piece not found");
			return false;
		}
		
		// Make sure piece selection is placed already.
		if (curPiece.getStatus() != GamePiece.PLACED)
		{
			JOptionPane.showMessageDialog(this.mw, "Invalid Piece - It is not placed or alive/in play");
			return false;
		}
		
		
		// We're ok to remove the piece. Change to movement phase.
		curLoc.setPiece(null);
		curPiece.setStatus(GamePiece.DEAD);
		
		
		// Grab the player's new calculated score.
		int score = player.getScore();
		
		if (score == 3){
			String s = "%s has engaged fly mode with 3 pieces remaining";
			s = String.format(s, player.getName());
			JOptionPane.showMessageDialog(this.mw, s);
		}
		
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
		
		ArrayList<Location> nghbrs = SomeNeighbors(loc, dir, owner);
		if (nghbrs.size() == 2)
			return 3;
		else if (nghbrs.size() == 1)
		{
			// See if the neighbor has another adjacent neighbor.
			if(SomeNeighbors(nghbrs.get(0), dir, owner).size() == 2)
				return 3;
			
			return 2;
		}
		return 1;
		
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

}
