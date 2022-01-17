package ePortfolio;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JTextArea;
/**
 * Portfolio class contains every helper and important functions needed to run program
 */
public class Portfolio {
    
    static ArrayList<Investment> data = new ArrayList<Investment>();    //arraylist declared
    static HashMap<String, ArrayList<Integer>> keywords = new HashMap<>();  //hashmap declared
    /**
     * to check if symbol exist of particular type
     * @param symbol String,  holds the symbol
     * @param type String, holds the type of investment
     * @return int, returns 1 if symbol and type found otherwise -1
     */
    public static int founder(String symbol, String type){

        for(Investment list: data){
            if (list.getSymbol().equals(symbol) && list.getType().equals(type)){   
                return 1;       //return  1 if symbol exist
            }
        }

        return -1;
    }
   /**
    * to find the index of symbol if exist
    * @param symbol, String, stores the symbol
    * @return int, rteurns index if symbol found, otherwise -1
    */
    public static int indexFounder(String symbol){
 
        int i = 0;
        for(Investment list: data){ //iterate to find index of symbol in the investment list
            if (list.getSymbol().equals(symbol)){   
                return i;   //return the index
            }
            i++;
        }

        return -1;
    }
    
    /**
     * add the investment that the user wants to add to current investments
     * @param symbol String, stores the symbol 
     * @param type String, stores which type of investment
     * @param quantity double, stores the quantity
     * @param price double, stores the price
     
     */
    public static void addInvestment (String symbol, String type, double quantity, double price){
        
        for(Investment list: data)
        {
            if (list.getType().equals(type)){
                if (list.getSymbol().equals(symbol)){   
                    list.addValue(quantity, price);     //added when found
                    System.out.println(list.toString());
                }
            } 
        }
    }
    /**
     * add the new investment 
     * @param symbol String, stores the symbol 
     * @param type String, stores which type of investment
     * @param quantity double, stores the quantity
     * @param price double, stores the price
     * @param name String, stores the name
     * @return String, object added recently
     * @throws Exception handles the various exceptions from investment class constructor
     */
    public static String addInvestment (String symbol, String type, double quantity, double price, String name) throws Exception{
        
        if (type.equals("stock")){
            //add  newinvestment to list
            Stock stockList;
            stockList = new Stock(symbol, name, quantity, price);
            data.add(stockList);
            hashSearch();
            return data.get(data.size() - 1).toString();
        }
        else if (type.equals("mutual fund")){
            MutualFund fundList = new MutualFund(symbol, name, quantity, price);        
            data.add(fundList);
            hashSearch();
            return data.get(data.size() - 1).toString();
        }
        return "-2";
    }

    /**
     * calculates the payment after selling
     * @param symbol String, stores the symbol
     * @param tempPrice String, stores the price
     * @param tempQuantity String, stores the quantity
     * @return String; payment calculated and investment sold
     * @throws Exception exceptions regarding selling the investments are made
     */
    public static String paymentCal (String symbol, String tempPrice, String tempQuantity)throws Exception{

        if (!tempPrice.isBlank() && !tempQuantity.isBlank() && !symbol.isBlank()){
    
            double price = Double.valueOf(tempPrice);
            double quantity = Double.valueOf(tempQuantity);
            int result = indexFounder(symbol);
            
            if (result == -1)   //if investment doesn't exist
            {
                throw new Exception("ERROR: Investment doesn't exist");
            }
            
            else if (data.get(result).getQuantity() < quantity) //when not enough quantity in stock
            {
                throw new Exception("Doesn't have enough quantity in stock. Try less quantity");
            }

            else if (data.get(result).getQuantity() == quantity)    //when full stock is sold
            {
                String pay = String.valueOf(data.get(result).subValue(quantity, price));
                data.remove(result);
                hashSearch();   //modify hashMap
                return "Payment = "+pay + " and Investment is removed as it is sold completely"; //return the payment calculated
            }

            String pay = String.valueOf(data.get(result).subValue(quantity, price)) + "\n" +data.get(result).toString();
            return " Payment = " + pay;
        }
        else{
            throw new NullPointerException("ERROR symbol, price, quantity can't be empty");
        }
    }
    
