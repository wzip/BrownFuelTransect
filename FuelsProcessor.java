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
			//Create and initialize stand file reader
			BufferedReader standBr = new BufferedReader(new FileReader(standFile));
			//Create file writer for outputs
			BufferedWriter out = new BufferedWriter(new FileWriter(outputFile));
			//write header to output file------------------------------------------------------------
			out.write("StandId,AvgPerSlope,SlopeCorFactor,AvgLitterIn,AvgDuffIn,LitterTonsPerAc,"+
			"DuffTonsPerAc\n");
			
			//read stand file line by line
			while((standLine = standBr.readLine()) != null)
			{
				BufferedReader tsectBr = new BufferedReader(new FileReader(transectFile));
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
					//get tokens for each stand in line
					String[] standTokens = standLine.split(DELIMITER);
					stand.setStandId(standTokens[0]);
					stand.setPlane1(Integer.parseInt(standTokens[1]));
					stand.setPlane3(Integer.parseInt(standTokens[2]));
					stand.setPlaneThreeP((Integer.parseInt(standTokens[3])));
					//test output
					System.out.println("StandID: "+stand.getStandId());
					
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
										//MORE TO WRITE HERE!!!
										
										//calculations per transect
										perSlopeTot += tsect.getPerSlope();
										tsectLitterAvg = (tsect.getLitterDepth1()+tsect.getLitterDepth2()+tsect.getLitterDepth3())/3;
										standLitterAvgSum += tsectLitterAvg;
										tsectDuffAvg =(tsect.getDuffDepth1()+tsect.getDuffDepth2()+tsect.getDuffDepth3())/3;
										standDuffAvgSum += tsectDuffAvg;
										
										//test output
										System.out.println("     " +tsect.getStandId());
									}
								}//end tsect else tsectFirstLn
							}//end tsect while
					tsectFirstLn = true;
					tsectBr.close();
					
					//stand level calcs from tsects
					perSlopeAvg = (double)perSlopeTot/(double)nTsects;
					stand.setAvgPerSlope(perSlopeAvg);
					stand.setC(BrownFuelsCalc.scfCalc(stand.getAvgPerSlope()));
					stand.setAvgLitterDepth(standLitterAvgSum/nTsects);
					stand.setAvgDuffDepth(standDuffAvgSum/nTsects);
					stand.setTonsAcLitter(BrownFuelsCalc.tonsLitter(stand.getAvgLitterDepth()));
					stand.setTonsAcDuff(BrownFuelsCalc.tonsDuff(stand.getAvgDuffDepth()));
					
					//test output
					System.out.println("     nTescts = "+nTsects);
					//write output line to file----------------------------------------------------------------
					out.write(stand.getStandId()+","+stand.getAvgPerSlope()+","+stand.getC()+","+
					stand.getAvgLitterDepth()+","+stand.getAvgDuffDepth()+","+stand.getTonsAcLitter()+
					","+stand.getTonsAcDuff()+"\n");
				}//end stand else standFirstLn
			}//end stand while
			//tsectBr.close();
			standBr.close();
			out.close();
		}//end try
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error: "+e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}//end catch
	}//end constructor
}