package nmm.model;

public class Location implements Comparable<Location> {
	
	private String label;
	private GamePiece piece;
	
	public Location ()
	{
		label = "NA";
		piece = null;
	}
	
	public Location (String name)
	{
		label = name;
		piece = null;
	}

	public String getLabel() {
		return label;
	}

	public boolean ContainsPiece(GamePiece inPiece)
	{
		if (this.getPiece() == inPiece)
			return true;
		else
			return false;
		
	}
	public void RemovePiece()
	{
		this.setPiece(null);
	}
	public void setLabel(String label) {
		this.label = label;
	}

	public GamePiece getPiece() {
		return piece;
	}

	public void setPiece(GamePiece piece) {
		this.piece = piece;
	}

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
