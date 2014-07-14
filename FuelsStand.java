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
	private int nlQtrIn;
	private int nl1In;
	private int nl3In;
	private int nl3p;
	private int nQtrIn;
	private int n1In;
	private int n3In;
	private double sumD2ThreePs;
	private double sumD2ThreePr;
	private int nTsects;
	//weights
	private double tonsAcLitter;
	private double tonsAcDuff;
	private double tonsAcQtrIn;
	private double tonsAc1In;
	private double tonsAc3In;
	private double tonsAc3pS;
	private double tonsAc3pR;
	private double totTonsAc;
	
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
	public void setNlQtrIn (int l)
	{
		 nlQtrIn = l;
	}
	public void setNl1In (int l)
	{
		 nl1In = l;
	}
	public void setNl3In (int l)
	{
		 nl3In = l;
	}
	public void setNl3p (int l)
	{
		 nl3p = l;
	}
	public void setNQtrIn (int n)
	{
		 nQtrIn = n;
	}
	public void setN1In (int n)
	{
		 n1In = n;
	}
	public void setN3In (int n)
	{
		 n3In = n;
	}
	public void setSumD2ThreePs (double s)
	{
		 sumD2ThreePs = s;
	}
	public void setSumD2ThreePr (double s)
	{
		 sumD2ThreePr = s;
	}
	public void setTonsAcLitter (double l)
	{
		tonsAcLitter = l;
	}
	public void setTonsAcDuff (double d)
	{
		tonsAcDuff = d;
	}
	public void setTonsAcQtrIn (double t)
	{
		tonsAcQtrIn = t;
	}
	public void setTonsAc1In (double t)
	{
		tonsAc1In = t;
	}
	public void setTonsAc3In (double t)
	{
		tonsAc3In = t;
	}
	public void setTonsAc3pS (double t)
	{
		tonsAc3pS = t;
	}
	public void setTonsAc3pR (double t)
	{
		tonsAc3pR = t;
	}
	public void setTotTonsAc (double t)
	{
		totTonsAc = t;
	}
	public void setNTesects (int n)
	{
		nTsects = n;
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
	public int getNlQtrIn()
	{
		return nlQtrIn;
	}
	public int getNl1In()
	{
		return nl1In;
	}
	public int getNl3In()
	{
		return nl3In;
	}
	public int getNl3p()
	{
		return nl3p;
	}
	public int getNQtrIn()
	{
		return nQtrIn;
	}
	public int getN1In()
	{
		return n1In;
	}
	public int getN3In()
	{
		return n3In;
	}
	public double getSumD2ThreePs()
	{
		return sumD2ThreePs;
	}
	public double getSumD2ThreePr()
	{
		return sumD2ThreePr;
	}
	public double getTonsAcLitter()
	{
		return tonsAcLitter;
	}
	public double getTonsAcDuff()
	{
		return tonsAcDuff;
	}
	public double getTonsAcQtrIn()
	{
		return tonsAcQtrIn;
	}
	public double getTonsAc1In()
	{
		return tonsAc1In;
	}
	public double getTonsAc3In()
	{
		return tonsAc3In;
	}
	public double getTonsAc3pS()
	{
		return tonsAc3pS;
	}
	public double getTonsAc3pR()
	{
		return tonsAc3pR;
	}
	public double getTotTonsAc()
	{
		return totTonsAc;
	}
	public int getNTsects()
	{
		return nTsects;
	}
}//end class