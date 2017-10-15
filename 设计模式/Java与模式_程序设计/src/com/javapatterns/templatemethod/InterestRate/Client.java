package com.javapatterns.templatemethod.InterestRate;

public class Client
{
    private static Account acct = null;

    public static void main(String[] args)
    {
    	acct = new MoneyMarketAccount();
        System.out.println("Interest earned from Money Market account = " + acct.calculateInterest());

    	acct = new CDAccount();
        System.out.println("Interest earned from CD account = " + acct.calculateInterest());
    }
}
