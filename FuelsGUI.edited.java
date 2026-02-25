//GUI For Brown Fuels Calculator
//Gets 4 files from user
//References FuelsProcessor class
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;
import java.io.*;

public class FuelsGUI extends JFrame
implements ActionListener
{
	//declarations
	//labels and text fields
	private JLabel header = new JLabel("Brown Fuels Calculator");
	private JLabel version = new JLabel("Version 1.1");
	private JTextField outputFileTxtFld = new JTextField("Output CSV", 20);
	private JTextField standTxtFld = new JTextField("Stand Data CSV", 20);
	private JTextField transectTxtFld = new JTextField("Transect Data CSV", 20);
	private JTextField dTxtFld = new JTextField("3+ Diameters CSV", 20);
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
	private JMenu howtoMenu = new JMenu("How to Use");
	private JMenu licenseMenu = new JMenu("License");
	private JMenuItem licenseItem = new JMenuItem("License");
	private JMenuItem aboutItem = new JMenuItem("About");
	private JMenuItem standItem = new JMenuItem("Stand Data");
	private JMenuItem transectItem = new JMenuItem("Transect Data");
	private JMenuItem diameterItem = new JMenuItem ("Diameter Data");
	private JMenuItem outputItem = new JMenuItem ("Output Data");
	private JMenuItem howtoGuide = new JMenuItem("How to Use Guide");
	
	//files
	File[] files = new File[4];
	//other vars and constants
	private final int ROWS = 6;
	private final int COLS = 1;
	private final int GAP = 2;
	Font lgBold = new Font("Arial", Font.BOLD, 30);
	Font medBold = new Font("Arial", Font.BOLD, 20);
	Font smBold = new Font("Arial", Font.BOLD, 16);
	private Container con = getContentPane();
	//define variable for path of last file selected
	//so that next chooser is opens last directory opened
	private String lastFilePath = "";
	//boolean variables
	private boolean outputSel = false;
	private boolean standSel = false;
	private boolean transectSel = false;
	private boolean dSel = false;

	// components for card-based info display
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private JPanel mainPanel;
	private JPanel infoPanel;
	private JLabel infoTitleLabel;
	private JTextPane infoTextArea;
	private JButton backButton;
	
	//constructor
	FuelsGUI()
	{
		super("Brown Fuels Calculator");
		try {
			super.setResizable(false);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			// initialize card layout and panels
			cardLayout = new CardLayout();
			cardPanel = new JPanel(cardLayout);
			mainPanel = new JPanel(new GridLayout(ROWS,COLS,GAP,GAP));
			this.setSize(500, 500);
			//add main menu bar, menus, and menu items
		setJMenuBar(mainMenuBar);
		mainMenuBar.add(aboutMenu);
		mainMenuBar.add(howtoMenu);
		mainMenuBar.add(licenseMenu);
		licenseMenu.add(licenseItem);
		licenseItem.addActionListener(this);
		aboutMenu.add(aboutItem);
		aboutItem.addActionListener(this);
		howtoMenu.add(howtoGuide);
		howtoMenu.add(standItem);
		howtoMenu.add(transectItem);
		howtoMenu.add(diameterItem);
		howtoMenu.add(outputItem);
		howtoGuide.addActionListener(this);
		standItem.addActionListener(this);
		transectItem.addActionListener(this);
		diameterItem.addActionListener(this);
		outputItem.addActionListener(this);

		//button color
		processButton.setBackground(Color.GRAY);
		processButton.setForeground(Color.BLACK);
		processButton.setFocusPainted(false);
		processButton.setOpaque(true);
		// change background when enabled state changes
		processButton.addPropertyChangeListener("enabled", new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				boolean enabled = (Boolean) evt.getNewValue();
				processButton.setBackground(enabled ? Color.RED : Color.GRAY);
			}
		});
		//add JPanels into mainPanel (card named "main")
		// mainPanel was initialized above; it holds primary controls
		mainPanel.add(headerPanel);
		mainPanel.add(inputPanel01);
		mainPanel.add(inputPanel02);
		mainPanel.add(inputPanel03);
		mainPanel.add(outputPanel);
		mainPanel.add(processPanel);
		
		//add JPanel contents
		//header panel
		headerPanel.add(Box.createVerticalStrut(5));
		headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
		header.setFont(medBold);
		header.setAlignmentX(Component.CENTER_ALIGNMENT);
		header.setHorizontalAlignment(SwingConstants.CENTER);
		headerPanel.add(header);
		//subheader panel
		version.setFont(smBold);
		version.setHorizontalAlignment(SwingConstants.CENTER);
		version.setAlignmentX(Component.CENTER_ALIGNMENT);
		headerPanel.add(Box.createVerticalStrut(5));
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
		processButton.setEnabled(false);
		processPanel.add(processButton);
		processButton.addActionListener(this);
		
		// now build the infoPanel used by menu items
		infoTitleLabel = new JLabel("", SwingConstants.CENTER);
		infoTitleLabel.setFont(smBold);
		// use JTextPane so we can display simple HTML formatting like the dialogs
		JTextPane infoPane = new JTextPane();
		infoPane.setContentType("text/html");
		infoPane.setEditable(false);
		infoPane.setBackground(UIManager.getColor("Panel.background"));
		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "main");
				setTitle("Brown Fuels Calculator");
			}
		});
		infoPanel = new JPanel(new BorderLayout());
		infoPanel.add(infoTitleLabel, BorderLayout.NORTH);
		infoPanel.add(new JScrollPane(infoPane), BorderLayout.CENTER);
		infoPanel.add(backButton, BorderLayout.SOUTH);
		// store pane reference for later updates
		infoTextArea = infoPane; // reuse field to refer to text component
		
		// add cards and set content pane
		cardPanel.add(mainPanel, "main");
		cardPanel.add(infoPanel, "info");
		setContentPane(cardPanel);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Initialization failed: " + e,
				"Error", JOptionPane.ERROR_MESSAGE);
		}
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
			showInfo("About",
				"                                               Brown Fuels Calculator\n"
				+ "                                                          Version 1.0\n\n"
				+ "The purpose of this project is to calculate weights of fine fuels and downed woody\n "
				+ "material using the inventory methods described in Brown, 1974, \"The Handbook for \n"
				+ "Inventorying Downed Woody Material\", USDA Forest Service\n"
				+ "(http://www.fs.fed.us/rm/pubs_int/int_gtr016.pdf).  It uses the equations in the\n"
				+ "handbook for a composite of species for the USDA Forest Service, Northern Region\n"
				+ "to calculate fuel weights in tons per acre with exceptions for litter and duff weights.\n"
				+ "For litter and duff weights, it uses the bulk densities given in the University of\n"
				+ "Florida, Fuel Load Calculations Cheatsheet, Dr. Leda Kobziar.");
			break;
			}//end case
	case "License":
			{
			License l = new License();
			break;
			}//end case
	case "How to Use Guide":
			{
			showInfo("How to Use Guide",
				"Transect Data:\n"
				+"Diameter Data\n");
			break;
			}
	 case "Stand Data":
			{
			showInfo("Stand Data",
				"Use the file browser to input CSV files (plain text files) with your data.\n"
				+"Stand Data should include\n");
			break;
			}
	 case "Transect Data":
			{
			showInfo("Transect Data",
				"Use the file browser to input CSV files (plain text files) with your data.\n"
				+"Transect Data should include\n");
			break;
			}
	 case "Diameter Data":
			{
			showInfo("Diameter Data",
				"Use the file browser to input CSV files (plain text files) with your data.\n"
				+"For the diameters, put at least one 0 in each row per transect \n" 
				+"for sound and rotten fuels in this category if none are tallied. \n");
			break;
			}
	 case "Output Data":
			{
			showInfo("Output Data",
				"Use the file browser to input CSV files (plain text files) with your data.\n"
				+"Output Data should include\n");
			break;
			}
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
		        			  "The selected output file already exists and will be\n"+
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

	private void showInfo(String title, String message)
	{
		// convert newlines to <br> for HTML display
		String html = "<html><body style='font-family:Arial; font-size:12pt;'>" +
			message.replace("\n", "<br>") +
			"</body></html>";
		infoTitleLabel.setText(title);
		infoTextArea.setText(html);
		cardLayout.show(cardPanel, "info");
		setTitle("Brown Fuels Calculator - " + title);
	}
}