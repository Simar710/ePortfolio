package ePortfolio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * class implementing the GUI for sell page
 */
public class sellFile {
	/**
	 * Constructor conating the functionality for sell page
	 * @return JPanel; the sell panel made 
	 */
	public JPanel linkFiles() {
		//Construction of Sell Page
		JPanel sellPage = new JPanel();
	    sellPage.setLayout(new BorderLayout());
	    sellPage.setBackground(Color.WHITE);
		//setting the layout
	    JPanel upSell = new JPanel(new BorderLayout());
	    upSell.setBorder(new EmptyBorder(5, 5, 0, 0));
	    JPanel leftSell = new JPanel(new GridLayout(3,2));
	    leftSell.setBorder(new EmptyBorder(10, 10, 0, 0));
	    JPanel rightSell = new JPanel();
	    rightSell.setBorder(new EmptyBorder(30, 100, 0, 0));
	    rightSell.setLayout(new BoxLayout(rightSell, BoxLayout.Y_AXIS));
	    JPanel downSell = new JPanel();
	    downSell.setLayout(new BorderLayout());
	    downSell.setBorder(new EmptyBorder(10, 10, 10, 10));

	    JLabel sellInvest = new JLabel("Selling an Investment");
	    JLabel symbolInvest = new JLabel("Symbol");
	    JLabel quantityInvest = new JLabel("Quantity");
	    JLabel priceInvest = new JLabel("Price");
	    JTextField symbolField = new JTextField(10);
        JTextField quantityField = new JTextField(10);
        JTextField priceField = new JTextField(10);
	    upSell.add(sellInvest, BorderLayout.NORTH); //adding labl to the panel
	    JButton reset = new JButton("Reset");
	    JTextArea sellBox = new JTextArea(5,10);
        JScrollPane scroller = new JScrollPane(sellBox);
        scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); //adding horizontal scrolll bar
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);	//adding vertical scrooll bar
        JLabel message = new JLabel("Messages");
        
	    JButton sellButton = new JButton("Sell");
	    /**
		 * Action lisener for reset button
		 */
	    reset.addActionListener((ActionEvent e) ->{
        	symbolField.setText(null);
        	quantityField.setText(null);
        	priceField.setText(null);
        	sellBox.setText(null);
        	
        });
        /**
		 * Action listener for selll button
		 */
        sellButton.addActionListener((ActionEvent e) ->{
        	String[] textArray = new String[3];
        	textArray[0] = symbolField.getText();
        	textArray[1] = quantityField.getText();
        	textArray[2] = priceField.getText();
        	
			//every exception occur while selling an investment is handled here
			try {
				String pay = Portfolio.paymentCal(textArray[0], textArray[2], textArray[1]);
				sellBox.setText(pay);
			} catch (Exception ex) {
				sellBox.setText(ex.getMessage());		
				ex.printStackTrace();
			} 
        });

		//adding the compaonents to the panels
	    leftSell.add(symbolInvest);
	    leftSell.add(symbolField);

	    leftSell.add(quantityInvest);
	    leftSell.add(quantityField);
	    
	    leftSell.add(priceInvest);
	    leftSell.add(priceField);
	    
	    rightSell.add(reset);
	    rightSell.add(sellButton);

	    downSell.add(message, BorderLayout.NORTH);
	    downSell.add(scroller, BorderLayout.CENTER);
		//adding the panels to the main panel
	    upSell.add(leftSell, BorderLayout.WEST);
	    upSell.add(rightSell, BorderLayout.CENTER);
	    sellPage.add(upSell, BorderLayout.NORTH);
	    sellPage.add(downSell, BorderLayout.CENTER);
	    
	    return sellPage;	//return the sell panel
	}

}
