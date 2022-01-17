package ePortfolio;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

/**
 * GUI construction for buy page
 */
public class buyFile {
    /**
	 * Constructor conating the functionality for buy page
	 * @return JPanel; the buy panel made 
	 */
	public JPanel linkFiles() {
		//buy Investment Panel construction
		JPanel buyPage = new JPanel ();
		buyPage.setLayout(new BorderLayout());
        buyPage.setBackground(Color.WHITE);
        //setting the layout of the page
        JPanel up = new JPanel(new BorderLayout());
        up.setBorder(new EmptyBorder(5, 5, 0, 0));
        JPanel left = new JPanel(new GridLayout(5,2));
        left.setBorder(new EmptyBorder(10, 10, 0, 0));
        JPanel right = new JPanel();
        right.setBorder(new EmptyBorder(30, 100, 0, 0));
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        JPanel down = new JPanel();
        down.setLayout(new BorderLayout());
        down.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel buyInvest = new JLabel("Buying an Investment");
        JLabel typeInvest = new JLabel("Type");
        JLabel symbolInvest = new JLabel("Symbol");
        JLabel nameInvest = new JLabel("Name");
        JLabel quantityInvest = new JLabel("Quantity");
        JLabel priceInvest = new JLabel("Price");
        
        up.add(buyInvest, BorderLayout.NORTH);
        //adding the combo box
        String[] typ = {"stock", "mutualfund"};
        JComboBox<String> typeField = new JComboBox<>(typ);
        typeField.setSelectedIndex(0);//setting the combo box to stock as default
        JTextField symbolField = new JTextField(10);
        JTextField nameField = new JTextField(10);
        JTextField quantityField = new JTextField(10);
        JTextField priceField = new JTextField(10);
        
        JTextArea messageBox = new JTextArea(5,10);
        JScrollPane scroller = new JScrollPane(messageBox);
        scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);//adding horizontal scrollbar
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//adding vertical scrollbar
        JLabel message = new JLabel("Messages");
        JButton reset = new JButton("Reset");
        JButton buyButton = new JButton("Buy");
        
        /**
         * adding action listner for rs=eset button
         */
        reset.addActionListener((ActionEvent e) ->{
        	typeField.setSelectedIndex(0);
        	symbolField.setText(null);
        	nameField.setText(null);
        	quantityField.setText(null);
        	priceField.setText(null);
        	messageBox.setText(null);
        });
        //adding action listener for buy button
        buyButton.addActionListener((ActionEvent e) ->{

        	String[] textArray = new String[5];
        	textArray[0] = typeField.getSelectedItem().toString();
        	textArray[1] = symbolField.getText();
        	textArray[2] = nameField.getText();
        	textArray[3] = quantityField.getText();
        	textArray[4] = priceField.getText();
        	messageBox.setText(null);//empty the message box
            int result = -3;
            if (textArray[0].equals("stock")){
                //if same symbol exist in other type
                result = Portfolio.founder(textArray[1], "mutual fund");
            }
            else if (textArray[0].equals("mutual fund")){
                //if same symbol exist in other type
                result = Portfolio.founder(textArray[1], "stock");
            }
            //if symbol already exists
            if(result == 1){
            	messageBox.setText("Symbol Occupied. Try Something else");
                return;
            }
            //Reference: (line 93 - 102) saw something like this on stackOverflow. But can't remember the source
            try {                                  
                Double.parseDouble(textArray[3]); 
            } catch(NumberFormatException ex) { 
                messageBox.setText("ERROR: Data type of quantity should be double"); 
            }
            try {                                
                Double.parseDouble(textArray[4]); 
            } catch(NumberFormatException ex) { 
                messageBox.setText("ERROR: Data type of price should be double"); 
            }

            //converting string to double
            double tempQuantity = Double.valueOf(textArray[3]);
            double tempPrice = Double.valueOf(textArray[4]);
            
            int fullList = Portfolio.indexFounder(textArray[1]);
            //error displayed if price or quantity fields are empty
            if(textArray[3].isBlank())
            {  messageBox.setText("ERROR: Quantity field is mandatory!");
                return;
            }
            if(textArray[4].isBlank())
            {  messageBox.setText("ERROR: Price field is mandatory!");
                return;
            }
            
            if (fullList == -1)
            {
            	try
            	{
                    //adding the new investment
            		Portfolio.addInvestment(textArray[1], textArray[0], tempQuantity, tempPrice, textArray[2]);
            	}
            	catch(Exception ex)
            	{
                    //handle the exceptiion
            		messageBox.setText(ex.getMessage());
            		ex.printStackTrace();
            		return;
            	}
            }
            else{
                Portfolio.addInvestment(textArray[1], textArray[0], tempQuantity, tempPrice);   //if old investment
            } 
            //display the recently bought investment in message box
            String display = Portfolio.latestStock();
            messageBox.setText(display);
        });
        //add to panels
        left.add(typeInvest);
        left.add(typeField);
        
        left.add(symbolInvest);
        left.add(symbolField);
        
        left.add(nameInvest);
        left.add(nameField);

        left.add(quantityInvest);
        left.add(quantityField);
        
        left.add(priceInvest);
        left.add(priceField);
        
        right.add(reset);
        right.add(buyButton);
        
        down.add(message, BorderLayout.NORTH);
        down.add(scroller, BorderLayout.CENTER);
        //add panels to the main panel
        up.add(left, BorderLayout.WEST);
        up.add(right, BorderLayout.CENTER);
        buyPage.add(up, BorderLayout.NORTH);
        buyPage.add(down, BorderLayout.CENTER);
        
        return buyPage;//return the main buy panel
	}
}
