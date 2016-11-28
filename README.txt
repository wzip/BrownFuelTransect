Version 1.0 Beta

The purpose of this project is to calculate weights of fine fuels and downed woody material 
using the inventory methods described in Brown, 1974, "The Handbook for Inventorying 
Downed Woody Material", USDA Forest Service (http://www.fs.fed.us/rm/pubs_int/int_gtr016.pdf).  
It uses the equations in the handbook for a composite of species for the USDA Forest Service, 
Northern Region to calculate fuel weights in tons per acre with exceptions for litter and duff 
weights. For litter and duff weights, it uses the bulk densities given in the University of 
Florida, Fuel Load Calculations Cheatsheet, Dr. Leda Kobziar.  The calculator takes 3 CSV 
files for input; one for stand information including transect lengths, another for duff 
depths and transect tallies up to 3 inches, and a third for diameters of material over 3 inches 
recorded for each transect.  The outputs should be a CSV file containing the fuel weights 
in tons per acre for each size class and total fuel weight in tons per acre for each stand.

This program is meant to be exported as an executable JAR File.  The BrownFuelsCalculator 
class contains a main() function and can be compiled with its dependencies.  
The DownedFuelsInventory.xls file included with the sources is a blank spreadsheet with 
each tab representing a separate CSV file that would be filled during tally.  Be sure to 
put 0's in the cells where no fuels are tallied.  For the diameters, put at least one 0 
in each row per transect for sound and rotten fuels in this category if none are tallied.  
This program does not have much error checking built into the FuelsProcessor class.  In other 
words it doesn't check to make sure that all stands have transects, or warn the user if 
transects exist without a matching stand, or vice versa, etc.