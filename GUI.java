package ePortfolio;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * This is the class that implements the GUI
 */
public class GUI extends JFrame{
	//declare private variables to be used in the class
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu = new JMenu("Commands");
	private JPanel Intro = new JPanel();
	private JPanel buyPage = new JPanel();
	private JPanel sellPage = new JPanel();
	private JPanel updatePage = new JPanel();
	private JPanel gainPage = new JPanel();
	private JPanel searchPage = new JPanel();
	private JMenuItem buyItem;
	private JMenuItem sellItem;
	private JMenuItem updateItem;
	private JMenuItem gainItem;
	private JMenuItem searchItem;
	private JMenuItem quitItem;
	
	/**
	 * Constructor which is responsible of setting the visibility of different pages
	 * along with some other functionality
	 */
	public GUI() {
	
		super();
		setSize(800, 500);	//set the size of window 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//setting the close operation
		setResizable(false);//not allowing to resize the window
		setTitle ("ePortfolio"); //set title of window // frame
        getContentPane().setBackground(Color.WHITE);//set the color of frame
        setJMenuBar(menuBar);//add the menubar to jframe
        menuBar.setBackground(Color.LIGHT_GRAY);//set the color of menubar
        menuBar.add(menu);//add menu list to menubar

		Portfolio obj = new Portfolio();

		//declare the menuitems
        buyItem = new JMenuItem("Buy");
        sellItem = new JMenuItem("Sell");
        updateItem = new JMenuItem("Update");
        gainItem = new JMenuItem("GetGain");
        searchItem = new JMenuItem("Search");
        quitItem = new JMenuItem("Quit");
        
		//add menuotems to menu
        menu.add(buyItem);
        menu.add(sellItem);
        menu.add(updateItem);
        menu.add(gainItem);
        menu.add(searchItem);
        menu.add(quitItem);
        
        //First Page Construction
        GridLayout layout = new GridLayout(2, 1);
        Intro.setLayout(layout);//set layout of first page to grid layout
        Intro.setBackground(Color.WHITE);
        JLabel introOne = new JLabel("Welcome to ePortfolio");
        JTextArea introTwo = new JTextArea("Choose a command from the \"Commands\" menu to buy or sell an investment, "
        		+ "update prices for all investments, get gain for the portfolio, search for releveant investments, or quit the program.");
		introTwo.setEditable(false);
        introTwo.setLineWrap(true);//allow to move the text to new line if it doesn't fit in frame
        Intro.setBorder(new EmptyBorder(100, 30, 100, 29));	//just to add padding
        Intro.add(introOne);//add label to intro page
        Intro.add(introTwo);//add non-editable textarea to firstpage

		//linking of Files
        buyPage = new buyFile().linkFiles();
        sellPage = new sellFile().linkFiles();
        updateFile update = new updateFile();
        updatePage = update.linkFiles();
        gainPage = (new gainFile()).linkFiles();
        searchPage = new searchFile().linkFiles();
	/**
	 * Action Listner for buy Page
	 */
	    buyItem.addActionListener((ActionEvent e)->{
	    	this.add(buyPage);
		    buyPage.setVisible(true);
		    Intro.setVisible(false);
		    sellPage.setVisible(false);
		    updatePage.setVisible(false);
		    gainPage.setVisible(false);
		    searchPage.setVisible(false);
	    });
	
	/**
	 * Action Listner for sell Page
	 */
	    sellItem.addActionListener((ActionEvent e)->{
	    	this.add(sellPage);
		    sellPage.setVisible(true);
		    Intro.setVisible(false);
		    buyPage.setVisible(false);
		    updatePage.setVisible(false);
		    gainPage.setVisible(false);
		    searchPage.setVisible(false);
	    });
	
	/**
	 * Action Listner for update Page
	 */
	    updateItem.addActionListener((ActionEvent e)->{
	    	this.add(updatePage);
			sellPage.setVisible(false);
		    Intro.setVisible(false);
		    buyPage.setVisible(false);

            ArrayList<Investment> data = obj.storeList(); 
            update.symbolField.setText(data.get(0).getSymbol());
            update.nameField.setText(data.get(0).getName());
			update.next.setEnabled(true);
			update.prev.setEnabled(true);
			
		    updatePage.setVisible(true);
		    gainPage.setVisible(false);
		    searchPage.setVisible(false);
	    });
	
	/**
	 * Action Listner for gain Page
	 */
	    gainItem.addActionListener((ActionEvent e)->{
	    	this.add(gainPage);
		    sellPage.setVisible(false);
		    Intro.setVisible(false);
		    buyPage.setVisible(false);
		    updatePage.setVisible(false);
		    gainPage.setVisible(true);
		    searchPage.setVisible(false);
			gainFile.gainBox.setText(null);
			double calGain = Portfolio.gain(gainFile.gainBox);
			String stringGain = String.valueOf(calGain);
			gainFile.gainField.setText(stringGain);
	    });
	
	/**
	 * Action Listner for search Page
	 */
	    searchItem.addActionListener((ActionEvent e)->{
	    	this.add(searchPage);
		    sellPage.setVisible(false);
		    Intro.setVisible(false);
		    buyPage.setVisible(false);
		    updatePage.setVisible(false);
		    gainPage.setVisible(false);
		    searchPage.setVisible(true);
	    });	
	    
		//adding Intro page to frame
	    this.add(Intro);
	    Intro.setVisible(true);

	    quitItem.addActionListener(new quitFile());
	}
	
	/**
	 * function to quit the program
	 */
	private class quitFile implements ActionListener{
		public void  actionPerformed (ActionEvent e) {
			System.exit(0);
		}
	}
	/**
	 * main function to call the GUI
	 * @param args command line argument
	 */
    public static void main(String[] args)
    {
        GUI gui = new GUI();
        gui.setVisible(true);
    }
}
