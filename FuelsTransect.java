import java.util.*;

public class FuelsTransect
{
	//declarations
	private String standId;
	private String plotId;
	private int perSlope;
	private double scf;
	private double duffDepth1;
	private double duffDepth2;
	private double duffDepth3;
	private double litterDepth1;
	private double litterDepth2;
	private double litterDepth3;
	private int intsQtr;
	private int ints1;
	private int ints3;
	private ArrayList ThreePSList = new ArrayList();
	private ArrayList ThreePRList = new ArrayList();
	private double threePSSqSum;
	private double threePRSqSum;
	
	//set methods
	public void setStandId(String s)
	{
		standId = s;
	}
	public void setPlotId(String p)
	{
		plotId = p;
	}
	public void setPerSlope(int p)
	{
		perSlope = p;
	}
	public void setScf(double s)
	{
		scf = s;
	}
	public void setDuffDepth1(double d)
	{
		duffDepth1 = d;
	}
	public void setDuffDepth2(double d)
	{
		duffDepth2 = d;
	}
	public void setDuffDepth3(double d)
	{
		duffDepth3 = d;
	}
	public void setLitterDepth1(double l)
	{
		litterDepth1 = l;
	}
	public void setLitterDepth2(double l)
	{
		litterDepth2 = l;
	}
	public void setLitterDepth3(double l)
	{
		litterDepth3 = l;
	}
	public void setIntsQtr(int n)
	{
		intsQtr = n;
	}
	public void setInts1(int n)
	{
		ints1 = n;
	}
	public void setInts3(int n)
	{
		ints3 = n;
	}
	public void setThreePSSqSum(double s)
	{
		threePSSqSum = s;
	}
	public void setThreePRSqSum(double s)
	{
		threePRSqSum = s;
	}
	
	//get methods
	public String getStandId()
	{
		return standId;
	}
	public String getPlotId()
	{
		return plotId;
	}	
	public int getPerSlope()
	{
		return perSlope;
	}
	public double getScf()
	{
		return scf;
	}
	public double getDuffDepth1()
	{
		return duffDepth1;
	}
	public double getDuffDepth2()
	{
		return duffDepth2;
	}
	public double getDuffDepth3()
	{
		return duffDepth3;
	}
	public double getLitterDepth1()
	{
		return litterDepth1;
	}
	public double getLitterDepth2()
	{
		return litterDepth2;
	}
	public double getLitterDepth3()
	{
		return litterDepth3;
	}
	public int getIntsQtr()
	{
		return intsQtr;
	}
	public int getInts1()
	{
		return ints1;
	}
	public int getInts3()
	{
		return ints3;
	}
	public double getThreePSSqSum()
	{
		return threePSSqSum;
	}
	public double getThreePRSqSum()
	{
		return threePRSqSum;
	}
	
	}//end class
