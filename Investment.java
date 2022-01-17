package ePortfolio;

/**
 * Investment class is the super class which will all super constructors and other methods used by both subclasses
 */
public abstract class Investment
{
    private String symbol;
    private String name;
    private double quantity ;
    private double price ;
    private double bookValue = 0.0; 
    private double comOnBuy;
    private double comOnSell;
    private String type;
    private double getval;

    /**
     * constructor of class Investment
     * @param tempSymbol String, stores the symbol
     * @param tempName String, stores the name
     * @param tempQuantity, double stores the quantity
     * @param tempPrice double, stores the price
     * @param comOnBuy double, stores commission on buying
     * @param comOnSell double, stores commission on selling
     * @param type String, stores the type of investment
     * @throws Exception various excetion regarding the inputs are handled
     */
    Investment(String tempSymbol, String tempName, double tempQuantity, double tempPrice, double comOnBuy, double comOnSell, String type) throws Exception
    {
        //if symbol is not empty
        if(!tempSymbol.isBlank()){
            this.symbol = tempSymbol;
        }
        else{
            throw new Exception("ERROR: Symbol is mandatory");
        }
        //if symbol contains spaces
        if(!tempSymbol.contains(" ")){
            this.symbol = tempSymbol;
        }
        else{
            throw new Exception("ERROR: It is wrong to have spaces in symbol");
        }
        //if name is not empty
        if(!tempName.isBlank()){
            this.name = tempName;
        }
        else{
            throw new Exception("ERROR: It is mandatory to write name");
        }
        //if quantity is positive
        if(tempQuantity >= 0){
            this.quantity = tempQuantity;
        }
        else{
            throw new Exception("ERROR: Quantity should be positive");
        }
           //if price is positive   
        if(tempPrice >= 0){
            this.price = tempPrice;
        } 
        else{
            throw new Exception("ERROR: Price should be positive");
        }

        this.comOnBuy = comOnBuy;
        this.comOnSell = comOnSell;
        this.bookValue = (tempPrice * tempQuantity) + comOnBuy;;
        this.type = type;
        up();
    }
 
    /**
     * return total gain
     * @return double, return total gain 
     */
    public double getGain(){
    	
        return this.getval;
    }
    /**
     * returns the type of investment
     * @return String, returns the type of investment
     */
    public String getType()
    {
        return this.type;
    }

    /**
    * getter function to get symbol
    * @return String;  returns symbol
    */
    public String getSymbol()
    {
        return this.symbol;
    }

    /**
    * getter function to get symbol
    * @return double;  returns symbol
    */
    public double getQuantity()
    {
        return this.quantity;
    }
    
    /**
     * sets book value
     * @param value double, bookvalue
     */
    public void setBookValue(double value)
    {
        this.bookValue = value;
    }
    /**
     * Setter function to set the value of leftover after selling
     * @param quantity quantity of new investment
     * @param price price of new investment
     */
    public void addValue(double quantity, double price){
        this.quantity += quantity;
        this.price += price;
        this.bookValue += (price * quantity) + comOnBuy;        //calculate bookvalue
        up();
    }
    /**
     * does modifications after sell function and calculates payment
     * @param quantity double, stores the quantity
     * @param price double, stores the price
     * @return double, returns the payment calculated
     */
    public double subValue(double quantity, double price)
    {
        this.bookValue = this.bookValue * ((this.quantity - quantity) / this.quantity);     //calculate and assigne the bookvalue
        this.price = price;

        double pay = price * quantity - comOnSell;      //calculate payment
        this.quantity -= quantity;
        up();
        return pay;
        
    }
    
    /**
     * calculates gain every time it is called
     */
    private void up () {
        this.getval = ((this.price * this.quantity - comOnSell) - this.bookValue);  //formula to calculate getGain
    }
    
    /**
     * updates price and calls up()
     * @param price double, stores the price
     */
    public void pricer(double price)
    {
        this.price = price;
        up();
    }
    /**
    * getter function to get bookvalue
    * @return double;  returns bookvalue
    */

    public double getBookvalue()
    {
        return this.bookValue;
    }
    /**
    * getter function to get price
    * @return double;  returns price
    */
    public double getPrice()
    {
        return this.price;
    }
    /**
    * getter function to get name
    * @return String;  returns name of stock
    */
    public String getName()
    {
        return this.name;
    }

    /**
    * prints the output
    * @return String
    */
    @Override
    public String toString()
    {
        return "type = " + '"' + type + '"' +
        "\nsymbol =  " + '"' + this.symbol + '"' +
        "\nname: " + '"' + this.name + '"' +
        "\nquantity: " + '"' + this.quantity + '"' +
        "\nprice: " + '"' + this.price + '"' +
        "\nbookValue:" + '"' + this.bookValue + '"' + "\n\n" ;
    }

    /**
     * used to comapre and find match according to the input
     * 
     * @param symbol     String; the symbol of the investment 
     * @param name     String, the name of the investment 
     * @param low String, the lower value of range of price
     * @param high String; the higher value of range of price
     * @return boolean; true if match found
     */
    public boolean searchHelp(String symbol, String name, String low,String high) {

        boolean parameter = false;

        //when the name is given
        if (name != null && !name.isEmpty()) {
            //store after spliting the name
            String keywords[] = name.split("( )+");

            for (int i = 0; i < keywords.length; i++) {
                // if match not foundd in name
                if (!this.name.toLowerCase().contains(keywords[i].toLowerCase())) {
                    return false;
                }
            }
            parameter = true;
        }

        double minrange = -1;
        double maxrange = Double.MAX_VALUE;
        //parsing the upper and lower ranges the price ranges
        if(low != null && !low.isEmpty()){
            minrange = Double.parseDouble(low);
        }
        if(high != null && !high.isEmpty()){
            maxrange = Double.parseDouble(high);

        }

        // check if our price is outside the maxrange and minrange bounds
        if (this.price < minrange || this.price > maxrange) {
            return false;
        }
        parameter = true;
    
        // when symbol was entered
        if (symbol != null && !symbol.isEmpty()) {
            if (!this.symbol.equalsIgnoreCase(symbol)) {
                return false;
            }
            parameter = true;
        }

        if (parameter) {
            return true;    //if match found and every operation is success
        } else {
            return false;
        }
    }
    

}