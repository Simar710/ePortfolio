package ePortfolio;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * Class for GUI of gain panel
 */
public class gainFile {
	
	static JTextArea gainBox = new JTextArea(5,10);
	static JTextField gainField = new JTextField(10);
	/**
	 * Constructor conating the functionality for gain page
	 * @return JPanel; the sell gain made 
	 */
	public JPanel linkFiles() {
		//Constructing getGain Page
		JPanel gainPage = new JPanel();
        gainPage.setLayout(new BorderLayout());
        gainPage.setBackground(Color.WHITE);
        //set the layout for the search page
        JPanel upGain = new JPanel(new BorderLayout());
        upGain.setBorder(new EmptyBorder(5, 5, 0, 0));
        JPanel leftGain = new JPanel();
        leftGain.setBorder(new EmptyBorder(10, 10, 0, 0));
        JPanel downGain = new JPanel();
        downGain.setLayout(new BorderLayout());
        downGain.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel gainInvest = new JLabel("Getting Total Gain");
        JScrollPane scroller = new JScrollPane(gainBox);
        //add horizontal and vertical scrollbars
        scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //add components to the respective panels
        upGain.add(gainInvest, BorderLayout.NORTH);
        
        JLabel totalGain = new JLabel("Total gain");
        leftGain.add(totalGain);
        leftGain.add(gainField);
        
        JLabel singles = new JLabel("Individual gains");

        downGain.add(singles, BorderLayout.NORTH);
        downGain.add(scroller, BorderLayout.CENTER); 
        //adding panels to the main panel
        upGain.add(leftGain, BorderLayout.WEST);
        gainPage.add(upGain, BorderLayout.NORTH);
        gainPage.add(downGain, BorderLayout.CENTER);
		return gainPage; //return the gain panel
	}
}
