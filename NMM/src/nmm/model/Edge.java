package nmm.model;

public class Edge implements Comparable<Edge>
{
	private String label;
	private Location location1;
	private Location location2;
	private int alignment;
	
	public Edge ()
	{
		label = "NA";
		location1 = null;
		location2 = null;
		alignment = -1;
	}
	
	public Edge (String inLabel, Location loc1, Location loc2, int align)
	{
		label = inLabel;
		location1 = loc1;
		location2 = loc2;
		alignment = align;
	}
	
	public String GetLabel()
	{
		return this.label;
	}
	
	public int GetAlignment()
	{
		return this.alignment;
	}
	
	public boolean HasLocation(Location loc)
	{
		if (location1 == loc || location2 == loc)
			return true;
		else
			return false;
	}
	
	public Location GetOpposite(Location loc)
	{
		if (loc == location1)
			return location2;
		else if (loc == location2)
			return location1;
		else
			return null;
	}
	
	@Override
	public int compareTo(Edge otherEdge) {
		return this.GetLabel().compareTo(otherEdge.GetLabel());
	}
	
	public String toString()
	{
		return String.format("%s[%s:(%s,%s)]", this.GetLabel(), (this.alignment == 0) ? "|" : "-", this.location1.toString(), this.location2.toString());
	}
}
