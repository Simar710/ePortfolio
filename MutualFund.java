package ePortfolio;

/**
 * MutualFund class is reponsible for every mutualfund related actions
 */
public class MutualFund extends Investment{
    
   /**
 * This function is a constructor which calls the constructor of base class
 * @param tempSymbol String, it holds the value of symbol
 * @param tempName String, it holds the value of name
 * @param tempQuantity double,  it holds the value of quantity
 * @param tempPrice double, it holds the value of price
 * @throws Exception
 */
    MutualFund(String tempSymbol, String tempName, double tempQuantity, double tempPrice) throws Exception {
        super(tempSymbol, tempName, tempQuantity, tempPrice, 45, 0, "mutual fund");
    }
}