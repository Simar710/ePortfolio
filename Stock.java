package ePortfolio;

/**
 *Stock class is reponsible for every mutualfund related actions
 */
public class Stock extends Investment{

/**
 * This function is a constructor which calls the constructor of base class
 * @param tempSymbol String, it holds the value of symbol
 * @param tempName String, it holds the value of name
 * @param tempQuantity double,  it holds the value of quantity
 * @param tempPrice double, it holds the value of price
 * @throws Exception
 */
    Stock(String tempSymbol, String tempName, double tempQuantity, double tempPrice) throws Exception {
        super(tempSymbol, tempName, tempQuantity, tempPrice, 9.99, 9.99, "stock");
    }
    
}