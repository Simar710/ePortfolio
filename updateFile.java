package ePortfolio;
//import the needed libraries/modules
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
/**
 * Class to update the price
 */
public class updateFile {
    Portfolio obj =  new Portfolio();   //object of portfolio class is made
    JTextField symbolField;
    JTextField nameField;
    ArrayList<Investment> data; 
    Investment investObj;
    JTextArea messageBox = new JTextArea(10,20);    
    int elemenNow = 0;
    
    JButton next = new JButton("Next");
    JButton prev = new JButton("Prev");
	public JPanel linkFiles(){
		
		//Construction of Update Page
		JPanel updatePage = new JPanel();
        updatePage.setLayout(new BorderLayout());
        updatePage.setBackground(Color.WHITE);
        data = obj.storeList();
        //set the layout of the update page
        JPanel upUpdate = new JPanel(new BorderLayout());
        upUpdate.setBorder(new EmptyBorder(5, 5, 0, 0));
        JPanel leftUpdate = new JPanel(new GridLayout(3,2));
        leftUpdate.setBorder(new EmptyBorder(10, 10, 0, 0));
        JPanel rightUpdate = new JPanel();
        rightUpdate.setBorder(new EmptyBorder(30, 100, 0, 0));
        rightUpdate.setLayout(new BoxLayout(rightUpdate, BoxLayout.Y_AXIS));
        JPanel downUpdate = new JPanel();
        downUpdate.setLayout(new BorderLayout());
        downUpdate.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        symbolField = new JTextField(10);
        nameField = new JTextField(10);
        symbolField.setEditable(false);
        nameField.setEditable(false);

        if (data.size() > 0) {
            //if there are some investments present then start with first elemeent
            investObj = data.get(0);
        }

        JLabel updateInvest = new JLabel("Updateing an Investment");
	    JLabel symbolInvest = new JLabel("Symbol");
	    JLabel nameInvest = new JLabel("Name");
	    JLabel priceInvest = new JLabel("Price");
        JTextField priceField = new JTextField(10);
	    
        JScrollPane scroller = new JScrollPane(messageBox);
        scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); //set the horizontal scroll bar
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //set the vertical scroll bar
        JLabel message = new JLabel("Messages");
        upUpdate.add(updateInvest, BorderLayout.NORTH);
              
        
        /**
         * //Acton listener for the previous button
         */
        prev.addActionListener((ActionEvent e)->{
            if(elemenNow <= 0){                               
                prev.setEnabled(false); // if only 1 investment is present, disable the prev button
            }
            else{
                next.setEnabled(true);
                prev.setEnabled(true);
                prevObj();    
            }

        });
        
        /**
         * //Acton listener for the next button
         */
        next.addActionListener((ActionEvent e)->{
            if(elemenNow >= (data.size())-1){     // if only last investment is accessed, disable the prev button
                next.setEnabled(false);
            }
            else{
                next.setEnabled(true);
                prev.setEnabled(true);
                nextObj();
            }
        });
       	JButton save = new JButton("Save");
        
        /**
         * //Action listener for save button
         */
        save.addActionListener((ActionEvent e)->{
            String upPrc = priceField.getText();
            if(upPrc.isBlank()){
                messageBox.setText("ERROR: The price is mandatory to save and update the price");
                return;
            }
            try { 
                Double.valueOf(upPrc); 
            } catch(NumberFormatException a) { 
                messageBox.setText("ERROR: Price should be of double type"); 
            }
            obj.updateVal(upPrc,elemenNow);
            String printObj = obj.printUpdate(elemenNow);
            messageBox.setText(printObj);            
        });
        //components to repective panels
        leftUpdate.add(symbolInvest);
        leftUpdate.add(symbolField);

        leftUpdate.add(nameInvest);
        leftUpdate.add(nameField);
        
        leftUpdate.add(priceInvest);
        leftUpdate.add(priceField);

       	rightUpdate.add(prev);
        rightUpdate.add(next);
        rightUpdate.add(save);

        downUpdate.add(message, BorderLayout.NORTH);
        downUpdate.add(scroller, BorderLayout.CENTER);

        upUpdate.add(leftUpdate, BorderLayout.WEST);
        upUpdate.add(rightUpdate, BorderLayout.CENTER);
        //add to main page
        updatePage.add(upUpdate, BorderLayout.NORTH);
        updatePage.add(downUpdate, BorderLayout.CENTER);
        
        return updatePage;
	}
    
    /**
     * function to access the object
     */
    private void nextObj() {
        data=obj.storeList();
        elemenNow++;    //increament on clickin next
        investObj = data.get(elemenNow);
        symbolField.setText(investObj.getSymbol());
        nameField.setText(investObj.getName());
    }
    
    /**
     * function to access the previous object
     */
    private void prevObj() {
        data=obj.storeList();
        elemenNow--;    //decrement on clicking prev
        investObj = data.get(elemenNow);
        symbolField.setText(investObj.getSymbol());    
        nameField.setText(investObj.getName());
    }
}
