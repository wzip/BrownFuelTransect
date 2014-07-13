
//Functions for calculating Brown Fuels from transects
public class BrownFuelsCalc
{
	//constants
	final static double D2QTR = 0.0151;
	final static double D2ONE = 0.289;
	final static double D2THREE = 2.76;
	final static double SQTR = 0.48;
	final static double S1 = 0.48;
	final static double S3 = 0.40;
	final static double S3PS = 0.40;
	final static double S3PR = 0.30;
	final static double AQTR = 1.13;
	final static double A1 = 1.13;
	final static double A3 = 1.13;
	final static double A3PS = 1.0;
	final static double A3PR = 1.0;
	
	//slope correction factor
	//input is % slope
	public static double scfCalc(double s)
	{
		double c;
		double slopeSq;
		slopeSq = Math.pow((s/100.00), 2);
		c = Math.sqrt((1+slopeSq));
		return c;
	}//end scfCalc
	
	//tons/acre litter
	//from University of Florida,
	//School of Forest Resources & Conservation 
	//fuel cheatsheet (2.75 lbs/cuft)
	public static double tonsLitter(double avgInchesLitter)
	{
		double t;
		t = 0.4159375*avgInchesLitter;
		return t;
	}//end tonsLitter
	
	//tons/acre duff
	//from University of Florida,
	//School of Forest Resources & Conservation 
	//fuel cheatsheet (5.5 lbs/cuft)
	public static double tonsDuff(double avgInchesDuff)
	{
		double t;
		t = 0.831875*avgInchesDuff;
		return t;
	}//end tonsDuff
	
	
	//tons/acre 0" - 0.25"
	//inputs: number intersections, tot. length of sampling plane,
	//avg. slope correction factor
	public static double tonsQtrIn(int n, int nL, double c)
	{
		double t;
		t = (11.64*(double)n*D2QTR*SQTR*c*AQTR)/(double)nL;
		return t;
	}//end tonsQtrIn
	
	//tons/acre 0.25" - 1"
	//inputs: number intersections, tot. length of sampling plane,
	//avg. slope correction factor
	public static double tons1in(int n, int nL, double c)
	{
		double t;
		t = (11.64*(double)n*D2ONE*S1*c*A1)/(double)nL;
		return t;
	}//end tons1in
	
	//tons/acre 1" - 3"
	//inputs: number intersections, tot. length of sampling plane,
	//avg. slope correction factor
	public static double tons3in(int n, int nL, double c)
	{
		double t;
		t = (11.64*(double)n*D2THREE*S3*c*A3)/(double)nL;
		return t;
	}//end tons3in
	
	//tons/acre 3+" sound
	//inputs: tot. length of sampling plane,
	//avg. slope correction factor, sum of d squared
	public static double tons3pS(int nL, double c, double sumD2)
	{
		double t;
		t = (11.64*sumD2*S3PS*c*A3PS)/(double)nL;
		return t;
	}//end tons3pS
	
	//tons/acre 3+" rotten
	//inputs: tot length of sampling plane,
	//avg. slope correction factor, sum of d squared
	public static double tons3pR(int nL, double c, double sumD2)
	{
		double t;
		t = (11.64*sumD2*S3PR*c*A3PR)/(double)nL;
		return t;
	}//end tons3pR
	
	//USFS Northern Region Composite Equations
	
	//USFS Northern Region Composite tons/acre 0" - 0.25"
	//inputs: number intersections, tot. length of sampling plane,
	//avg. slope correction factor
	public static double nrcTonsQtrIn(int n, int nL, double c)
	{
		double t;
		t = 0.09533*((n*c)/nL);
		return t;
	}//end nrcTonsQtrIn
	
	//USFS Northern Region Composite tons/acre 0.25" - 1"
	//inputs: number intersections, tot. length of sampling plane,
	//avg. slope correction factor
	public static double nrcTons1in(int n, int nL, double c)
	{
		double t;
		t = 1.825*((n*c)/nL);
		return t;
	}//end nrcTons1in
	
	//USFS Northern Region Composite tons/acre 1" - 3"
	//inputs: number intersections, tot. length of sampling plane,
	//avg. slope correction factor
	public static double nrcTons3in(int n, int nL, double c)
	{
		double t;
		t = 14.52*((n*c)/nL);
		return t;
	}//end nrcTons3In
	
	//USFS Northern Region Composite tons/acre 3+" sound
	//inputs: tot. length of sampling plane,
	//avg. slope correction factor, sum of d squared
	public static double nrcTons3pS(int nL, double c, double sumD2)
	{
		double t;
		t = 4.656*((sumD2*c)/nL);
		return t;
	}//end nrcTons3pS
	
	//USFS Northern Region Composite tons/acre 3+" sound
	//inputs: tot. length of sampling plane,
	//avg. slope correction factor, sum of d squared
	public static double nrcTons3pR(int nL, double c, double sumD2)
	{
		double t;
		t = 3.492*((sumD2*c)/nL);
		return t;
	}//end nrcTons3pR
}