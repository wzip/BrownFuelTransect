public class FuelsStand
{
	//declarations
	String standId;
	private int plane1;
	private int plane3;
	private int planeThreeP;
	private double avgPerSlope;
	private double c;
	private double avgLitterDepth;
	private double avgDuffDepth;
	private double tonsAcLitter;
	private double tonsAcDuff;
	
	//set methods
	public void setStandId (String s)
	{
		standId = s;
	}
	public void setPlane1 (int p)
	{
		plane1 = p;
	}
	public void setPlane3 (int p)
	{
		plane3 = p;
	}
	public void setPlaneThreeP (int p)
	{
		planeThreeP = p;
	}
	public void setAvgPerSlope (double s)
	{
		avgPerSlope = s;
	}
	public void setC (double s)
	{
		c = s;
	}
	public void setAvgLitterDepth (double l)
	{
		avgLitterDepth = l;
	}
	public void setAvgDuffDepth (double d)
	{
		avgDuffDepth = d;
	}
	public void setTonsAcLitter (double l)
	{
		tonsAcLitter = l;
	}
	public void setTonsAcDuff (double d)
	{
		tonsAcDuff = d;
	}
	
	//get methods
	public String getStandId()
	{
		return standId;
	}
	public int getPlane1()
	{
		return plane1;
	}
	public int getPlane3()
	{
		return plane3;
	}
	public int getPlaneThreeP()
	{
		return planeThreeP;
	}
	public double getC()
	{
		return c;
	}
	public double getAvgPerSlope()
	{
		return avgPerSlope;
	}
	public double getAvgLitterDepth()
	{
		return avgLitterDepth;
	}
	public double getAvgDuffDepth()
	{
		return avgDuffDepth;
	}
	public double getTonsAcLitter()
	{
		return tonsAcLitter;
	}
	public double getTonsAcDuff()
	{
		return tonsAcDuff;
	}
	
}//end class