HOW TO BUILD:
    1. Be in the directory, where ePortfolio is cloned
    2. Enter the command to compile: javac ePortfolio/*.java
    3. Run the program using : java ePortfolio.GUI

TEST PLAN:
    1. The user will click on "Commands", a menu will be displayed and then the desired action can be selected from menu. Menu will contain; Buy, Sell, Update, Search and Quit.
    2. Suppose the user selected Buy. A page will be displayed with default type selected as stock. He can switch between stock and mutualfund. Buy a stock with with by filling 
    the fields with symbol, name, price and quantity. and click on 'Buy' Button. 'Transaction Completed!' will be displayed in the message box, means stock is bought. This can also 
    checked by selecting Commands>Search and filling the symbol of the stock. Data related to that stock will be displayed in the message box. Now try buying same stock. It will be added to the same stock. 
    3. Click 'Reset' to clear every field. If user tries to buy same investment but of different types the program will prevent him from continuing with that action. For example, suppose there is stock with symbol
    "APP". Try to add the symbol "APP" of type mutualfund. It will not be possible to carry out this action. The message will be displayed stating that the sction is not possible.
    4. Buy two to three investments to see that program is working. Use Commands>Search and press 'Search' button (with emoty fields) to see all investments.
    5. Select Command>Sell to sell an investment. Fill in the fields with one of stock which is already present. Click Sell. The messagebox will display the related message along with the payment. Make sure the 
    quantity you are selling is lesser than the quantity present.Otherwise the 
    related error message will be displayed.
    6. Sell all of any one particualar investment to see that it is removing the investment.
    7. For Update, click Commands>Update. Now fields with symbol and name will be automatically filled will be preset there. enter the new price and click 'Save' to save the new price. Click 'Next' to move to 
    next investment and 'prev' to move to the previous investment.
    8. For getGain, click Commands>getGain. The total gain in the 'Total Gain' field and gain of every single investments in textArea will be displayed.
    9. Search function is used to search an element depending on the input. There can be total of 2^3 = 8 cases, which are
    as follows:
        Symbol, keyword, price
        1           0       0
        1           1       0
        1           1       1
        1           0       1
        0           1       1
        0           0       1
        0           1       0
        0           0       0
        These cases can be tried for searching.
        
        Search can also be performed without entering any data. It will display every investment present
        Enter Price range in the format: 
        -Only High Price 
        -Only Low Price. 
        -Leave any one of them empty. 
        -Write both high and low price
        
        The result will be displayed in the Text Area.
        
    10. Last option in the menu,i.e. 'Quit' is to quit the program. It will close the GUI interface and end the program.
    11. Exceptions are also handled: To check exceptions handling, do following:
    	For Buy:
    	i. Keep any of the field in Buy Panel Empty.
    	ii. Write Alphabats instead of numeriical values in Quantity and Price
    	iii. Write negative value of quantity or price.
    	
    	For Sell:
    	i. Keep any of the field in Buy Panel Empty.
    	ii. Write Alphabats instead of numeriical values in Quantity and Price.
    	iii. Write negative value of quantity or price.
    	iv. Try to sell investment which is not present.
    	v. Try to sell sommething with quantity greater than present in stock.
    	
    	For Update:
    	i. Try to click save without entering the price.
    	ii. Try entering alphabats instead of numerical values in Price field.
    	
    	On testing these cases, related errors will be displayed.
    	Similarly, there many other errors and exceptions which are handled by the program.

EDGE CASES:
	These are same as Exceptions which are handled in the program.
    1. Negative values of quantity are handled.
    2. Quantity = 0 is handled so that bookvalue doesn't become infinity.
    3. Perform search using options from test plan #9.
    4. When all the quantity is sold.
