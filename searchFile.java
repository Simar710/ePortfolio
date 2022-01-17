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
 * contains GUI and functionality for the search page
 */
public class searchFile {
    /**
	 * Constructor conating the functionality for search page
	 * @return JPanel; the search panel made 
	 */
	public JPanel linkFiles() {
		
		//Constructing Search Page
		JPanel searchPage = new JPanel();
        searchPage.setLayout(new BorderLayout());
        searchPage.setBackground(Color.WHITE);

        //setting the layout
        JPanel upSearch = new JPanel(new BorderLayout());
        upSearch.setBorder(new EmptyBorder(5, 5, 0, 0));
        JPanel leftSearch = new JPanel(new GridLayout(4,2));
        leftSearch.setBorder(new EmptyBorder(10, 10, 0, 0));
        JPanel rightSearch = new JPanel();
        rightSearch.setBorder(new EmptyBorder(30, 100, 0, 0));
        rightSearch.setLayout(new BoxLayout(rightSearch, BoxLayout.Y_AXIS));
        JPanel downSearch = new JPanel();
        downSearch.setLayout(new BorderLayout());
        downSearch.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel searchInvest = new JLabel("Searching Investment");
        upSearch.add(searchInvest, BorderLayout.NORTH);
	    JButton reset = new JButton("Reset");
	    JTextArea searchBox = new JTextArea(5,10);
        JScrollPane scroller = new JScrollPane(searchBox);
        scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); //addnig horizontal srrollbar
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //adding vertical scrollbar
        JLabel symbol = new JLabel ("Symbol");
        JLabel nameKey = new JLabel("<html>Name<br>keywords</br></html>");
        JLabel hPrice = new JLabel("High Price");
        JLabel lPrice = new JLabel("Low Price");
        JLabel searchResult = new JLabel("Search results");
        JTextField symbolField = new JTextField(10);
        JTextField nameKeyField = new JTextField(10);
        JTextField hPriceField = new JTextField(10);
        JTextField lPriceField = new JTextField(10);
        JButton searchButton = new JButton("Search");
        /**
         * Action Listner for reset button
         */
        reset.addActionListener((ActionEvent e) ->{
        	symbolField.setText(null);
        	nameKeyField.setText(null);
        	hPriceField.setText(null);
        	lPriceField.setText(null);
        	searchBox.setText(null);
        });
        
        /**
         * Action listener for search button
         */
        searchButton.addActionListener((ActionEvent e) ->{

        	String[] textArray = new String[4];
        	textArray[0] = symbolField.getText();
        	textArray[1] = nameKeyField.getText();
        	textArray[2] = hPriceField.getText();
        	textArray[3] = lPriceField.getText();
            searchBox.setText("");
        	//callnig serch function from portfoliofile
        	Portfolio.searcher(textArray[0],textArray[1],textArray[2],textArray[3],searchBox);
        });
        //adding tpo panels
        leftSearch.add(symbol);
        leftSearch.add(symbolField);

        leftSearch.add(nameKey);
        leftSearch.add(nameKeyField);

        leftSearch.add(lPrice);
        leftSearch.add(lPriceField);

        leftSearch.add(hPrice);
        leftSearch.add(hPriceField);

        rightSearch.add(reset);
        rightSearch.add(searchButton);
        
        downSearch.add(searchResult, BorderLayout.NORTH);
        downSearch.add(scroller, BorderLayout.CENTER);
        //adding panels to the main panel
        upSearch.add(leftSearch, BorderLayout.WEST);
        upSearch.add(rightSearch, BorderLayout.CENTER);
        searchPage.add(upSearch, BorderLayout.NORTH);
        searchPage.add(downSearch, BorderLayout.CENTER);
		return searchPage;  //retrn the search panel made
	}
}
