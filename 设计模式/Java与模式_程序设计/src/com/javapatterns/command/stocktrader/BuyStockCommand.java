package com.javapatterns.command.stocktrader;

class BuyStockCommand implements Command
{
	
	private StockMarket stock; 
	
	public BuyStockCommand ( StockMarket stock)
    {
		stock = stock;
	} 
	public void execute( )
    {
		stock.buy( );
	} 

} 

