package nmm.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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

	
	// Stuff from Zach's gui
	private int[][] boardArray;
	
	public Board(){
		// Load the edge and location list from file
		location_list = new ArrayList<Location>();
		edge_list = new ArrayList<Edge>();
		this.LoadBoard();
		
		this.current_phase = PLACEMENT_PHASE;
		//this.PrintEdges();
		
		//this.boardArray = newBoard();
		
	}

	public int GetCurrentPhase(Player curplayer) {
		// See if there are pieces yet to be placed.
		if (curplayer.getPiecesPlayed() < 9)
			return PLACEMENT_PHASE;

		// See if phase has been set to REMOVAL
		else if (current_phase == REMOVAL_PHASE)
			return current_phase;
		
		// Only other option is movement phase.
		else
			return MOVEMENT_PHASE;
	}

	public void SetCurrentPhase(int current_phase) {
		this.current_phase = current_phase;
	}
	


	
	private Edge GetEdgeByLabel(String label)
	{
		for(int i = 0; i < edge_list.size(); i++)
			if (edge_list.get(i).GetLabel().equals(label))
				return edge_list.get(i);
				
		return null;
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
			System.out.println("| Invalid Piece - It is not unplaced.");
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
	
	private ArrayList<Location> AllNeighbors(GamePiece piece)
	{
		Location loc = GetPieceLocation(piece);
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
	private int[][] newBoard() {
		int[][] bd = new int[7][7];
		for(int i=0; i<7; i++)
			for(int j=0; j<7; j++){
				bd[i][j] = 1;
			}
		ArrayList<ArrayList<Integer>> placeableSpots = validSpots();
		int k = 0;
		for(ArrayList<Integer> i: placeableSpots){
			for(Integer j: i){
				bd[k][j] = -1;
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
	
	public int[][] getBoardArray() {
		return this.boardArray;
	}
*/
}
