//takes 3 input CSV files and an output CSV file
//Processes inputs and calculates outputs
//References FuelsStand and FuelsTransect classes
//Also references BrownFuelsCalc class
import java.io.*;
import java.util.*;
import javax.swing.*;

public class FuelsProcessor
{
	//declarations
	//output File
	private File outputFile;
	//input Files
	private File standFile, transectFile, dFile;
	//Abstract Data Types to represent stand and transect
	private FuelsTransect tsect = new FuelsTransect();
	private FuelsStand stand = new FuelsStand();
	
	//constructor
	FuelsProcessor(File oF, File sF, File tF, File dF)
	{
		//assign files specified from GUI
		outputFile = oF;
		standFile = sF;
		transectFile = tF;
		dFile = dF;
		//delimiter
		final String DELIMITER = ",";
		
		try
		{
			//variables for holding file lines as Strings
			String standLine = "";
			String tsectLine = "";
			String dLine = "";
			//binary variables to indicate first line of file
			boolean standFirstLn = true;
			boolean tsectFirstLn = true;
			boolean dFirstLn = true;
			//other variables
			int nStands = 0;
			//Create and initialize stand file reader
			BufferedReader standBr = new BufferedReader(new FileReader(standFile));
			//Create file writer for outputs
			BufferedWriter out = new BufferedWriter(new FileWriter(outputFile));
			//write header to output file------------------------------------------------------------
			out.write("StandId,Date,AvgPerSlope,SlopeCorFactor,AvgLitterIn,AvgDuffIn,LitterTonsAc,"+
			"DuffTonsAc,QtrInTonsAc,OneInTonsAc,ThreeInTonsAc,ThreeInPlsSTonsAc,"+
			"ThreeInPlsRTonsAc,TotTonsAc\n");
			
			//read stand file line by line
			while((standLine = standBr.readLine()) != null)
			{
				BufferedReader tsectBr = new BufferedReader(new FileReader(transectFile));
				BufferedReader dBr = new BufferedReader(new FileReader (dFile));
				//skip first line containing headers
				if (standFirstLn)
					standFirstLn = false;
				else
				{
					//define individual stand level variables
					int nTsects = 0;
					int perSlopeTot = 0;
					double perSlopeAvg = 0.0;
					double tsectLitterAvg = 0.0;
					double standLitterAvgSum = 0.0;
					double tsectDuffAvg = 0.0;
					double standDuffAvgSum = 0.0;
					int standNQtrIn = 0;
					int standN1In = 0;
					int standN3In = 0;
					double sumD2s = 0.0;
					double sumD2r =0.0;
					
					
					++nStands;
					//get tokens for each stand in line
					String[] standTokens = standLine.split(DELIMITER);
					stand.setStandId(standTokens[0]);
					stand.setDate(standTokens[1]);
					stand.setPlane1(Integer.parseInt(standTokens[2]));
					stand.setPlane3(Integer.parseInt(standTokens[3]));
					stand.setPlaneThreeP((Integer.parseInt(standTokens[4])));
					
					//loop to process transect data for each stand
					while((tsectLine = tsectBr.readLine()) != null)
							{
								if(tsectFirstLn)
									tsectFirstLn = false;
								else
								{
									//get tokens for each transect in line 
									String[] tsectTokens = tsectLine.split(DELIMITER);
									tsect.setStandId(tsectTokens[0]);
									if(stand.getStandId().equals(tsect.getStandId()))
									{
										++nTsects;
										//assign transect variables from tokens
										tsect.setPerSlope(Integer.parseInt(tsectTokens[2]));
										tsect.setLitterDepth1(Double.parseDouble(tsectTokens[3]));
										tsect.setLitterDepth2(Double.parseDouble(tsectTokens[4]));
										tsect.setLitterDepth3(Double.parseDouble(tsectTokens[5]));
										tsect.setDuffDepth1(Double.parseDouble(tsectTokens[6]));
										tsect.setDuffDepth2(Double.parseDouble(tsectTokens[7]));
										tsect.setDuffDepth3(Double.parseDouble(tsectTokens[8]));
										tsect.setIntsQtr(Integer.parseInt(tsectTokens[9]));
										tsect.setInts1(Integer.parseInt(tsectTokens[10]));
										tsect.setInts3(Integer.parseInt(tsectTokens[11]));
										//calculations per transect
										perSlopeTot += tsect.getPerSlope();
										tsectLitterAvg = (tsect.getLitterDepth1()+tsect.getLitterDepth2()+tsect.getLitterDepth3())/3;
										standLitterAvgSum += tsectLitterAvg;
										tsectDuffAvg =(tsect.getDuffDepth1()+tsect.getDuffDepth2()+tsect.getDuffDepth3())/3;
										standDuffAvgSum += tsectDuffAvg;
										standNQtrIn += tsect.getIntsQtr();
										standN1In += tsect.getInts1();
										standN3In += tsect.getInts3();
									}
								}//end tsect else tsectFirstLn
							}//end tsect while
					tsectFirstLn = true;
					tsectBr.close();
					
					//loop to process diameter data for each stand
					while((dLine = dBr.readLine()) != null)
					{
						if(dFirstLn)
							dFirstLn = false;
						else
						{
							//get tokens for each set of diameters in line
							String[] dTokens = dLine.split(DELIMITER);
							if(stand.getStandId().equals(dTokens[0]))
							{
								//calculate sum of Diameters Squared for each stand
								sumD2s += (Double.parseDouble(dTokens[2])*Double.parseDouble(dTokens[2]));
								sumD2r += (Double.parseDouble(dTokens[3])*Double.parseDouble(dTokens[3]));
							}
						}//end d else dFirstLn
							
					}//end d while
					dFirstLn = true;
					dBr.close();
					
					//stand level calcs from tsects
					stand.setNTesects(nTsects);
					perSlopeAvg = (double)perSlopeTot/(double)stand.getNTsects();
					stand.setAvgPerSlope(perSlopeAvg);
					stand.setC(BrownFuelsCalc.scfCalc(stand.getAvgPerSlope()));
					stand.setAvgLitterDepth(standLitterAvgSum/stand.getNTsects());
					stand.setAvgDuffDepth(standDuffAvgSum/stand.getNTsects());
					//total tallies for 0.1 - 3" material
					stand.setNQtrIn(standNQtrIn);
					stand.setN1In(standN1In);
					stand.setN3In(standN3In);
					//total plane lengths
					//transect lengths for 0-0.25" and 0.25-1.0" material are the same
					stand.setNlQtrIn(stand.getPlane1()*stand.getNTsects());
					stand.setNl1In(stand.getNlQtrIn());
					stand.setNl3In(stand.getPlane3()*stand.getNTsects());
					stand.setNl3p(stand.getPlaneThreeP()*stand.getNTsects());
					//tons/acre calculations
					stand.setTonsAcLitter(BrownFuelsCalc.tonsLitter(stand.getAvgLitterDepth()));
					stand.setTonsAcDuff(BrownFuelsCalc.tonsDuff(stand.getAvgDuffDepth()));
					stand.setTonsAcQtrIn(BrownFuelsCalc.nrcTonsQtrIn(stand.getNQtrIn(), stand.getNlQtrIn(), 
							stand.getC()));
					stand.setTonsAc1In(BrownFuelsCalc.nrcTons1in(stand.getN1In(), stand.getNl1In(), 
							stand.getC()));
					stand.setTonsAc3In(BrownFuelsCalc.nrcTons3in(stand.getN3In(), stand.getNl3In(),
							stand.getC()));
					stand.setTonsAc3pS(BrownFuelsCalc.nrcTons3pS(stand.getNl3p(), stand.getC(), sumD2s));
					stand.setTonsAc3pR(BrownFuelsCalc.nrcTons3pS(stand.getNl3p(), stand.getC(), sumD2r));
					stand.setTotTonsAc(stand.getTonsAcLitter()+stand.getTonsAcDuff()+stand.getTonsAcQtrIn()+
							stand.getTonsAc1In()+stand.getTonsAc3In()+stand.getTonsAc3pS()+
							stand.getTonsAc3pR());
					
					//write output line to file----------------------------------------------------------------
					out.write(stand.getStandId()+","+stand.getDate()+","+stand.getAvgPerSlope()+","+stand.getC()+","+
					stand.getAvgLitterDepth()+","+stand.getAvgDuffDepth()+","+stand.getTonsAcLitter()+
					","+stand.getTonsAcDuff()+","+stand.getTonsAcQtrIn()+","+stand.getTonsAc1In()+","+
					stand.getTonsAc3In()+","+stand.getTonsAc3pS()+","+stand.getTonsAc3pR()+","+
					stand.getTotTonsAc()+"\n");
				}//end stand else standFirstLn
			}//end stand while
			standBr.close();
			out.close();
			//confirm processing
			JOptionPane.showMessageDialog(null, "Processing Complete!\n"+nStands+" Stands processed.");
		}//end try
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}//end catch
	}//end constructor
}