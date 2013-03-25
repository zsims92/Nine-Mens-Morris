package nmm.model;

public class Location implements Comparable<Location> {
	
	private String label;
	private GamePiece piece;
	
	/***
	 * Default location constructor
	 */
	public Location ()
	{
		label = "NA";
		piece = null;
	}
	
	/***
	 * Creates a location with the passed
	 * in name
	 * @param name
	 */
	public Location (String name)
	{
		label = name;
		piece = null;
	}

	/***
	 * returns the locations label
	 * @return
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Determines if the location contains
	 * a piece
	 * @param inPiece
	 * @return
	 */
	public boolean ContainsPiece(GamePiece inPiece)
	{
		if (this.getPiece() == inPiece)
			return true;
		else
			return false;
		
	}
	
	/***
	 * Removes the piece from the location
	 */
	public void RemovePiece()
	{
		this.setPiece(null);
	}
	
	/***
	 * Changes the label of the location
	 * @param label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/***
	 * Returns the piece at the location
	 * @return
	 */
	public GamePiece getPiece() {
		return piece;
	}

	/***
	 * Changes the piece at location
	 * to the passed in piece
	 * @param piece
	 */
	public void setPiece(GamePiece piece) {
		this.piece = piece;
	}

	/***
	 * Compares to locations
	 */
	@Override
	public int compareTo(Location otherLoc) {
		return this.getLabel().compareTo(otherLoc.getLabel());
	}
	
	public String toString()
	{
		int id = (this.piece == null) ? -1 : this.piece.getID();
		return this.label + "<" + id + ">";
	}
	
	

}
