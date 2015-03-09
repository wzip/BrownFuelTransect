//GUI For Brown Fuels Calculator
//Gets 4 files from user
//References FuelsProcessor class
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class FuelsGUI extends JFrame
implements ActionListener
{
	//declarations
	//labels and text fields
	private JLabel header = new JLabel("Brown Fuels Calculator");
	private JLabel version = new JLabel("     Version 1.0    ");
	private JTextField outputFileTxtFld = new JTextField("Output CSV", 20);
	private JTextField standTxtFld = new JTextField("Stand Data CSV", 20);
	private JTextField transectTxtFld = new JTextField("Transect Data CSV", 20);
	private JTextField dTxtFld = new JTextField("3+ Diameters CSV", 20);
	private JLabel processLbl = new JLabel("     Process Data!     ");
	//buttons
	private JButton outputButton = new JButton("Output Browse");
	private JButton standButton = new JButton("Stand Data Browse");
	private JButton transectButton = new JButton("Transect Data Browse");
	private JButton dButton = new JButton("Diameters Browse");
	private JButton processButton = new JButton("Process Data");
	//JPanels
	private JPanel headerPanel = new JPanel();
	private JPanel outputPanel = new JPanel();
	private JPanel inputPanel01 = new JPanel();
	private JPanel inputPanel02 = new JPanel();
	private JPanel inputPanel03 = new JPanel();
	private JPanel processPanel = new JPanel();
	//JMenuBar, JMenus, and JMenuItems
	private JMenuBar mainMenuBar = new JMenuBar();
	private JMenu aboutMenu = new JMenu("About");
	private JMenuItem licenseItem = new JMenuItem("License");
	private JMenuItem aboutItem = new JMenuItem("About");
	//files
	File[] files = new File[4];
	//other vars and constants
	private final int ROWS = 6;
	private final int COLS = 1;
	private final int GAP = 2;
	Font lgBold = new Font("Arial", Font.BOLD, 28);
	Font medBold = new Font("Arial", Font.BOLD, 18);
	Font smBold = new Font("Arial", Font.BOLD, 14);
	private Container con = getContentPane();
	//define variable for path of last file selected
	//so that next chooser is opens last directory opened
	private String lastFilePath = "";
	//boolean variables
	private boolean outputSel = false;
	private boolean standSel = false;
	private boolean transectSel = false;
	private boolean dSel = false;
	
	//constructor
	FuelsGUI()
	{
		super("Brown Fuels Calculator");
		super.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		con.setLayout(new GridLayout(ROWS,COLS,GAP,GAP));
		this.setSize(325, 500);
		//add main menu bar, menus, and menu items
		setJMenuBar(mainMenuBar);
		mainMenuBar.add(aboutMenu);
		aboutMenu.add(licenseItem);
		licenseItem.addActionListener(this);
		aboutMenu.add(aboutItem);
		aboutItem.addActionListener(this);

		//add JPanels
		con.add(headerPanel);
		con.add(inputPanel01);
		con.add(inputPanel02);
		con.add(inputPanel03);
		con.add(outputPanel);
		con.add(processPanel);
		
		//add JPanel contents
		//header panel
		header.setFont(medBold);
		headerPanel.add(header);
		version.setFont(smBold);
		headerPanel.add(version);
		//output panel
		outputPanel.add(outputFileTxtFld);
		outputPanel.add(outputButton);
		outputButton.addActionListener(this);
		//input panel01
		inputPanel01.add(standTxtFld);
		inputPanel01.add(standButton);
		standButton.addActionListener(this);
		//input panel 02
		inputPanel02.add(transectTxtFld);
		inputPanel02.add(transectButton);
		transectButton.addActionListener(this);
		//input panel 03
		inputPanel03.add(dTxtFld);
		inputPanel03.add(dButton);
		dButton.addActionListener(this);
		//process panel
		processLbl.setFont(lgBold);
		processPanel.add(processLbl);
		processButton.setEnabled(false);
		processPanel.add(processButton);
		processButton.addActionListener(this);
		
	}//end constructor
	
	//actionPerformed()
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();
		//Determine which button was clicked
		switch (command)
		{
		case "About":
			{
			JOptionPane.showMessageDialog(null,
					"                                               Brown Fuels Calculator\n"
					+ "                                                          Version 1.0\n\n"
					+ "The purpose of this project is to calculate weights of fine fuels and downed woody\n "
					+ "material using the inventory methods described in Brown, 1974, \"The Handbook for \n"
					+ "Inventorying Downed Woody Material\", USDA Forest Service\n"
					+ "(http://www.fs.fed.us/rm/pubs_int/int_gtr016.pdf).  It uses the equations in the\n"
					+ "handbook for a composite of species for the USDA Forest Service, Northern Region\n"
					+ "to calculate fuel weights in tons per acre with exceptions for litter and duff weights.\n"
					+ "For litter and duff weights, it uses the bulk densities given in the University of\n"
					+ "Florida, Fuel Load Calculations Cheatsheet, Dr. Leda Kobziar.",
					"About", JOptionPane.INFORMATION_MESSAGE);
			break;
			}//end case
	case "License":
			{
			License l = new License();
			break;
			}//end case

		case "Stand Data Browse":
			{
				//spawn chooser for stand data
				JFileChooser chooser = new JFileChooser(lastFilePath);
				//define return value and get File Object from chooser
				int returnValue = chooser.showOpenDialog(this);
		        if (returnValue == JFileChooser.APPROVE_OPTION) 
		        {
		          files[1] = chooser.getSelectedFile();
		          standTxtFld.setText(files[1].getPath());
		          lastFilePath = files[1].getParent();
		          standSel = true;
		          if (standSel&&transectSel&&dSel&&outputSel)
		          {
		        	  processButton.setEnabled(true);
		          }
		        }
		        break;
			}//end case
		case "Transect Data Browse":
			{
				//spawn chooser for transect data
				JFileChooser chooser = new JFileChooser(lastFilePath);
				//define return value and get File Object from chooser
				int returnValue = chooser.showOpenDialog(this);
				if (returnValue == JFileChooser.APPROVE_OPTION) 
				{
					files[2] = chooser.getSelectedFile();
					transectTxtFld.setText(files[2].getPath());
					lastFilePath = files[2].getParent();
					transectSel = true;
					if (standSel&&transectSel&&dSel&&outputSel)
			        {
						processButton.setEnabled(true);
			        }
				}
				break;
			}//end case
		case "Diameters Browse":
			{
				//spawn chooser for diameters for 3+ inch material
				JFileChooser chooser = new JFileChooser(lastFilePath);
				//define return value and get File Object from chooser
				int returnValue = chooser.showOpenDialog(this);
				if (returnValue == JFileChooser.APPROVE_OPTION) 
				{
					files[3] = chooser.getSelectedFile();
					dTxtFld.setText(files[3].getPath());
					lastFilePath = files[3].getParent();
					dSel = true;
					if (standSel&&transectSel&&dSel&&outputSel)
			        {
						processButton.setEnabled(true);
			        }
				}
				break;
			}//end case
		case "Output Browse":
			{
				//spawn chooser for output file
				JFileChooser chooser = new JFileChooser(lastFilePath);
				//define return value and get File Object from chooser
				int returnValue = chooser.showOpenDialog(this);
		        if (returnValue == JFileChooser.APPROVE_OPTION) 
		        {
		          files[0] = chooser.getSelectedFile();
		          //check and warn user if output file already exists
		          if (files[0].exists())
		          {
		        	  JOptionPane.showMessageDialog(null, 
		        			  "The slected output file already exists and will be\n"+
		        			  "overwritten if you process data!", 
		        			  "Warning", JOptionPane.WARNING_MESSAGE);
		          }
		          outputFileTxtFld.setText(files[0].getPath());
		          lastFilePath = files[0].getParent();
		          outputSel = true;
		          if (standSel&&transectSel&&dSel&&outputSel)
		          {
						processButton.setEnabled(true);
			      }
		        }
		        break;
			}//end case
		case "Process Data":
			{
				//initialize FuelsProcessor for file processing
				FuelsProcessor proc = new FuelsProcessor(files[0],files[1],files[2],files[3]);
			}//end case
		
		}//end switch
	}//end actionPerformed()
}