    /**
     * Updates the prices of all prices
     * @param elemenNow int; current element index whose price is to be updated
     * @param upPrc String; updated price
     */
    public void updateVal(String upPrc, int elemenNow)
    {
        double upPrcDouble = Double.valueOf(upPrc);
        Investment list = data.get(elemenNow);
        double value = (upPrcDouble * list.getQuantity()) + 9.99;  //bookvalue is calculated
        list.setBookValue(value);
        list.pricer(upPrcDouble);
    }
    
    /**
     * helps in calculating the total and individual gain
     * @param singleGains JTextArea; text area where indivual gains will be displayed
     * @return double; total gain
     */
    public static double gain(JTextArea singleGains)
    {
        double gaining = 0;

        for(Investment list: data)
        {
            gaining += list.getGain();      //add all the gain
            String stringGain = String.valueOf(list.getGain());
            String resultedString = "Gain of " + list.getSymbol() + " = " + stringGain + "\n";
            singleGains.append(resultedString); //display the gains of individual gains

        }
        return gaining;
    }
    
    /**
     * Implements HashMap functionality for searching the list
     */
    public static void hashSearch(){
        keywords = new HashMap<String, ArrayList<Integer>>();
        String[] ch;
        for(int i=0;i< data.size();i++){
            ch = (data.get(i).getName().toLowerCase()).split("[ ]+",0);      //split and store the keywords from name
            for(int j=0; j< ch.length; j++){
                if(keywords.get(ch[j])==null) 
                {
                    keywords.put(ch[j],new ArrayList<Integer>());
                }
                keywords.get(ch[j]).add(i);
            }
        } 
    }
    /**
     * This functions is  used to access the latest investment 
     * @return String; latest investment
     */
    public static String latestStock() {
        return data.get(data.size() - 1).toString();
    }
    /**
     * It is used to get ArrayList where investments are stored
     * @return ArrayList;  ArrayList where investments are stored
     */
    public ArrayList<Investment> storeList(){
        return data;
    }


    /**
     * Perform search in the list of investments present
     * @param symbol String; symbol of investment
     * @param name String; name of the investment
     * @param low String; lower value of the price range
     * @param high String; upper value of the price range
     * @param searchBox JTextArea; where all the search result will be dislayed
     */
    public static void searcher(String symbol, String name, String low, String high, JTextArea searchBox) {
        name = name.toLowerCase();
        // declaring our result ArrayList
        ArrayList<String> listing  = new ArrayList<String>();
        ArrayList<Integer> legend = new ArrayList<Integer>();

        String []hisa = name.split("[ ]+"); //split and store the name
        //search using hashmap
        for (int i = 0; i < hisa.length; i++){
            if (i == 0){
                legend = keywords.get(hisa[i]);
            }
            else{
                legend.retainAll(keywords.get(hisa[i]));
            }
        }

        if(legend != null){
            for(int counter: legend){
                if (data.get(counter).searchHelp(symbol, name, low,high)){
                    searchBox.append(data.get(counter).toString());     //display the search results
                }
            }
        }
        else{
             // investment list iteration
            for (int i = 0; i < data.size(); i++) {
                // add to the arraylist if match found
                if (data.get(i).searchHelp(symbol, name, low,high)) {
                    listing.add(data.get(i).toString());
                }
            }

            //print if match found
            for (int i = 0; i < listing.size(); i++) {
                searchBox.append(listing.get(i));
            }

            //if no match found
            if (listing.size() == 0) {
                searchBox.setText("No Match found!");
            }
        }
    }
    /**
     * to get the element whose price is updated, which will be printed in GUI
     * @param elemenNow int; investment index which is currently accesssed
     * @return String; the investent object
     */
    public String printUpdate(int elemenNow) {
        Investment list = data.get(elemenNow);
        return list.toString();
        
    }
}

