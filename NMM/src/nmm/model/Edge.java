package nmm.model;

public class Edge implements Comparable<Edge>
{
	private String label;
	private Location location1;
	private Location location2;
	private int alignment;
	
	/***
	 * Default edge
	 */
	public Edge ()
	{
		label = "NA";
		location1 = null;
		location2 = null;
		alignment = -1;
	}
	
	/***
	 * Constructor that creates an edge
	 * from the passed in parameters
	 * @param inLabel
	 * @param loc1
	 * @param loc2
	 * @param align
	 */
	public Edge (String inLabel, Location loc1, Location loc2, int align)
	{
		label = inLabel;
		location1 = loc1;
		location2 = loc2;
		alignment = align;
	}
	
	/***
	 * Returns the edges label
	 * @return
	 */
	public String GetLabel()
	{
		return this.label;
	}
	
	/***
	 * Returns the Edges alignment
	 * @return
	 */
	public int GetAlignment()
	{
		return this.alignment;
	}
	
	/***
	 * Determines if the given
	 * Locations has a location
	 * @param loc
	 * @return
	 */
	public boolean HasLocation(Location loc)
	{
		if (location1 == loc || location2 == loc)
			return true;
		else
			return false;
	}
	
	/***
	 * Returns the opposite Location
	 * from the given Location
	 * @param loc
	 * @return
	 */
	public Location GetOpposite(Location loc)
	{
		if (loc == location1)
			return location2;
		else if (loc == location2)
			return location1;
		else
			return null;
	}
	
	/***
	 * Compares the label of the edges for
	 * sorting
	 */
	@Override
	public int compareTo(Edge otherEdge) {
		return this.GetLabel().compareTo(otherEdge.GetLabel());
	}
	
	/***
	 * Returns a readable version of the edge for printing
	 */
	public String toString()
	{
		return String.format("%s[%s:(%s,%s)]", this.GetLabel(), (this.alignment == 0) ? "|" : "-", this.location1.toString(), this.location2.toString());
	}
}
