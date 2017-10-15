package com.javapatterns.command.stocktrader;

// Client
public class Client
{
	Broker testInvoker;

	public static void main(String[] args)
    {
		StockMarket stockMarket = new StockMarket();
		BuyStockCommand buy = new BuyStockCommand(stockMarket);
		SellStockCommand sell = new SellStockCommand(stockMarket);
		
		Broker myBroker = new Broker( buy, sell);

		myBroker.buy(); // Buy Shares
		myBroker.sell(); // Sell Shares
	} 
} 
